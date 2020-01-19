package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashSet;

import protos.ProtoAuth.Auth;
import protos.ProtoManufacturer.ManufacturerRequest;
import protos.ProtoImporter.ImporterRequest;


public class Client {

    private String username;
    private Auth.Type type;

    private BufferedReader in;
    private SocketChannel socketChannel;
    private ByteBuffer byteBuffer;

    private HashSet<String> subscriptions = new HashSet<>();

    public Client(int server) throws Exception {

        SocketAddress socketAddress = new InetSocketAddress("localhost", server);

        socketChannel = SocketChannel.open();
        socketChannel.connect(socketAddress);

        byteBuffer = ByteBuffer.allocate(1024);

        in = new BufferedReader(new InputStreamReader(System.in));

        while (true) startClient();



    }

    private void startClient() throws Exception{

        while (!authenticate());

        System.out.println("Authenticated!");

        String option;
        boolean logged_in = true;

        while (logged_in) {

            printMainMenu();
            option = in.readLine();

            switch (type) {

                case MANUFACTURER:

                    switch (option) {

                        case "1":
                            publishProduct();
                            break;
                        case "2":
                            logged_in = false;
                            break;

                    }

                    break;
                case IMPORTER:

                    switch (option) {

                        case "1":
                            makeBid();
                            break;
                        case "2":
                            dealSubscriptions();
                            break;
                        case "3":
                            checkCatalog();
                            break;
                        case "4":
                            logged_in = false;
                            break;

                    }

                    break;

            }

        }

    }

    private boolean authenticate() throws Exception{

        System.out.println("Username: ");

        String username = in.readLine();
        this.username = username;

        System.out.println("Password: ");
        String password = in.readLine();

        Auth auth_proto = Auth.newBuilder().setUsername(username)
                .setPassword(password)
                .build();

        byte[] data = auth_proto.toByteArray();

        // Sending TCP binary
        send(data);

        // Receiving TCP binary
        data = receive();

        auth_proto = Auth.parseFrom(data);

        byteBuffer.clear();

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

        return false;
    }

    private void printMainMenu() {

        switch (type) {
            case MANUFACTURER:
                System.out.println("#####################################################");
                System.out.println("# 1 - Publicar produção                             #");
                System.out.println("# 2 - Logout                                        #");
                System.out.println("#####################################################");
                break;
            case IMPORTER:
                System.out.println("#####################################################");
                System.out.println("# 1 - Fazer oferta                                  #");
                System.out.println("# 2 - Gerir subscrições                             #");
                System.out.println("# 3 - Consultar Catálogo                            #");
                System.out.println("# 4 - Logout                                        #");
                System.out.println("#####################################################");
                break;
        }

    }

    private void printDealSubscriptionsMenu() throws Exception {

        System.out.println("#####################################################");
        System.out.println("# 1 - Add subscription                              #");
        System.out.println("# 2 - Remove subscription                           #");
        System.out.println("# 3 - List subscriptions                            #");
        System.out.println("# 4 - Return to main menu                           #");
        System.out.println("#####################################################");

    }

    private void printCatalogMenu() throws Exception {

        System.out.println("#####################################################");
        System.out.println("# 1 - List manufacturers                            #");
        System.out.println("# 2 - List products of manufacturer                 #");
        System.out.println("# 3 - List current bids of a product                #");
        System.out.println("# 4 - Check manufacturer history                    #");
        System.out.println("# 5 - Return to main menu                           #");
        System.out.println("#####################################################");

    }

    private void publishProduct() throws Exception{

        System.out.println("Product Name: ");
        String product = in.readLine();

        System.out.println("Minimum Quantity: ");
        String min_quantity = in.readLine();

        System.out.println("Maximum Quantity: ");
        String max_quantity = in.readLine();

        System.out.println("Mininum Unitary Price: ");
        String price = in.readLine();

        System.out.println("Time period (seconds): ");
        String seconds = in.readLine();

        ManufacturerRequest product_proto = ManufacturerRequest.newBuilder()
                .setManufacturer(username)
                .setProduct(product)
                .setMinQuantity(Integer.parseInt(min_quantity))
                .setMaxQuantity(Integer.parseInt(max_quantity))
                .setMinPrice(Float.parseFloat(price))
                .setSeconds(Integer.parseInt(seconds))
                .build();

        byte[] data = product_proto.toByteArray();

        send(data);

    }

