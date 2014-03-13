package gui;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Login extends JPanel {
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	/**
	 * Create the panel.
	 */
	public Login() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		usernameField = new JTextField();
		usernameField.setBounds(121, 54, 200, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 89, 200, 20);
		contentPane.add(passwordField);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(22, 56, 89, 17);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(22, 91, 89, 17);
		contentPane.add(passwordLabel);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loginButton.setBounds(121, 120, 89, 23);
		contentPane.add(loginButton);
		
		JButton newuserButton = new JButton("New user");
		newuserButton.setBounds(232, 120, 89, 23);
		contentPane.add(newuserButton);
		
		JButton FYPButton = new JButton("Forgot your password?");
		FYPButton.setBounds(121, 154, 200, 28);
		contentPane.add(FYPButton);
	}

}
