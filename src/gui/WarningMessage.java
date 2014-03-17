package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 *Class that creates a warning message pop up window.
 *
 */

public class WarningMessage {
	
	private JFrame frame;
	
	/**
	 *
	 *constructor thats takes inn parameters title and message for the error window
	 * 
	 */
	
	public WarningMessage(String title,String message){
		
		JOptionPane.showMessageDialog(frame,message,title,JOptionPane.WARNING_MESSAGE);
		
	}
	
}
