package persistence;

import persistence.server.Client;

import java.util.ArrayList;

public class UpdateHandler{
    Client client;

    public UpdateHandler(){
        Client client = new Client(this);
        client.run();
    }

    public void receiveUpdates(ArrayList<Object> changedObjects, String type){
        for(Object object : changedObjects){

        }
    }

    public void sendUpdates(ArrayList<Object> changedObjects, String type){
        client.sendMessage(changedObjects, type);
    }

    public static void main(String args[]){
        new UpdateHandler();
    }
}
