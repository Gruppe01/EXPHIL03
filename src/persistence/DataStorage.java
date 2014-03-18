package persistence;

public class DataStorage {
    private Users users;
    private Groups groups;
    private Rooms rooms;
    private Meetings meetings;

    public DataStorage(Users users, Groups groups, Rooms rooms, Meetings meetings){
        this.users = users;
        this.groups = groups;
        this.rooms = rooms;
        this.meetings = meetings;
    }

    public DataStorage(){
        initialize();
    }

    public Users getUsers() {
        return users;
    }

    public Groups getGroups() {
        return groups;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public Meetings getMeetings() {
        return meetings;
    }

    private void initialize(){

    }
}
