package persistence.server;

import persistence.UpdateHandler;

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
    private UpdateHandler updateHandler;

    public Server(UpdateHandler updateHandler){
        try{
            serverSocket = new ServerSocket(PORT);
        }catch(IOException ioe){
            System.out.println("Could not create server socket on " + HOST + ":" + PORT);
            System.exit(0);
        }

        System.out.println("Server running on " + HOST + ":" + PORT);

        this.updateHandler = updateHandler;

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

    public void sendChanges(ArrayList<Object> changedObjects, String type){
        for(Socket client : clients){
            try {
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                out.writeObject(type);
                out.writeObject(changedObjects);
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
                    System.out.println("Waiting for changes");

                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    try {
                        String type = (String) in.readObject();
                        ArrayList<Object> changedObjects = (ArrayList<Object>) in.readObject();

                        updateHandler.receiveChanges(changedObjects, type);
                        sendChanges(changedObjects, type);
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

    public UpdateHandler getUpdateHandler(){
        return updateHandler;
    }

    public static void main (String[] args){
        UpdateHandler updateHandler = new UpdateHandler("server");
        Server server = new Server(updateHandler);


    }
}
