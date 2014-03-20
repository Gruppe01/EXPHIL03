package gui;

import model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateUser extends JPanel {
	private JTextField usernameField;
	private JTextField emailField;
	private JTextField mobilenumberField;
	private JPasswordField passwordField;
	private JPasswordField repeatpasswordField;
	private JButton confirmButton;
	private User user;
	private JTextField nameField;
	private Frame frame;
	/**
	 * Create the panel.
	 */
	public CreateUser(final Frame in) {
		frame = in;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(136, 31, 200, 20);
		add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(136, 93, 200, 20);
		add(emailField);
		emailField.setColumns(10);
		
		mobilenumberField = new JTextField();
		mobilenumberField.setBounds(136, 124, 200, 20);
		add(mobilenumberField);
		mobilenumberField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 158, 200, 20);
		add(passwordField);
		
		repeatpasswordField = new JPasswordField();
		repeatpasswordField.setBounds(136, 189, 200, 20);
		add(repeatpasswordField);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 34, 116, 14);
		add(usernameLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(10, 96, 116, 14);
		add(emailLabel);
		
		JLabel mobilenumberLabel = new JLabel("Mobile number:");
		mobilenumberLabel.setBounds(10, 127, 116, 14);
		add(mobilenumberLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 158, 116, 14);
		add(passwordLabel);
		
		JLabel repeatpasswordLabel = new JLabel("Repeat password:");
		repeatpasswordLabel.setBounds(10, 192, 116, 14);
		add(repeatpasswordLabel);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 65, 116, 14);
		add(lblName);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(136, 62, 200, 20);
		add(nameField);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(39, 226, 93, 29);
		add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setFrame("login");
			}
		});
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(315, 226, 93, 29);
		add(confirmButton);
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				create();
			}
		});
	}
	
	private void create(){
		String username;
		String password;
		String conPassword;
		String name;
		String email;
		String phonenumber;
		
		username = usernameField.getText();
		password = String.valueOf(passwordField.getPassword());
		conPassword = String.valueOf(repeatpasswordField.getPassword());
		name = nameField.getText();
		email = emailField.getText();
		phonenumber = mobilenumberField.getText();
		
		if (!password.equals(conPassword)){
			ErrorMessage error = new ErrorMessage("Error", "Password and Repeat password Don't match");
			return;
		}
		
		try{
		    user = new User(username, password, name, email, phonenumber);

            ArrayList<Object> changedObject = new ArrayList<>(); changedObject.add(user);

            Frame.client.sendChanges(changedObject, "insert");
		}catch(IllegalArgumentException e){
			ErrorMessage error = new ErrorMessage("Error", e.getMessage());
			return;
		}
		
		frame.createUser(user);
		frame.setFrame("login");
	}
}
