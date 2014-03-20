package persistence.server;

import persistence.DataHandler;
import persistence.DataStorage;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client extends Thread{
    private static String HOST;
    private static int PORT;
    private Socket socket;
    private DataHandler dataHandler;

    public Client(DataHandler dataHandler){
        HOST = Server.HOST;
        PORT = Server.PORT;

        this.dataHandler = dataHandler;
        this.start();
    }

    public Client(){
        HOST = Server.HOST;
        PORT = Server.PORT;

        this.dataHandler = new DataHandler();
        this.start();
    }

    public Client(String host, int port){
        HOST = host;
        PORT = port;

        this.dataHandler = new DataHandler();
        this.start();
    }

    @SuppressWarnings("unchecked")
    public void run(){
        ObjectInputStream in = null;

        try{
            socket = new Socket(Server.HOST, PORT);
            System.out.println("Connected to server on " + HOST + ":" + PORT);

            while(true){
                in = new ObjectInputStream(socket.getInputStream());

                try{
                    String type = (String) in.readObject();
                    ArrayList<Object> changedObjects = (ArrayList<Object>) in.readObject();

                   dataHandler.receiveChanges(changedObjects, type);
                }catch(ClassNotFoundException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        }catch(UnknownHostException e){
            System.err.println("Cannot connect to " + HOST + " on port " + PORT);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(in != null) in.close();

                System.out.println("Closed input stream");
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void sendChanges(ArrayList<Object> changedObjects, String type){
        ObjectOutputStream out;

        try{
            out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(type);
            out.writeObject(changedObjects);
            out.flush();
        }catch(IOException e){
            System.out.println("Could not send to " + socket);
        }
    }

    public DataStorage getDataStorage(){
        return dataHandler.getDataStorage();
    }

    public void setDataHandler(DataHandler dataHandler){
        this.dataHandler = dataHandler;
    }

    public DataHandler getDataHandler(){
        return dataHandler;
    }
}
