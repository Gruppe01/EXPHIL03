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
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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
		scrollPane.setBounds(10, 35, 256, 296);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Newsfeed");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblNewLabel);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(276, 35, 369, 129);
		contentPane.add(scrollPane_1);
		
		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotifications.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1.setColumnHeaderView(lblNotifications);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JButton btnEditShow = new JButton("Edit / Show meeting");
		btnEditShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnEditShow.setBounds(276, 175, 136, 45);
		contentPane.add(btnEditShow);
		
		JButton btnShowCalendar = new JButton("Show week calendar");
		btnShowCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnShowCalendar.setBounds(276, 231, 136, 45);
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
		
		JButton btnNewMeeting_1 = new JButton("New meeting");
		btnNewMeeting_1.setBounds(276, 287, 136, 44);
		contentPane.add(btnNewMeeting_1);
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.setBounds(556, 299, 89, 32);
		contentPane.add(btnNewButton);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(556, 11, 89, 14);
		contentPane.add(lblUsername);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setText("Choose date here");
		datePicker.setTextEditable(false);
		datePicker.setToolTipText("");
		datePicker.setBounds(426, 175, 146, 23);

		contentPane.add(datePicker);
	}
}
