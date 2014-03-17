package persistence.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client {
    private static final String HOST = Server.HOST;
    private static final int PORT = Server.PORT;
    private Socket socket;

    @SuppressWarnings("unchecked")
    public void run(){
        ObjectInputStream in = null;

        try{
            socket = new Socket(Server.HOST, PORT);
            System.out.println("Connected to server on " + HOST + ":" + PORT);

            while(true){
                in = new ObjectInputStream(socket.getInputStream());

                try{
                    ArrayList<String> tables = (ArrayList<String>) in.readObject();

                   //TODO: Update client model
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

    public void sendMessage(ArrayList<String> tables){
        ObjectOutputStream out;

        try{
            out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(tables);
            out.flush();
        }catch(IOException e){
            System.out.println("Could not send to " + socket);
        }
    }

    public static void main(String args[]){
        Client client = new Client();
        client.run();
    }
}
