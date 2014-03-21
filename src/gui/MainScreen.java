package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.paint.Color;
import persistence.data.*;
import model.*;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class MainScreen extends JPanel {
	private LocalDate date;
	private JLabel lblUsername;
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	private String pickedDate;
	private JList meetingslist;
	private DefaultListModel meetingListModel;
	private JList notifications;
	private DefaultListModel notiflistModel;
	private ArrayList<Integer> notifmeetings = new ArrayList<Integer>();
	private model.Meeting meeting;
	private ArrayList<Integer> meetingIDList;
    private ButtonGroup group;


	/**
	 * Create the frame.
	 */
	public MainScreen(final Frame frame) {
		//setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 426);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 256, 296);
		add(scrollPane);

		JLabel lblNewLabel = new JLabel("Newsfeed");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblNewLabel);

		meetingListModel = new DefaultListModel();
		meetingslist = new JList(meetingListModel);
		scrollPane.setViewportView(meetingslist);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(276, 35, 369, 129);
		add(scrollPane_1);

		JLabel lblNotifications = new JLabel("Notifications");
		lblNotifications.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotifications.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane_1.setColumnHeaderView(lblNotifications);

		notiflistModel = new DefaultListModel<>();
		notifications = new JList(notiflistModel);
		scrollPane_1.setViewportView(notifications);
        group = new ButtonGroup();

		meetingIDList = new ArrayList<Integer>();

		JButton btnEditShow = new JButton("Edit / Show meeting");
        btnEditShow.setBounds(276, 175, 170, 45);
		add(btnEditShow);
        btnEditShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(meetingslist.getSelectedIndex() > -1){
					meeting = Frame.getClient().getDataStorage().meetings().getMeetingByID(meetingIDList.get(meetingslist.getSelectedIndex()));
					MeetingAdmin admin = Frame.getDataStorage().meetingAdmins().getMeetingAdminByUsernameAndMeetingID(meeting.getMeetingID(), Frame.getUserName());
					if (admin != null){
						frame.getEditMeeting().setMeeting(meeting);
						frame.setFrame("editMeeting");	
					}else{
						frame.getShowMeeting().setMeeting(meeting);
						frame.setFrame("showMeeting");	
					}
				}
			}
		});

		JButton btnShowCalendar = new JButton("Show week calendar");
		btnShowCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrame("weekCalendar");
				frame.getWeekCalendar().setCollegues();
			}
		});
		btnShowCalendar.setBounds(276, 231, 170, 45);
		add(btnShowCalendar);

		JButton btnNewMeeting = new JButton("New meeting");
		btnNewMeeting.setBounds(276, 287, 170, 44);
		add(btnNewMeeting);
		btnNewMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setFrame("createMeeting");
			}
		});

		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(556, 299, 89, 32);
		add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.logout();
				frame.setFrame("login");
			}
		});

		lblUsername = new JLabel("");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setBounds(556, 11, 89, 14);
		add(lblUsername);

		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setText("Choose date here");
		datePicker.setTextEditable(false);
		datePicker.setToolTipText("");
		datePicker.setBounds(499, 175, 146, 23);
		datePanel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setDatePicked();
			}

		});

        pickedDate = LocalDate.now().toString();

		add(datePicker);
	}

	public void refresh(){
		this.revalidate();
		this.repaint();
	}

	public void setDatePicked(){
		int thismonth = model.getMonth();
		String month = (thismonth+1)<10 ? "0" + (thismonth+1) : "" + (thismonth+1);
		String day = model.getDay()<10 ? "0" + model.getDay() : "" + model.getDay();

		pickedDate = model.getYear() + "-" + month + "-" + day;
		setNewsfeed();
	}

    @SuppressWarnings("unchecked")
	public void setNewsfeed(){
        meetingListModel.removeAllElements();
        meetingIDList.clear();

        ArrayList<MeetingInvite> meetings = Frame.getClient().getDataStorage().getMeetingInvitesByUsernameAndDate(Frame.getUserName(), LocalDate.parse(pickedDate));

        for(MeetingInvite meetingInvite : meetings){
            Meeting meeting = Frame.getClient().getDataStorage().meetings().getMeetingByID(meetingInvite.getMeetingID());

            meetingListModel.addElement(meeting.getDescription());
            meetingIDList.add(meeting.getMeetingID());
        }
	}

    @SuppressWarnings("unchecked")
	public void setNotifications(){
        notiflistModel.removeAllElements();
        notifmeetings.clear();

        ArrayList<Meeting> notif = Frame.getClient().getDataStorage().getMeetingNotificationsByUsername(Frame.getUserName());

        for(Meeting meeting : notif){
            if(!notifmeetings.contains(meeting.getMeetingID())){
                notiflistModel.addElement(meeting.getDescription() + " has been updated.");
                notifmeetings.add(meeting.getMeetingID());
            }
        }
	}

	public void setUser(String in){
		lblUsername.setText(in);
	}
}
