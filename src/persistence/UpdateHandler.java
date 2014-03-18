package persistence;

import persistence.server.Client;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateHandler{
    Client client;

    public UpdateHandler(){
        Client client = new Client(this);
        client.run();
    }

    public void receiveUpdates(HashMap<String, ArrayList<String>> changes, String type){
        for(String table : changes.keySet()){
            switch(table){
                case "User":
                    break;
                case "Meeting":
                    break;
                case "MeetingRoom":
                    break;
                case "MeetingInvite":
                    break;
                case "Group":
                    break;
                case "GroupMembership":
                    break;
                case "MeetingAdmin":
                    break;
                case "ExternalUser":
                    break;
                default:
                    return;
            }
        }
    }

    public void sendUpdates(HashMap<String, ArrayList<String>> changes, String type){
        client.sendMessage(changes, type);
    }

    public static void main(String args[]){
        new UpdateHandler();
    }
}
