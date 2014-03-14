package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;

public class ShowMembers extends JPanel {

	/**
	 * Create the panel.
	 */
	public ShowMembers() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 26, 321, 220);
		add(scrollPane);
		
		JLabel lblMeetingmembers = new JLabel("Meetingmembers");
		lblMeetingmembers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMeetingmembers.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblMeetingmembers);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(10, 266, 89, 23);
		add(btnNewButton);

	}
}
