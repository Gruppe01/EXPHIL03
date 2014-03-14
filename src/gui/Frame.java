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
		frame.setContentPane(login);
		frame.pack();
		frame.setVisible(true);
		setFrame(425,300);
	}
	
	public void setFrame(int width,int hight){
		frame.setSize(width, hight);
	}
}
