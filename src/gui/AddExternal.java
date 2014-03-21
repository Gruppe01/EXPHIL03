package gui;

import model.ExternalUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.sun.glass.events.WindowEvent;

public class AddExternal extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private EditMembers editMembers;

	/**
	 * Create the panel.
	 */
	public AddExternal(final EditMembers in) {
		
		editMembers = in;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(349,178);
		contentPane = new JPanel();
		
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setBounds(39, 20, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(39, 51, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(39, 82, 46, 14);
		contentPane.add(lblPhone);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(95, 17, 158, 20);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(95, 48, 158, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldPhone = new JTextField();
		textFieldPhone.setBounds(95, 79, 158, 20);
		contentPane.add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 110, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				editMembers.enableExternalButton();
				dispose();
			}
		});
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(222, 110, 89, 23);
		contentPane.add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				create();
				editMembers.enableExternalButton();
				dispose();
			}
		});
		
		setResizable(false);
		setContentPane(contentPane);
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	editMembers.enableExternalButton();
            }
        });

	}
	
	public void create(){
		
		ExternalUser user = new ExternalUser(textFieldEmail.getText(), textFieldName.getText(), textFieldPhone.getText());
		
		editMembers.addExternal(user);
	}

}