    private void dealSubscriptions () throws Exception {

        String option;
        boolean inSubscriptionMenu = true;

        while (inSubscriptionMenu) {

            printDealSubscriptionsMenu();
            option = in.readLine();

            switch (option) {

                case "1":
                    System.out.println("Manufacturer: ");
                    String manufacturer = in.readLine();
                    subscriptions.add(manufacturer);
                    // SUBSCRIBE 0MQ
                    break;
                case "2":
                    System.out.println("Manufacturer to remove: ");
                    String manufacturerToRemove = in.readLine();
                    subscriptions.remove(manufacturerToRemove);
                    // UNSUBSCRIBE 0MQ
                    break;
                case "3":
                    for (String sub: subscriptions) System.out.println(sub);
                    break;
                case "4":
                    inSubscriptionMenu = false;
                    break;

            }
        }
    }

    private void checkCatalog () throws Exception {

        String option;
        boolean inCatalogMenu = true;

        while (inCatalogMenu) {

            printCatalogMenu();
            option = in.readLine();

            switch (option) {

                case "1":
                    ImporterRequest manufacturersRequest = ImporterRequest.newBuilder()
                                    .setOperation(ImporterRequest.Operation.LIST_MANUFACTURERS)
                                    .build();

                    send(manufacturersRequest.toByteArray());
                    break;

                case "2":
                    System.out.println("Manufacturer: ");
                    String manufacturerForManufacturerRequests = in.readLine();

                    ImporterRequest productsRequest = ImporterRequest.newBuilder()
                            .setOperation(ImporterRequest.Operation.LIST_PRODUCTS)
                            .setManufacturer(manufacturerForManufacturerRequests)
                            .build();

                    send(productsRequest.toByteArray());
                    break;

                case "3":
                    System.out.println("Manufacturer: ");
                    String manufacturerForBids = in.readLine();
                    System.out.println("Product: ");
                    String productForBids = in.readLine();

                    ImporterRequest bidsRequest = ImporterRequest.newBuilder()
                            .setOperation(ImporterRequest.Operation.LIST_BIDS)
                            .setManufacturer(manufacturerForBids)
                            .setProduct(productForBids)
                            .build();

                    send(bidsRequest.toByteArray());
                    break;

                case "4":
                    System.out.println("Manufacturer: ");
                    String manufacturerForHistory = in.readLine();

                    ImporterRequest historyRequest = ImporterRequest.newBuilder()
                            .setOperation(ImporterRequest.Operation.CHECK_HISTORY)
                            .setManufacturer(manufacturerForHistory)
                            .build();

                    send(historyRequest.toByteArray());
                    break;

                case "5":
                    inCatalogMenu = false;
                    break;

            }
        }
    }

    private void makeBid() throws Exception {

        System.out.println("Manufacturer: ");
        String manufacturer = in.readLine();

        System.out.println("Product Name: ");
        String product = in.readLine();

        System.out.println("Quantity: ");
        String quantity = in.readLine();

        System.out.println("Price: ");
        String price = in.readLine();

        ImporterRequest importerRequest = ImporterRequest.newBuilder()
                .setOperation(ImporterRequest.Operation.MAKE_BID)
                .setImporter(username)
                .setManufacturer(manufacturer)
                .setProduct(product)
                .setQuantity(Integer.parseInt(quantity))
                .setPrice(Float.parseFloat(price))
                .build();

        send(importerRequest.toByteArray());
    }

    private void send(byte[] data) throws Exception {

        socketChannel.write(ByteBuffer.wrap(data));

    }

    private byte[] receive() throws Exception {

        byteBuffer.clear();

        socketChannel.read(byteBuffer);

        byteBuffer.flip();

        byte[] data = new byte[byteBuffer.remaining()];

        byteBuffer.get(data);

        return data;

    }

    public static void main(String[] args) throws Exception {

        // Getting server port from command line
        int port = Integer.parseInt(args[0]);

        new Client(port);

    }
}