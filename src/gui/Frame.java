package gui;

import persistence.DataStorage;
import persistence.server.Client;
import model.User;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel{
	private JFrame frame;
	private Login login;
	private CreateUser createUser;
	private AddExternal addExternal;
	private CreateMeeting createMeeting;
	private EditMeeting editMeeting;
	private EditMembers editMembers;
	private MainScreen mainScreen;
	private ShowMeeting showMeeting;
	private ShowMembers showMembers;
	private WeekCalendar weekCalendar;
	private static Client client;
	private static User user;
	private static String username;
	
	private JPanel curPanel;
	
	public Frame(){
		frame = new JFrame();
		client = new Client("localhost", 1234);
		username = null;
		user = null;
		login = new Login(this);
		createUser = new CreateUser(this);
		createMeeting = new CreateMeeting(this);
		editMeeting = new EditMeeting(this);
		mainScreen = new MainScreen(this);
		showMeeting = new ShowMeeting(this);
		weekCalendar = new WeekCalendar(this);
		
		curPanel = login;
		
		this.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setVisible(true);
		setFrame("login");
		
	}
	
	public static Client getClient(){
		return client;
	}

    public static DataStorage getDataStorage(){
        return client.getDataStorage();
    }
	
	public void setUser(String in){
		username = in;
		user = client.getDataStorage().users().getUserByUsername(username);
		mainScreen.setUser(in);
	}
	
	public static String getUserName(){
		return username;
	}
	
	public static User getUser(){
		return user;
	}
	
	public ShowMeeting getShowMeeting(){
		return showMeeting;
	}
	
	public EditMeeting getEditMeeting(){
		return editMeeting;
	}
	
	public WeekCalendar getWeekCalendar(){
		return weekCalendar;
	}
	
	public void createUser(User u){
		client.getDataStorage().users().addUser(u);
	}
	
	public void logout(){
		user = null;
		username = null;
	}
	
	public MainScreen getMainScreen(){
		return mainScreen;
	}
	
	public void setFrame(String panel){
		switch(panel){
			case "login":
				frame.remove(curPanel);
				frame.add(login);
				login.refresh();
				frame.validate();
				frame.repaint();
				curPanel=login;
				frame.setSize(425,300);
				break;
			case "createUser":
				frame.remove(curPanel);
				frame.add(createUser);
				createUser.refresh();
				frame.validate();
				frame.repaint();
				curPanel=createUser;
				frame.setSize(425,300);
				break;
			case "createMeeting":
				frame.remove(curPanel);
				frame.add(createMeeting);
				createMeeting.refresh();
				frame.validate();
				frame.repaint();
				curPanel=createMeeting;
				frame.setSize(600, 350);
				break;
			case "editMeeting":
				frame.remove(curPanel);
				frame.add(editMeeting);
				editMeeting.refresh();
				frame.validate();
				frame.repaint();
				curPanel=editMeeting;
				frame.setSize(660, 360);
				break;
			case "mainScreen":
				frame.remove(curPanel);
				frame.add(mainScreen);
				mainScreen.refresh();
				frame.validate();
				frame.repaint();
				curPanel=mainScreen;
				frame.setSize(700, 400);
                mainScreen.setNewsfeed();
				break;
			case"showMeeting":
				frame.remove(curPanel);
				frame.add(showMeeting);
				showMeeting.refresh();
				frame.validate();
				frame.repaint();
				curPanel=showMeeting;
				frame.setSize(600, 360);
				break;
			case "weekCalendar":
				frame.remove(curPanel);
				frame.add(weekCalendar);
				weekCalendar.refresh();
				frame.validate();
				frame.repaint();
				curPanel=weekCalendar;
				frame.setSize(700, 400);
				break;
			
		}
	}

    public static void main(String args[]){
        new Frame();
    }
}
