package persistence;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    public static final String HOST = "localhost";
    public static final int PORT = 1234;

    private ServerSocket serverSocket;
    private ArrayList<Socket> clients = new ArrayList<>();
    private boolean serverOn = true;

    public Server(){
        try{
            serverSocket = new ServerSocket(PORT);
        }catch(IOException ioe){
            System.out.println("Could not create server socket on port " + PORT);
            System.exit(0);
        }

        System.out.println("Server running on " + HOST + ":" + PORT);

        while(serverOn){
            try{
                Socket clientSocket = serverSocket.accept();
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

    public void sendMessage(ArrayList<String> tables, Socket socket){
        for(Socket client : clients){
            if(client == socket) continue;

            try {
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                out.writeObject(tables);
                out.flush();
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
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    System.out.println("Waiting for client");

                    try {
                        ArrayList<String> tables = (ArrayList<String>) in.readObject();

                        sendMessage(tables, socket);
                    }catch(ClassNotFoundException e){
                        System.out.println("Received unapproved item");
                    }
                }
            }catch(IOException e){
                System.out.println("Disconnected from " + socket);
            }finally{
                try{
                    socket.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main (String[] args){
        new Server();
    }
}
