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
	
	public Frame(){
		
		frame = new JFrame();
		login = new Login();
		createUser = new CreateUser();
		createMeeting = new CreateMeeting();
		editMeeting = new EditMeeting();
		mainScreen = new MainScreen();
		showMeeting = new ShowMeeting();
		weekCalendar = new WeekCalendar();
		editMembers = new EditMembers();
		
		addExternal = new AddExternal();
		showMembers = new ShowMembers();
		
		this.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.setVisible(true);
		//setFrame("");//this is for testing
		
	}
	
	public void setFrame(String panel){
		
		switch(panel){
			case "login":
				frame.getContentPane().removeAll();
				frame.setContentPane(login);
				frame.setSize(425,300);
				break;
			case "createUser":
				frame.getContentPane().removeAll();
				frame.setContentPane(createUser);
				frame.setSize(425,300);
				break;
			case "createMeeting":
				frame.getContentPane().removeAll();
				frame.setContentPane(createMeeting);
				frame.setSize(600, 300);
				break;
			case "editMeeting":
				frame.getContentPane().removeAll();
				frame.setContentPane(editMeeting);
				frame.setSize(700, 300);
				break;
			case "mainScreen":
				frame.getContentPane().removeAll();
				frame.setContentPane(mainScreen);
				frame.setSize(700, 400);
				break;
			case"showMeeting":
				frame.getContentPane().removeAll();
				frame.setContentPane(showMeeting);
				frame.setSize(600, 400);
				break;
			case "weekCalendar":
				frame.getContentPane().removeAll();
				frame.setContentPane(weekCalendar);
				frame.setSize(700, 400);
				break;
			case "editMembers":
				frame.getContentPane().removeAll();
				frame.setContentPane(editMembers);
				frame.setSize(700, 300);
				break;
		}
		
		
		
	}
}
