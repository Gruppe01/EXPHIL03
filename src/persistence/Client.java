package persistence;

import java.net.Socket;
import java.io.*;

public class Client {
    private static final int THROTTLE = Server.THROTTLE;
    private Socket socket;
    private boolean connected;
    private Inport inport;

    private class Inport extends Thread{
        private DataInputStream in;

        public void run(){
            try{
                //Send and receive
                in = new DataInputStream(socket.getInputStream());

                System.out.println(in.read());
                //End
            }catch(IOException e){
                System.out.println("Could not get input stream from " + toString());
                return;
            }

            System.out.println(socket + " has connected input.");

            while(true){
                try{
                    Thread.sleep(THROTTLE);
                }catch(Exception e){
                    System.out.println(toString() + " has input interrupted.");
                }
            }
        }
    }

    public Client(Socket newSocket){
        socket = newSocket;
        connected = true;
        inport = new Inport();
        inport.start();
    }

    public boolean isConnected(){
        return connected;
    }

    public void purgeDissconnect(){
        try{
            connected = false;
            socket.close();
        } catch(IOException e){
            System.out.println("Could not purge " + socket + ".");
        }
    }

    public String toString(){
        return socket.toString();
    }

    //For testing purposes
    public static void main(String args[]) throws Exception{
        Client client = new Client(new Socket("127.0.0.1", 1234)); client.inport.start();;
    }
}
