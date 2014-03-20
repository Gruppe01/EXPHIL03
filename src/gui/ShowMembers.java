package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JButton;

public class ShowMembers extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Create the panel.
	 */
	public ShowMembers() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(425,335);
		contentPane = new JPanel();
		contentPane.setBounds(100, 100, 450, 300);
		
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 26, 321, 220);
		contentPane.add(scrollPane);
		
		JLabel lblMeetingmembers = new JLabel("Meetingmembers");
		lblMeetingmembers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMeetingmembers.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblMeetingmembers);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(10, 266, 89, 23);
		contentPane.add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		setResizable(false);
		setContentPane(contentPane);
		
	}
}
