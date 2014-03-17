package gui;


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
	private JPanel curPanel;
	
	public Frame(){
		
		frame = new JFrame();
		login = new Login(this);
		createUser = new CreateUser(this);
		createMeeting = new CreateMeeting();
		editMeeting = new EditMeeting();
		mainScreen = new MainScreen();
		showMeeting = new ShowMeeting();
		weekCalendar = new WeekCalendar();
		editMembers = new EditMembers();
		
		addExternal = new AddExternal();
		showMembers = new ShowMembers();
		
		curPanel = login;
		
		this.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setVisible(true);
		setFrame("login");//this is for testing
		
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
				frame.setSize(600, 300);
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
			case "editMembers":
				frame.remove(curPanel);
				frame.add(editMembers);
				frame.validate();
				frame.repaint();
				curPanel=editMembers;
				frame.setSize(700, 300);
				break;
		}
		
		
		
	}
}
