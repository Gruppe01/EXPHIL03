package gui;

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
	public static Client client;
	private User user;
	private String username;
	
	private JPanel curPanel;
	
	public Frame(){
		frame = new JFrame();
		client = new Client("169.254.36.165", 1234);
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
	
	public Client getClient(){
		return client;
	}
	
	public void setUser(String in){
		username = in;
		user = client.getDataStorage().users().getUserByUsername(username);
		mainScreen.setUser(in);
	}
	
	public String getUserName(){
		return username;
	}
	
	public User getUser(){
		return user;
	}
	
	public void createUser(User u){
		client.getDataStorage().users().addUser(u);
	}
	
	public void logout(){
		user = null;
		username = null;
	}
	
	public void setFrame(String panel){
		switch(panel){
			case "login":
				frame.remove(curPanel);
				frame.add(login);
				frame.validate();
				frame.repaint();
				curPanel=login;
				frame.setSize(425,300);
				break;
			case "createUser":
				frame.remove(curPanel);
				frame.add(createUser);
				frame.validate();
				frame.repaint();
				curPanel=createUser;
				frame.setSize(425,300);
				break;
			case "createMeeting":
				frame.remove(curPanel);
				frame.add(createMeeting);
				frame.validate();
				frame.repaint();
				curPanel=createMeeting;
				frame.setSize(600, 350);
				break;
			case "editMeeting":
				frame.remove(curPanel);
				frame.add(editMeeting);
				frame.validate();
				frame.repaint();
				curPanel=editMeeting;
				frame.setSize(700, 300);
				break;
			case "mainScreen":
				frame.remove(curPanel);
				frame.add(mainScreen);
				frame.validate();
				frame.repaint();
				curPanel=mainScreen;
				frame.setSize(700, 400);
				break;
			case"showMeeting":
				frame.remove(curPanel);
				frame.add(showMeeting);
				frame.validate();
				frame.repaint();
				curPanel=showMeeting;
				frame.setSize(600, 400);
				break;
			case "weekCalendar":
				frame.remove(curPanel);
				frame.add(weekCalendar);
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
