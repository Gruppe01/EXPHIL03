package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddExternal extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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
		
		textField = new JTextField();
		textField.setBounds(95, 17, 158, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(95, 48, 158, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(95, 79, 158, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 110, 89, 23);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(222, 110, 89, 23);
		contentPane.add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		setContentPane(contentPane);

	}

}
