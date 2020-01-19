package client;

import org.zeromq.ZMQ;


public class Notificator extends Thread {
    private ZMQ.Socket sub;

    public Notificator(ZMQ.Socket sub) {
        this.sub = sub;
    }

    public void run() {
        String msg;
        while(true) {
            byte[] b = sub.recv();
            msg = new String(b);
            System.out.println("Notification " + msg);
        }
    }

}