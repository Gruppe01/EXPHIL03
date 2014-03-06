package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class CreateMeeting extends JPanel {
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTextField textField_3;
	private JLabel lblDescription;
	private JLabel lblSelectTimeStart;
	private JLabel lblSelectTimeEnd;
	private JButton btnCancel;
	private JButton btnEditMembers;
	private JButton btnCreateMeeting;
	private JLabel lblParticipants;
	private JLabel label;
	private JLabel label_1;
	private JSpinner spinner_3;

	/**
	 * Create the panel.
	 */
	public CreateMeeting() {
		setBounds(100, 100, 680, 426);
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(195, 118, 145, 20);
		add(textField);
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(519, 51, 125, 183);
		add(scrollPane);
		
		textField_3 = new JTextField();
		textField_3.setBounds(519, 258, 125, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(65, 121, 101, 14);
		add(lblDescription);
		
		lblSelectTimeStart = new JLabel("Select time start:");
		lblSelectTimeStart.setBounds(65, 152, 101, 14);
		add(lblSelectTimeStart);
		
		lblSelectTimeEnd = new JLabel("Select time end:");
		lblSelectTimeEnd.setBounds(65, 183, 101, 14);
		add(lblSelectTimeEnd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 370, 113, 45);
		add(btnCancel);
		
		btnEditMembers = new JButton("Edit Members");
		btnEditMembers.setBounds(281, 370, 113, 45);
		add(btnEditMembers);
		
		btnCreateMeeting = new JButton("Create meeting");
		btnCreateMeeting.setBounds(557, 370, 113, 45);
		add(btnCreateMeeting);
		
		lblParticipants = new JLabel("Participants:");
		lblParticipants.setBounds(446, 261, 86, 14);
		add(lblParticipants);
		
		label = new JLabel(":");
		label.setBounds(264, 152, 15, 14);
		add(label);
		
		label_1 = new JLabel(":");
		label_1.setBounds(264, 183, 15, 14);
		add(label_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(195, 149, 59, 20);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(195, 180, 59, 20);
		add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(281, 149, 59, 20);
		add(spinner_2);
		
		spinner_3 = new JSpinner();
		spinner_3.setBounds(281, 180, 59, 20);
		add(spinner_3);
	}
}
