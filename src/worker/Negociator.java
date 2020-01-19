package worker;

import catalog.representations.Bid;
import catalog.representations.Item;
import catalog.representations.Order;
import catalog.representations.Manufacturer;
import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import protos.ProtoImporter;
import protos.ProtoManufacturer;

import java.io.*;
import java.net.*;

import java.net.URL;
import java.util.*;

public class Negociator {

    List<Item> items;
    ZMQ.Socket pull ;
    //ZMQ.Socket sub  ;
    ZMQ.Socket pub  ;
    ZContext context ;

    Negociator(String pull_port, String sub_port, String pub_port){
        this.context = new ZContext();
        this.pull = context.createSocket(SocketType.PULL);
        //this.sub  = context.createSocket(SocketType.SUB);
        this.pub  = context.createSocket(SocketType.PUB);
        this.pull.connect("tcp://localhost:" + pull_port);
        //this.sub.connect("tcp://localhost:" + sub_port);
        this.pub.connect("tcp://localhost:" + pub_port);

        new Thread_Pull(this.pull, this.context, sub_port, this.pub).start();
    }

    public static void main(String[] args) {
        new Negociator(args[0], args[1], args[2]);
    }
}

class Thread_Pull extends Thread{

    ZMQ.Socket pull ;
    ZMQ.Socket pub  ;
    ZContext context;
    String sub_port;

    public Thread_Pull(ZMQ.Socket pull, ZContext context, String sub_port, ZMQ.Socket pub) {
        this.pull = pull;
        this.pub = pub;
        this.context= context;
        this.sub_port = sub_port;
    }

    @Override
    public void run() {

        while(true){

            byte[] b = this.pull.recv();

            new Thread(new Thread_Sub(this.context, this.sub_port, this.pub, b)).start();

        }
    }
}


class Thread_Sub extends Thread{

    ZContext context ;
    String sub_port;
    ZMQ.Socket pub ;
    byte[] data;

    public Thread_Sub(ZContext context, String sub_port, ZMQ.Socket pub, byte[] data) {
        this.context = context;
        this.sub_port = sub_port;
        this.pub = pub;
        this.data = data;
    }

    @Override
    public void run() {

        ZMQ.Socket sub = this.context.createSocket(SocketType.SUB);
        sub.connect("tcp://localhost:" + sub_port);

        ProtoManufacturer.ManufacturerRequest product = null;
        try {

            product = ProtoManufacturer.ManufacturerRequest.parseFrom(data);

            Item item = new Item(product.getMinQuantity(),product.getMaxQuantity(),product.getMinPrice(),product.getSeconds(),product.getProduct());

            Gson gson =  new Gson();
            String novoItem = gson.toJson(item);

            System.out.println(novoItem);

            String path = "http://localhost:8080/manufacturers/"+product.getManufacturer()+"/addItem";

            URL url = new URL(path);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");

            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(novoItem);
            wr.flush();
            wr.close();

            con.getResponseCode();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(product.toString());

        String productTag = product.getManufacturer() + "_" + product.getProduct() + "/";
        String productNotificationPath = product.getManufacturer() + "/move2History/" + product.getProduct();
        String productWinners = product.getManufacturer() + "/winners/" + product.getProduct();
        String winnersTag = "bid_" + product.getManufacturer() + "_" + product.getProduct() + "/";

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                try {

                    sub.unsubscribe(productTag);

                    // MOVE TO HISTORY

                    String path = "http://localhost:8080/manufacturers/"+ productNotificationPath;
                    URL url = new URL(path);

                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("PUT");

                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes("");
                    wr.flush();
                    wr.close();

                    con.getResponseCode();

                    // GET WINNERS

                    path = "http://localhost:8080/manufacturers/"+ productWinners;
                    url = new URL(path);

                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");

                    con.setRequestProperty("Content-Type", "application/json");
                    con.setDoOutput(true);
                    DataInputStream ir = new DataInputStream(con.getInputStream());

                    con.getResponseCode();
                    //String winners = con.getResponseMessage();
                    String winners = ir.readLine();

                    Gson gson =  new Gson();

                    String notification = winnersTag + gson.toJson(winners);

                    pub.send(notification);

                } catch (Exception e) { e.printStackTrace();}
            }

        }, product.getSeconds() * 1000);

        sub.subscribe(productTag);

        String toSend = "manufacturer_" + product.getManufacturer()  + "/Manufacturer " + product.getManufacturer() + " producing " + product.getProduct() + " at max of " + product.getMaxQuantity() + "at min_price of " +product.getMinPrice() ;

        this.pub.send(toSend);

        while(true) {

            byte[] b = sub.recv();
            System.out.println(new String(b));
            
            String[] tag_and_rest = (new String(b)).split("/");

            byte[] c = Arrays.copyOfRange(b, (tag_and_rest[0].getBytes()).length + "/".getBytes().length, b.length);

            System.out.println(new String(c));

            ProtoImporter.ImporterRequest bid = null;
            try {

                bid = ProtoImporter.ImporterRequest.parseFrom(c);
                Order order = new Order(bid.getImporter(),bid.getQuantity(),bid.getPrice());
                Gson gson =  new Gson();
                String novOrder = gson.toJson(order);

                String path = "http://localhost:8080/manufacturers/"+bid.getManufacturer()+"/addBid/"+bid.getProduct();

                URL url = new URL(path);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("PUT");

                con.setRequestProperty("Content-Type", "application/json");
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(novOrder);
                wr.flush();
                wr.close();

                con.getResponseCode();

            } catch (Exception e) {
                e.printStackTrace();
            }

            toSend = "bid_" + bid.getManufacturer() + "_" + bid.getProduct() + "/Bid no valor de " + bid.getPrice() + "para quantidade de " + bid.getQuantity() + "no produto" +bid.getProduct() + "_" +bid.getManufacturer();

            this.pub.send(toSend);
            System.out.println(toSend);
        }
    }
}
