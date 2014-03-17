package persistence;

import persistence.server.Client;

import java.util.ArrayList;

public class UpdateHandler{
    Client client;

    public UpdateHandler(){
        Client client = new Client(this);
        client.run();
    }

    public void receiveUpdates(ArrayList<String> tables){
        for(String table : tables){
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

    public void sendUpdates(ArrayList<String> tables){
        client.sendMessage(tables);
    }
}
