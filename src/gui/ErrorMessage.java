package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 *Class that creates a error message pop up window.
 *
 */

public class ErrorMessage {
	
	private JFrame frame;
	
	/**
	 *
	 *constructor thats takes inn parameters title and message for the error window
	 * 
	 */
	
	public ErrorMessage(String title,String message){
		
		JOptionPane.showMessageDialog(frame,message,title,JOptionPane.ERROR_MESSAGE);
		
	}
	
}
