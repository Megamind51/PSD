package worker;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import java.util.Scanner;

public class Tester {

    public static void main(String[] args) {
            ZMQ.Context context = ZMQ.context(1);
            ZMQ.Socket socket = context.socket(SocketType.PUB);
            socket.connect("tcp://localhost:10000");
            while(true){
                Scanner scanner= new Scanner(System.in);
                String test = scanner.nextLine();
                socket.send(test);
            }
    }
}
