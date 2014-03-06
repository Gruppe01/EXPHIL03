package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JPanel {

	private JPanel contentPane;

	

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		//setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 321, 376);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(339, 11, 321, 153);
		contentPane.add(scrollPane_1);
		
		JButton btnEditShow = new JButton("Edit / Show meeting");
		btnEditShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEditShow.setBounds(341, 230, 136, 45);
		contentPane.add(btnEditShow);
		
		JButton btnShowCalendar = new JButton("Show week calendar");
		btnShowCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowCalendar.setBounds(341, 286, 136, 45);
		contentPane.add(btnShowCalendar);
		
		JButton btnNewMeeting = new JButton("New Meeting");
		btnNewMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewMeeting.setBounds(341, 342, 136, 45);
		contentPane.add(btnNewMeeting);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogout.setBounds(524, 342, 136, 45);
		contentPane.add(btnLogout);
	}
}
