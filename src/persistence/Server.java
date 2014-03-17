package persistence;

import model.User;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server extends Thread {
    public static final int PORT = 1235;
    public static final int THROTTLE = 200;

    private ServerSocket serverSocket;
    private InetAddress host;
    private Socket socket;
    private DataOutputStream out;
    private ArrayList<Client> clients = new ArrayList<>();

    User user = new User("Simoms", "drossap", "Simon B-J", "simoms_85@hotmail.com", "12345678");

    public Server(){
        try{
            host = InetAddress.getLocalHost();
        }catch(UnknownHostException e){
            System.out.println("Could not get the host address.");
            System.exit(0);
        }

        System.out.println("Running server on ip " + host);

        try{
            serverSocket = new ServerSocket(PORT, 0, host);
        }catch(IOException e){
            System.out.println("Could not open server socket.");
            System.exit(0);
        }

        System.out.println("Socket " + serverSocket + " created.");
    }

    public void run(){
        System.out.println("Server is running.");

        while(true) {
            for(Client client : clients){
                if(!client.isConnected()){
                    System.out.println(client + " disconnected.");
                    clients.remove(client);
                }
            }

            try{
                socket = serverSocket.accept();
                //Send and receive

                out.writeUTF("Hei");
                out.flush();
                out.close();

                //
            }catch(IOException e){
                System.out.println("Could not connect to client.");
            }

            System.out.println("Client " + socket + " has connected.");

            clients.add(new Client(socket));

            try{
                Thread.sleep(THROTTLE);
            }catch(InterruptedException e){
                System.out.println("Server has been interrupted.");
            }
        }
    }

    //For testing purposes
    public static void main(String args[]){
        Server server = new Server(); server.start();
    }
}
