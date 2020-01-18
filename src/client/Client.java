package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import protos.AuthOuterClass.Auth;

public class Client {

    private String username;
    private Auth.Type type;

    private BufferedReader in;
    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;

    public Client(int server) throws Exception {

        SocketAddress socketAddress = new InetSocketAddress("localhost", server);

        socketChannel = SocketChannel.open();
        socketChannel.connect(socketAddress);

        byteBuffer = ByteBuffer.allocate(1024);

        in = new BufferedReader(new InputStreamReader(System.in));

        while (!authenticate());

        System.out.println("Authenticated!");

    }

    private boolean authenticate() {

        System.out.println("Username: ");
        try {
            
            String username = in.readLine();
            this.username = username;

            System.out.println("Password: ");
            String password = in.readLine();

            Auth auth_proto = Auth.newBuilder().setUsername(username)
                    .setPassword(password)
                    .build();

            byte[] data = auth_proto.toByteArray();

            socketChannel.write(ByteBuffer.wrap(data));
            
            byteBuffer.clear();

            socketChannel.read(byteBuffer);

            byteBuffer.flip();

            data = new byte[byteBuffer.remaining()];

            byteBuffer.get(data);

            auth_proto = Auth.parseFrom(data) ;

            switch (auth_proto.getAuthenticated()) {
                case 0:
                    type = auth_proto.getType();
                    System.out.println(type);
                    return true;
                case 1:
                    System.out.println("Username unknown.");
                    break;
                case 2:
                    System.out.println("Passwords don't match.");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
/*
    public void startCliente() throws IOException {



        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String msg;
        int resultado = 0;


        while (true) {
            print_menu();
            if ((msg = in.readLine()).toUpperCase().equals("EXIT")) break;
            try {
                resultado = Integer.parseInt(msg);
                switch (resultado) {
                    case 1:
                        send_post(in);
                        break;
                    case 2:
                        deal_subscriptions(in);
                        break;
                    case 3:
                        list_posts();
                        break;
                    case 4:
                        get_username(in);
                        break;
                    default:
                        System.out.println("Opcao invalida");
                }
                //tipo_menu = resultado;

            } catch (Exception e) {
                System.out.println("Formato invalido");
                ;
            }

        }
    }

    private void deal_subscriptions(BufferedReader in) {
        String msg;
        int lido;


        while (true) {
            try {
                print_deal_subscriptions();
                msg = in.readLine();
                lido = Integer.parseInt(msg);
                if (lido == 4) break;
                switch (lido) {
                    case 1:
                        System.out.println("Inserir Categoria");
                        msg = in.readLine();
                        categories.add("#" + msg);
                        break;
                    case 2:
                        System.out.println("Categoria a remover");
                        msg = in.readLine();
                        if (categories.contains("#" + msg)) {
                            categories.remove(categories.indexOf("#" + msg));
                        } else {
                            System.out.println("Categoria não subscrita");
                            System.out.println("Categorias subscritas são");
                            print_categories();
                        }
                        break;
                    case 3:
                        print_categories();
                }

            } catch (IOException ex) {
                System.out.println("Por favor utilizador formato valido");
            }

        }
    }

    private void print_deal_subscriptions() {
        System.out.println("#####################################################");
        System.out.println("# 1 - Adicionar subscricao                          #");
        System.out.println("# 2 - Remover subscricoes                           #");
        System.out.println("# 3 - Listar subscricoes                            #");
        System.out.println("# 4 - Voltar menu inicial                           #");
        System.out.println("#####################################################");
    }

    private void print_categories() {
        System.out.println(categories.toString());
    }

    public void send_post(BufferedReader in ){
            System.out.println("Escreva o post:");
            try {
                String mensagem = in.readLine();
                System.out.println("Categorias");
                ArrayList<String> arrayList = new ArrayList<String>();
                Matcher m = Pattern.compile("#[-_'a-zA-ZÀ-ÖØ-öø-ÿ0-9]*")
                        .matcher(mensagem);
                while (m.find()) {
                    arrayList.add(m.group());
                }
                for (String cena : arrayList)
                    System.out.println(1);
                if( arrayList.size() < 1){
                    System.out.println("Necessário pelo menos 1 categoria");

                }else{
                    int id = counter.increment();
                    Post post = new Post(mensagem, arrayList, username, id );
                    byte[] data = post_serializer.encode(post);
                    System.out.println(servidor.toString());
                    System.out.println("->" + post.toString());
                    this.log.writeLog("POST " + post.toString() + " " + servidor.port(),id);
                    messagingService.sendAsync(servidor, "POST", data);
            }

        } catch (IOException e) {
            System.out.println("Formato invalido");
        }

    }

    public void list_posts() {
        if (categories.size() == 0) {
            System.out.println("Sem categorias selecionadas");
        } else {
            Get get = new Get(categories, counter.increment());

            byte[] data = get_serializer.encode(get);
            System.out.println("Esperar pela resposta do servidor");
            messagingService.sendAsync(servidor, "GET", data);

        }
    }

    public void print_menu() {
        System.out.println("#####################################################");
        System.out.println("# 1 - Fazer um Post                                 #");
        System.out.println("# 2 - Adicionar/Remover subscricoes                 #");
        System.out.println("# 3 - Pedir 10 posts mais recentes das subscricoes  #");
        System.out.println("# 4 - Mudar de utilizador                           #");
        System.out.println("#####################################################");
    }
*/
    public static void main(String[] args) throws Exception {

        // Getting server port from command line
        int port = Integer.parseInt(args[0]);

        new Client(port);

    }
}