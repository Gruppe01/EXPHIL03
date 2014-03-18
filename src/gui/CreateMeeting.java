package gui;

import model.Meeting;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JTextField textField;
	private CreateMeeting working;
	private Frame frame;

	/**
	 * Create the panel.
	 */
	public CreateMeeting(final Frame in) {
		
		frame = in;
		working = this;
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
		textField_3.setBounds(449, 225, 125, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 66, 101, 14);
		add(lblDescription);
		
		lblSelectTimeStart = new JLabel("Select time start:");
		lblSelectTimeStart.setBounds(10, 176, 101, 14);
		add(lblSelectTimeStart);
		
		lblSelectTimeEnd = new JLabel("Select time end:");
		lblSelectTimeEnd.setBounds(10, 207, 101, 14);
		add(lblSelectTimeEnd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 287, 125, 31);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrame("mainScreen");
			}
		});
		
		btnEditMembers = new JButton("Edit Members");
		btnEditMembers.setBounds(239, 287, 113, 31);
		add(btnEditMembers);
		btnEditMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditMembers edit = new EditMembers(working);
				edit.setVisible(true);
			}
		});
		
		btnCreateMeeting = new JButton("Create meeting");
		btnCreateMeeting.setBounds(449, 287, 125, 31);
		add(btnCreateMeeting);
		btnCreateMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		lblParticipants = new JLabel("Participants:");
		lblParticipants.setBounds(353, 228, 86, 14);
		add(lblParticipants);
		
		label = new JLabel(":");
		label.setBounds(190, 176, 15, 14);
		add(label);
		
		label_1 = new JLabel(":");
		label_1.setBounds(190, 207, 15, 14);
		add(label_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(121, 173, 59, 20);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(121, 204, 59, 20);
		add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(200, 173, 59, 20);
		add(spinner_2);
		
		spinner_3 = new JSpinner();
		spinner_3.setBounds(200, 204, 59, 20);
		add(spinner_3);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(121, 31, 143, 103);
		add(textPane);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setText("Choose date here");
		datePicker.setTextEditable(false);
		datePicker.setToolTipText("");
		datePicker.setBounds(274, 31, 145, 23);
		add(datePicker);
		
		JLabel lblPlace = new JLabel("Place: ");
		lblPlace.setBounds(10, 151, 46, 14);
		add(lblPlace);
		
		textField = new JTextField();
		textField.setBounds(121, 145, 138, 20);
		add(textField);
		textField.setColumns(10);
		
//		scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(274, 103, 132, 86);
//		add(scrollPane_1);
	}
	
	private void create(){
		
		
		
		frame.setFrame("mainScreen");
	}
}
