package company.Client;

import company.Protos.AuthOuterClass.Auth;


import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

import java.io.*;
import java.net.*;


public class Client {

    public static void main(String[] args) {
        try{
            if(args.length<2)
                System.exit(1);

            String host = args[0];
            int port = Integer.parseInt(args[1]);
            Socket s = new Socket(host, port);
            CodedInputStream cis = CodedInputStream.newInstance(s.getInputStream());
            CodedOutputStream cos = CodedOutputStream.newInstance(s.getOutputStream());
            Auth p = createAuth(0,0,"O ganso","Ah o ganso");
            byte[] ba = p.toByteArray();
            while (true) {
                System.out.println("Len: " + ba.length);
                cos.writeFixed32NoTag(ba.length);
                System.out.println("Wrote Len");
                cos.writeRawBytes(ba);
                System.out.println("Wrote " + ba.length + " bytes");
                cos.flush();
                Thread.sleep(3000);
            }
            //os.close();
            //s.shutdownOutput();
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
