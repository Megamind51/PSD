package worker;

import catalog.representations.Bid;
import catalog.representations.Item;
import catalog.representations.Order;
import catalog.representations.Manufacturer;
import com.google.protobuf.InvalidProtocolBufferException;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import protos.ProtoImporter;
import protos.ProtoManufacturer;


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

        new Thread_Pull(this.pull, this.sub, this.pub).start();
        new Thread_Sub(this.sub, this.pub).start();
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
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            System.out.println(product.toString());
            this.sub.subscribe(product.getName());
            String toSend = "manufacturer_" + product.getName()  + "/Manufacturer " + product.getName() + "for " + product.getMaxQuantity() + "at min_price of " +product.getMinPrice() ;
            this.pub.send(toSend);
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
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
            String toSend = "bid_" + bid.getManufacturer() + "_" + bid.getProduct() + "/Bid no valor de " + bid.getPrice() + "para quantidade de " + bid.getQuantity() + "no produto" +bid.getProduct() + "_" +bid.getManufacturer();
            this.pub.send(toSend);
        }
    }
}