package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
	
	public Main(){
		
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("...");
		frame.getContentPane().add(new Main());
		frame.pack();
		frame.setVisible(true);
	}
}
