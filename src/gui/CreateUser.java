package gui;

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
	/**
	 * Create the panel.
	 */
	public CreateUser() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(136, 31, 200, 20);
		add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(136, 62, 200, 20);
		add(emailField);
		emailField.setColumns(10);
		
		mobilenumberField = new JTextField();
		mobilenumberField.setBounds(136, 93, 200, 20);
		add(mobilenumberField);
		mobilenumberField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 127, 200, 20);
		add(passwordField);
		
		repeatpasswordField = new JPasswordField();
		repeatpasswordField.setBounds(136, 158, 200, 20);
		add(repeatpasswordField);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 34, 116, 14);
		add(usernameLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(10, 65, 116, 14);
		add(emailLabel);
		
		JLabel mobilenumberLabel = new JLabel("Mobile number:");
		mobilenumberLabel.setBounds(10, 96, 116, 14);
		add(mobilenumberLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 127, 116, 14);
		add(passwordLabel);
		
		JLabel repeatpasswordLabel = new JLabel("Repeat password:");
		repeatpasswordLabel.setBounds(10, 161, 116, 14);
		add(repeatpasswordLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(39, 206, 93, 29);
		add(cancelButton);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(314, 206, 93, 29);
		add(confirmButton);
	}

}
