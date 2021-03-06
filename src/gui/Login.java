package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
	private JTextField usernameField;
	private JPasswordField passwordField;
	private Frame frame;
	/**
	 * Create the panel.
	 */
	public Login(final Frame in) {
		frame = in;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(121, 54, 200, 20);
		add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 89, 200, 20);
		add(passwordField);
		passwordField.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		    	login();
		    }
		});
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(22, 56, 89, 17);
		add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(22, 91, 89, 17);
		add(passwordLabel);
		
		JButton loginButton = new JButton("Log in");
		loginButton.setBounds(121, 120, 89, 23);
		add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		
		JButton newuserButton = new JButton("New user");
		newuserButton.setBounds(232, 120, 89, 23);
		add(newuserButton);
		newuserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setFrame("createUser");
			}
		});
		
		JButton FYPButton = new JButton("Forgot your password?");
		FYPButton.setBounds(121, 154, 200, 28);
		add(FYPButton);
	}
	
	public void refresh(){
		usernameField.setText("");
		passwordField.setText("");
		this.revalidate();
		this.repaint();
	}
	
	private void login(){
		String username;
		String password;
		
		username = usernameField.getText();
		password = String.valueOf(passwordField.getPassword());

        try {
            if (!Frame.getClient().getDataStorage().users().checkLogin(username, password)){
                new ErrorMessage("Error", "Invalid login!");
                return;
            }
            
            frame.setUser(username);
            frame.setFrame("mainScreen");
            frame.getMainScreen().setNotifications();
        }catch (IllegalArgumentException e){
            new ErrorMessage("Error", e.getMessage());
            return;
        }
	}
}
