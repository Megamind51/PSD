package company.Client;

import company.Protos.AuthOuterClass.Auth;


import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.SocketChannel;


public class Client {

    public static void main(String[] args) {
        try{

            SocketChannel client = SocketChannel.open();
            SocketAddress socketAddr = new InetSocketAddress("localhost", Integer.parseInt(args[0]));
            client.connect(socketAddr);

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            Auth p = createAuth(0,0,"O ganso","Ah o ganso");
            byte[] ba = p.toByteArray();
            client.write(ByteBuffer.wrap(ba));
            Thread.sleep(3000);
            client.read(byteBuffer);
            p = Auth.parseFrom(byteBuffer) ;
            System.out.println(p.toString());
        }catch(Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }


    static Auth createAuth(int operation, int type, String username, String password) {
        return
                Auth.newBuilder()
                        .setOperationValue(operation)
                        .setTypeValue(type)
                        .setUsername(username)
                        .setPassword(password)
                        .build();
    }

}
