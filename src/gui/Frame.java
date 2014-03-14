package gui;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JPanel{
	
	private JFrame frame;
	private Login login;
	
	public Frame(){
		login = new Login();
		this.setLayout(new BorderLayout());
		frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		setFrame("login");
	}
	
	public void setFrame(String panel){
		
		switch(panel){
			case "login":
				frame.setContentPane(login);
				frame.setSize(425,300);
				break;
		}
		
		
		
	}
}
