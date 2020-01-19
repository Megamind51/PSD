package worker;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;
import org.zeromq.ZContext;

import java.util.Scanner;

public class SubTester {

    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket socket = context.socket(SocketType.SUB);
        socket.connect("tcp://localhost:10001");
        while(true){
            byte[] dados = socket.recv();
            System.out.println(new String(dados));
        }
    }
}
