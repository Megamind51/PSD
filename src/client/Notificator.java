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
            msg = msg.replaceFirst("[a-zA-Z0-9]*/","");
            System.out.println("Notification -> " + msg);
        }
    }

}