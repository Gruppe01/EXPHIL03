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

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

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
	private JScrollPane scrollPane_1;

	/**
	 * Create the panel.
	 */
	public CreateMeeting() {
		setBounds(100, 100, 584, 347);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(449, 31, 125, 183);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblAvail = new JLabel("Available rooms");
		lblAvail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvail.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblAvail);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(449, 225, 125, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 66, 101, 14);
		add(lblDescription);
		
		lblSelectTimeStart = new JLabel("Select time start:");
		lblSelectTimeStart.setBounds(10, 140, 101, 14);
		add(lblSelectTimeStart);
		
		lblSelectTimeEnd = new JLabel("Select time end:");
		lblSelectTimeEnd.setBounds(10, 176, 101, 14);
		add(lblSelectTimeEnd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 287, 113, 31);
		add(btnCancel);
		
		btnEditMembers = new JButton("Edit Members");
		btnEditMembers.setBounds(232, 280, 113, 45);
		add(btnEditMembers);
		
		btnCreateMeeting = new JButton("Create meeting");
		btnCreateMeeting.setBounds(461, 287, 113, 31);
		add(btnCreateMeeting);
		
		lblParticipants = new JLabel("Participants:");
		lblParticipants.setBounds(353, 228, 86, 14);
		add(lblParticipants);
		
		label = new JLabel(":");
		label.setBounds(190, 140, 15, 14);
		add(label);
		
		label_1 = new JLabel(":");
		label_1.setBounds(190, 176, 15, 14);
		add(label_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(121, 137, 59, 20);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(121, 173, 59, 20);
		add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(200, 137, 59, 20);
		add(spinner_2);
		
		spinner_3 = new JSpinner();
		spinner_3.setBounds(200, 173, 59, 20);
		add(spinner_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(121, 31, 143, 86);
		add(textPane);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setText("Choose date here");
		datePicker.setTextEditable(false);
		datePicker.setToolTipText("");
		datePicker.setBounds(274, 31, 145, 23);

		add(datePicker);
		
//		scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(274, 103, 132, 86);
//		add(scrollPane_1);
	}
}
