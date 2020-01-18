package worker;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Queue {

    public static void main(String[] args) {

        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");
            ZMQ.Socket push = context.createSocket(SocketType.PUSH);
            push.bind("tcp://*:" + args[0]);
            ZMQ.Socket pull = context.createSocket(SocketType.PULL);
            pull.bind("tcp://*:" + args[1]);
            ZMQ.proxy(push, pull, null);
        }
    }
}