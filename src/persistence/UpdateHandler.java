package persistence;

import persistence.server.Client;

import java.util.ArrayList;

public class UpdateHandler{
    private Client client = null;
    private DataStorage dataStorage;

    public UpdateHandler(String type){
        if(type.equals("client")) client = new Client(this);

        dataStorage = new DataStorage();
    }

    public void receiveChanges(ArrayList<Object> changedObjects, String type){
        switch (type){
            case "insert":
                insert(changedObjects);
                break;
            case "update":
                update(changedObjects);
                break;
            case "delete":
                delete(changedObjects);
                break;
            default:
                break;
        }
    }

    public void insert(ArrayList<Object> changedObjects){

    }

    public void update(ArrayList<Object> changedObjects){

    }

    public void delete(ArrayList<Object> changedObjects){

    }

    public void sendChanges(ArrayList<Object> changedObjects, String type){
        if(client != null) client.sendChanges(changedObjects, type);
    }

    public void populate(){

    }

    public static void main(String args[]){
        new UpdateHandler("client");
    }
}
