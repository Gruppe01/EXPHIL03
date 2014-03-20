package persistence.server;

import persistence.ServerDataHandler;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    public static String HOST;
    public static final int PORT = 1234;

    private ServerSocket serverSocket;
    private ArrayList<Socket> clients = new ArrayList<>();
    private boolean serverOn = true;
    private ServerDataHandler dataHandler;

    public Server(){
        this.dataHandler = new ServerDataHandler();

        try{
            serverSocket = new ServerSocket(PORT);
        }catch(IOException ioe){
            System.out.println("Could not create server socket on " + HOST + ":" + PORT);
            System.exit(0);
        }

        try {
            HOST = InetAddress.getLocalHost().getHostAddress();
        }catch (Exception e){
            HOST = "localhost";
        }

        System.out.println("Server running on " + HOST + ":" + PORT);

        while(serverOn){
            try{
                Socket clientSocket = serverSocket.accept();
                populateClient(clientSocket);
                clients.add(clientSocket);

                System.out.println(clientSocket + " connected to server");

                ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
                cliThread.start();
            }catch(IOException ioe){
                System.out.println("Exception encountered on connection to socket");
            }
        }

        try{
            serverSocket.close();
            System.out.println("Server stopped");
        }catch(Exception e){
            System.out.println("Problem stopping server socket");
            System.exit(0);
        }
    }

    public void populateClient(Socket client){
        try{
            Object object = dataHandler.getDataStorage();

            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            out.writeObject("populate");
            out.writeObject(object);
            out.flush();
        }catch (IOException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void sendChanges(Object changedObject, String type){
        for(Socket client : clients){
            try {
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                out.writeObject(type);
                out.writeObject(changedObject);
                out.flush();

                System.out.println("Data send to " + client);
            }catch(IOException e){
                System.out.println("Could not send to " + client);
            }
        }
    }

    @SuppressWarnings("unchecked")
    class ClientServiceThread extends Thread{
        private Socket socket;

        public ClientServiceThread(Socket socket){
            this.socket = socket;
        }

        public void run(){
            try{
                while(serverOn){
                    System.out.println("Waiting for changes");

                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    try {
                        String type = (String) in.readObject();
                        Object changedObject = in.readObject();

                        dataHandler.receiveChanges(changedObject, type);
                        dataHandler.editDatabase(changedObject, type);
                        sendChanges(changedObject, type);
                    }catch(ClassNotFoundException e){
                        System.out.println("Received unapproved item");
                    }
                }
            }catch(IOException e){
                System.out.println("Disconnected from " + socket);
            }finally{
                try{
                    clients.remove(socket);
                    socket.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public ServerDataHandler getDataHandler(){
        return dataHandler;
    }

    public static void main (String[] args){
        Server server = new Server();
    }
}
