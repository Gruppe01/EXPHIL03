package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CreateMeeting extends JPanel {
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
		setBounds(100, 100, 584, 394);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(429, 84, 125, 183);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblAvail = new JLabel("Available rooms");
		lblAvail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvail.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblAvail);
		
		textField_3 = new JTextField();
		textField_3.setBounds(429, 278, 125, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 143, 101, 14);
		add(lblDescription);
		
		lblSelectTimeStart = new JLabel("Select time start:");
		lblSelectTimeStart.setBounds(10, 213, 101, 14);
		add(lblSelectTimeStart);
		
		lblSelectTimeEnd = new JLabel("Select time end:");
		lblSelectTimeEnd.setBounds(10, 253, 101, 14);
		add(lblSelectTimeEnd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 333, 113, 45);
		add(btnCancel);
		
		btnEditMembers = new JButton("Edit Members");
		btnEditMembers.setBounds(236, 333, 113, 45);
		add(btnEditMembers);
		
		btnCreateMeeting = new JButton("Create meeting");
		btnCreateMeeting.setBounds(461, 333, 113, 45);
		add(btnCreateMeeting);
		
		lblParticipants = new JLabel("Participants:");
		lblParticipants.setBounds(331, 281, 86, 14);
		add(lblParticipants);
		
		label = new JLabel(":");
		label.setBounds(190, 213, 15, 14);
		add(label);
		
		label_1 = new JLabel(":");
		label_1.setBounds(190, 250, 15, 14);
		add(label_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(121, 210, 59, 20);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(121, 247, 59, 20);
		add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(200, 210, 59, 20);
		add(spinner_2);
		
		spinner_3 = new JSpinner();
		spinner_3.setBounds(200, 247, 59, 20);
		add(spinner_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(121, 103, 143, 86);
		add(textPane);
	}
}
