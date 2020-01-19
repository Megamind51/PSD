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
import java.util.ArrayList;
import java.util.List;

public class Negociator {

    List<Item> items;
    ZMQ.Socket pull ;
    ZMQ.Socket sub  ;
    ZMQ.Socket pub  ;
    ZContext context ;

    Negociator(String pull_port, String sub_port, String pub_port){
        this.context = new ZContext();
        this.pull = context.createSocket(SocketType.PULL);
        this.sub  = context.createSocket(SocketType.SUB);
        this.pub  = context.createSocket(SocketType.PUB);
        this.pull.connect("tcp://localhost:" + pull_port);
        this.sub.connect("tcp://localhost:" + sub_port);
        this.pub.connect("tcp://localhost:" + pub_port);

       // new Thread_Sub(this.sub, this.pub).start();
        new Thread_Pull(this.pull, this.sub, this.pub).run();
    }

    public static void main(String[] args) {
        new Negociator(args[0], args[1], args[2]);
    }
}

class Thread_Pull extends Thread{

    ZMQ.Socket pull ;
    ZMQ.Socket pub  ;
    ZMQ.Socket sub  ;

    public Thread_Pull(ZMQ.Socket pull, ZMQ.Socket sub, ZMQ.Socket pub) {
        this.pull = pull;
        this.pub = pub;
        this.sub = sub;
    }

    @Override
    public void run() {
        while(true){
            byte[] b = this.pull.recv();
            ProtoManufacturer.ManufacturerRequest product = null;
            try {
                product = ProtoManufacturer.ManufacturerRequest.parseFrom(b);
                Item item = new Item(product.getMinQuantity(),product.getMaxQuantity(),product.getMinPrice(),product.getSeconds(),product.getProduct());

                Gson gson =  new Gson();
                String novoItem = gson.toJson(item);
                System.out.println("\nJSON AQUI: " + novoItem);
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
                int response = con.getResponseCode();



            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(product.toString());
            this.sub.subscribe(product.getProduct() + "_" + product.getManufacturer());
            String toSend = "manufacturer_" + product.getProduct()  + "/Manufacturer " + product.getManufacturer() + " producing " + product.getProduct() + "at max of " + product.getMaxQuantity() + "at min_price of " +product.getMinPrice() ;
            this.pub.send(toSend);
            System.out.println(this.pub.toString());
            System.out.println(toSend);
        }
    }
}


class Thread_Sub extends Thread{

    ZMQ.Socket sub ;
    ZMQ.Socket pub ;

    public Thread_Sub(ZMQ.Socket sub, ZMQ.Socket pub) {
        this.sub = sub;
        this.pub = pub;
    }

    @Override
    public void run() {
        while(true){
            byte[] b = this.sub.recv();
            ProtoImporter.ImporterRequest bid = null;
            try {
                bid = ProtoImporter.ImporterRequest.parseFrom(b);
                Order order = new Order(bid.getImporter(),bid.getQuantity(),bid.getPrice());
                Gson gson =  new Gson();
                String novOrder = gson.toJson(order);
                System.out.println("\nJSON AQUI: " + novOrder);
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
                int response = con.getResponseCode();



            } catch (Exception e) {
                e.printStackTrace();
            }
            String toSend = "bid_" + bid.getManufacturer() + "_" + bid.getProduct() + "/Bid no valor de " + bid.getPrice() + "para quantidade de " + bid.getQuantity() + "no produto" +bid.getProduct() + "_" +bid.getManufacturer();
            this.pub.send(toSend);
        }
    }
}