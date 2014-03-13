package gui;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateUser extends JPanel {
	private JPanel contentPane;
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(121, 31, 200, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(121, 62, 200, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		mobilenumberField = new JTextField();
		mobilenumberField.setBounds(121, 93, 200, 20);
		contentPane.add(mobilenumberField);
		mobilenumberField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 124, 200, 20);
		contentPane.add(passwordField);
		
		repeatpasswordField = new JPasswordField();
		repeatpasswordField.setBounds(121, 158, 200, 20);
		contentPane.add(repeatpasswordField);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 34, 101, 14);
		contentPane.add(usernameLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(10, 65, 101, 14);
		contentPane.add(emailLabel);
		
		JLabel mobilenumberLabel = new JLabel("Mobile number:");
		mobilenumberLabel.setBounds(10, 96, 101, 14);
		contentPane.add(mobilenumberLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 127, 101, 14);
		contentPane.add(passwordLabel);
		
		JLabel repeatpasswordLabel = new JLabel("Repeat password:");
		repeatpasswordLabel.setBounds(10, 161, 101, 14);
		contentPane.add(repeatpasswordLabel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(39, 206, 93, 29);
		contentPane.add(cancelButton);
		
		confirmButton = new JButton("Confirm");
		confirmButton.setBounds(314, 206, 93, 29);
		contentPane.add(confirmButton);
	}

}
