package worker;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class PullNegociator {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket pull = context.createSocket(SocketType.PULL);
            pull.connect("tcp://localhost:" + args[0]);
        while (true) {
            byte[] b = pull.recv();
            String n = new String(b);
            System.out.println("Received " + n);
        }
        //source.close();
        //sink.close();
        //context.term();
        }
    }
}

