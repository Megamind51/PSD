package worker;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class NotificationHandler {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket pubs = context.socket(SocketType.XSUB);
        ZMQ.Socket subs = context.socket(SocketType.XPUB);
        System.out.println(pubs.bind("tcp://*:10000"));
        System.out.println(subs.bind("tcp://*:10001"));
        ZMQ.proxy(pubs,subs,null);
        //new Proxy(context, pubs, subs).poll();

    }
}