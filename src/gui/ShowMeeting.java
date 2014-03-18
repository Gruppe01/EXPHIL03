package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.*;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ShowMeeting extends JPanel {
	private JTable table;

	
	public ShowMeeting(final Frame frame) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 110, 168);
		add(scrollPane);
		
		JLabel lblMeetings = new JLabel("Meetings");
		lblMeetings.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeetings.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setColumnHeaderView(lblMeetings);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(130, 58, 72, 14);
		add(lblDescription);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(212, 52, 123, 69);
		add(textPane);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(130, 138, 46, 14);
		add(lblPlace);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(212, 132, 123, 20);
		add(textPane_1);
		
		JLabel lblStartTime = new JLabel("Start time:");
		lblStartTime.setBounds(130, 169, 72, 14);
		add(lblStartTime);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(212, 163, 123, 20);
		add(textPane_2);
		
		JLabel lblEndTime = new JLabel("End time:");
		lblEndTime.setBounds(130, 200, 72, 14);
		add(lblEndTime);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(212, 194, 123, 20);
		add(textPane_3);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 287, 89, 23);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrame("mainScreen");
			}
		});
		
		JButton btnShowMembers = new JButton("Show members");
		btnShowMembers.setBounds(130, 287, 127, 23);
		add(btnShowMembers);
		btnShowMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowMembers showMembers = new ShowMembers();
				showMembers.setVisible(true);
			}
		});
		
		JCheckBox chckbxShowInCalendar = new JCheckBox("Show in calendar");
		chckbxShowInCalendar.setBounds(263, 287, 110, 23);
		add(chckbxShowInCalendar);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(470, 253, 89, 23);
		add(btnAccept);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrame("mainScreen");
			}
		});
		
		JButton btnDecline = new JButton("Decline");
		btnDecline.setBounds(470, 287, 89, 23);
		add(btnDecline);
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrame("mainScreen");
			}
		});
		

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setText("Choose date here");
		datePicker.setTextEditable(false);
		datePicker.setToolTipText("");
		datePicker.setBounds(357, 54, 137, 23);
		
		add(datePicker);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Time before meeting", "10 min", "15 min", "20 min", "25 min", "30 min"}));
		comboBox.setBounds(212, 225, 123, 20);
		add(comboBox);
		
		JLabel lblCreatAlaram = new JLabel("Create alarm:");
		lblCreatAlaram.setBounds(130, 225, 72, 14);
		add(lblCreatAlaram);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"SMS", "Email", "Both"}));
		comboBox_1.setBounds(345, 225, 57, 20);
		add(comboBox_1);
		
		

	}
}
