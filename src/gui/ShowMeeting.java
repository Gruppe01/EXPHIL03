package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.*;

import model.MeetingInvite;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class ShowMeeting extends JPanel {
	private JTable table;
	private model.Meeting meeting;
	private JTextPane textPane;
	private JTextPane textPane_1;
	private JTextPane textPane_2;
	private JTextPane textPane_3;
	private JTextPane textPane_4;
    private JComboBox comboBox;
    private JScrollPane scrollPane;
    private JList listMeetingMembers;
    private DefaultListModel listMeetingMembersModel;
	
	public void setMeeting(model.Meeting m){
		this.meeting = m;
		textPane.setText(meeting.getDescription());
		textPane_1.setText(meeting.getPlace());
		textPane_2.setText(meeting.getStartTimeAsLocalDateTime().toLocalTime().toString());
		textPane_3.setText(meeting.getEndTimeAsLocalDateTime().toLocalTime().toString());
		textPane_4.setText(meeting.getStartTimeAsLocalDateTime().toLocalDate().toString());
		
		listMeetingMembersModel = new DefaultListModel();
		ArrayList<MeetingInvite> meetingMembers =  Frame.getDataStorage().getMeetingMembers(meeting.getMeetingID());
		
		for (MeetingInvite mI : meetingMembers){
			listMeetingMembersModel.addElement(mI.getUsername());
		}
		
		listMeetingMembers = new JList(listMeetingMembersModel);
		scrollPane.setViewportView(listMeetingMembers);
		
		textPane.setEditable(false);
		textPane_1.setEditable(false);
		textPane_2.setEditable(false);
		textPane_3.setEditable(false);
		textPane_4.setEditable(false);
	}

	public ShowMeeting(final Frame frame) {
		setLayout(null);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 11, 72, 14);
		add(lblDescription);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(110, 11, 147, 69);
		add(textPane);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(10, 97, 46, 14);
		add(lblPlace);
		
		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(110, 91, 147, 20);
		add(textPane_1);
		
		JLabel lblStartTime = new JLabel("Start time:");
		lblStartTime.setBounds(10, 128, 72, 14);
		add(lblStartTime);
		
		textPane_2 = new JTextPane();
		textPane_2.setBounds(110, 122, 147, 20);
		add(textPane_2);
		
		JLabel lblEndTime = new JLabel("End time:");
		lblEndTime.setBounds(10, 159, 72, 14);
		add(lblEndTime);
		
		textPane_3 = new JTextPane();
		textPane_3.setBounds(110, 153, 147, 20);
		add(textPane_3);
		
		
		JButton btnCancel = new JButton("Back");
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
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(480, 268, 89, 42);
		add(btnAccept);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String comboboxValue = comboBox.getSelectedItem().toString();
                String alarmTime;

                if(comboboxValue.equals("Time before meeting")) alarmTime = null;
                else{
                    int minutesBefore = Integer.parseInt(comboboxValue.substring(0, comboboxValue.indexOf(" ")));
                    alarmTime = meeting.getStartTimeAsLocalDateTime().minusMinutes(minutesBefore).toString();
                }

                Frame.getClient().getDataStorage().updateMeetingInviteByUsernameAndMeetingID(meeting.getMeetingID(), Frame.getUserName(), true, alarmTime);

                frame.setFrame("mainScreen");
			}
		});
		
		JButton btnDecline = new JButton("Decline");
		btnDecline.setBounds(381, 268, 89, 42);
		add(btnDecline);
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Frame.getClient().getDataStorage().updateMeetingInviteByUsernameAndMeetingID(meeting.getMeetingID(), Frame.getUserName(), false, null);

				frame.setFrame("mainScreen");
			}
		});
		

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Time before meeting", "10 min", "15 min", "20 min", "25 min", "30 min"}));
		comboBox.setBounds(110, 184, 147, 20);
		add(comboBox);
		
		JLabel lblCreatAlaram = new JLabel("Create alarm:");
		lblCreatAlaram.setBounds(10, 190, 89, 14);
		add(lblCreatAlaram);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(267, 11, 46, 14);
		add(lblDate);
		
		textPane_4 = new JTextPane();
		textPane_4.setEditable(false);
		textPane_4.setBounds(312, 5, 123, 20);
		add(textPane_4);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 42, 302, 162);
		add(scrollPane);
		
		JLabel lblMeetingMembers = new JLabel("Meeting members");
		lblMeetingMembers.setHorizontalAlignment(SwingConstants.CENTER);
		lblMeetingMembers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setColumnHeaderView(lblMeetingMembers);
		
		
		

	}
	
	public void refresh(){
		this.revalidate();
		this.repaint();
	}
}
