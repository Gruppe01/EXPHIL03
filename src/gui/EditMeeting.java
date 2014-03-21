package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import model.ExternalUser;
import model.Meeting;
import model.MeetingAdmin;
import model.MeetingInvite;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditMeeting extends JPanel {
	private JTextPane descriptionTextField;
	private JTextPane placeTextField;
	private UtilDateModel model;
	private DefaultListModel listModel;
	private model.Meeting meeting;
	private JList list_1;
	private JSpinner starth;
	private JSpinner startm;
	private JSpinner endh;
	private JSpinner endm;
	private JSpinner participantsSpinner;
	private JButton btnEditMembers;
	private Frame frame;
	private EditMeeting working;
	private int meetingID;
	private int room;
	private ArrayList<String> members = new ArrayList<>();
	private ArrayList<String> admin = new ArrayList<>();
	private ArrayList<ExternalUser> externalUsers = new ArrayList<>();
	
	public void setMeeting(model.Meeting m){
		this.meeting = m;
		String startTime = meeting.getStartTimeAsLocalDateTime().toString();
		String endTime = meeting.getEndTimeAsLocalDateTime().toString();
		String[] startSplit = startTime.split("T");
		String[] endSplit = endTime.split("T");
		String[] startClock = startSplit[1].split(":");
		String[] endClock = endSplit[1].split(":");
		String[] date = startSplit[0].split("-");
		String startHour = startClock[0];
		String endHour = endClock[0];
		String startMinute = startClock[1];
		String endMinute = endClock[1];
		String year = date[0];
		String month = date[1];
		String day = date[2];
		String place = meeting.getPlace();
		String description = meeting.getDescription();
		String participants = String.valueOf(meeting.getMinCapacity());
		meetingID = meeting.getMeetingID();
		room = meeting.getRoom();
		descriptionTextField.setText(description);
		placeTextField.setText(place);
		starth.setValue(Integer.parseInt(startHour));
		endh.setValue(Integer.parseInt(endHour));
		startm.setValue(Integer.parseInt(startMinute));
		endm.setValue(Integer.parseInt(endMinute));
		model.setDate(Integer.parseInt(year), Integer.parseInt(month)-1, Integer.parseInt(day));
		
		roomLoader();
		
	}

	/**
	 * Create the panel.
	 */
	public EditMeeting(final Frame in) {
		frame = in;
		working = this;
		listModel = new DefaultListModel<>();
		setLayout(null);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(10, 52, 72, 14);
		add(lblDescription);
		
		descriptionTextField = new JTextPane();
		descriptionTextField.setBounds(116, 52, 142, 69);
		add(descriptionTextField);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(10, 138, 46, 14);
		add(lblPlace);
		
		placeTextField = new JTextPane();
		placeTextField.setBounds(116, 132, 142, 20);
		add(placeTextField);
		
		JLabel lblStartTime = new JLabel("Start time:");
		lblStartTime.setBounds(10, 169, 72, 14);
		add(lblStartTime);
		
		JLabel lblEndTime = new JLabel("End time:");
		lblEndTime.setBounds(10, 200, 72, 14);
		add(lblEndTime);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 287, 110, 23);
		add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrame("mainScreen");
			}
		});
		
		btnEditMembers = new JButton("Edit members");
		btnEditMembers.setBounds(158, 287, 148, 23);
		add(btnEditMembers);
		btnEditMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditMembers edit = new EditMembers(null,working);
				edit.setVisible(true);
				btnEditMembers.setEnabled(false);
			}
		});
		
		JButton btnAccept = new JButton("Update meeting");
		btnAccept.setBounds(512, 287, 137, 23);
		add(btnAccept);
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateMeeting();
			}
		});
		
		JButton btnDelete = new JButton("Delete meeting");
		btnDelete.setBounds(347, 287, 127, 23);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.getClient().sendChanges(meeting, "delete");
				frame.setFrame("mainScreen");
			}
		});
		

		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setText("Choose date");
		datePicker.setTextEditable(false);
		datePicker.setToolTipText("");
		datePicker.setBounds(270, 52, 244, 23);
		add(datePicker);
		model.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				roomLoader();
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Time before meeting", "5 min", "10 min", "15 min", "20 min", "30 min"}));
		comboBox.setBounds(116, 225, 142, 20);
		add(comboBox);
		
		JLabel lblCreatAlaram = new JLabel("Create alarm:");
		lblCreatAlaram.setBounds(10, 228, 96, 14);
		add(lblCreatAlaram);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(526, 49, 123, 168);
		add(scrollPane_1);
		
		list_1 = new JList(listModel);
		scrollPane_1.setViewportView(list_1);
		
		JButton btnClear = new JButton("Clear Selection");
		btnClear.setBounds(524, 223, 125, 25);
		add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list_1.clearSelection();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Available rooms");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblNewLabel);
		
		JLabel lblParticipants = new JLabel("Participants:");
		lblParticipants.setBounds(442, 256, 72, 14);
		add(lblParticipants);
		
		starth = new JSpinner();
		starth.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		starth.setBounds(116, 165, 59, 22);
		add(starth);
		starth.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();

			}
		});
		
		startm = new JSpinner();
		startm.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		startm.setBounds(199, 165, 59, 22);
		add(startm);
		startm.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();

			}
		});
		
		endh = new JSpinner();
		endh.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		endh.setBounds(116, 196, 59, 22);
		add(endh);
		endh.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();

			}
		});
		
		endm = new JSpinner();
		endm.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		endm.setBounds(199, 196, 59, 22);
		add(endm);
		endm.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();

			}
		});
		
		JLabel label = new JLabel(":");
		label.setBounds(187, 168, 56, 16);
		add(label);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(187, 199, 56, 16);
		add(label_1);
		
		participantsSpinner = new JSpinner();
		participantsSpinner.setBounds(526, 252, 123, 22);
		add(participantsSpinner);
		participantsSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();

			}
		});
	}
	
	private void roomLoader(){

		listModel.removeAllElements();

		int capacity;
		String startTime;
		String endTime;

		String month = model.getMonth()+1<10 ? "0" + (model.getMonth()+1) : "" + (model.getMonth()+1);
		String day = model.getDay()<10 ? "0" + model.getDay() : "" + model.getDay();
		String startH = (Integer)starth.getValue() < 10 ? "0" + (Integer)starth.getValue() : "" + (Integer)starth.getValue();
		String endH = (Integer)endh.getValue() < 10 ? "0" + (Integer)endh.getValue() : "" + (Integer)endh.getValue();
		String startM = (Integer)startm.getValue() < 10 ? "0" + (Integer)startm.getValue() : "" + (Integer)startm.getValue();
		String endM = (Integer)endm.getValue() < 10 ? "0" + (Integer)endm.getValue() : "" + (Integer)endm.getValue();

		startTime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+startH+":"+startM+":00";
		endTime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+endH+":"+endM+":00";

		capacity = (Integer)participantsSpinner.getValue();

		ArrayList<Integer> room = Frame.getClient().getDataStorage().getAvailableRooms(startTime, endTime, capacity);
		
		//listModel.addElement("Room"+room);
		int roomCnt = -1;
		for (int i:room){
			roomCnt++;
			listModel.addElement("Room " + i);
			if(("Room " + i).equals(meeting.getPlace())){
				list_1.setSelectedIndex(roomCnt);
			}
		}
	}
	
	public int getMeetingID(){
		return meetingID;
	}
	
	public void setAdmins(ArrayList<String> in){
		admin = in;
	}

	public void setMembers(ArrayList<String> in){
		members = in;
	}
	
	public void setExternalUsers(ArrayList<ExternalUser> in){
		externalUsers = in;
	}

	public void enableEditButton(){
		btnEditMembers.setEnabled(true);
	}
	
	private void updateMeeting(){

		String starttime;
		String endtime;
		int capacity;
		String description;
		String place;
		String roomS;
		int room;

		String month = model.getMonth()+1<10 ? "0" + (model.getMonth()+1) : "" + (model.getMonth()+1);
		String day = model.getDay()<10 ? "0" + model.getDay() : "" + model.getDay();
		String startH = (Integer)starth.getValue() < 10 ? "0" + (Integer)starth.getValue() : "" + (Integer)starth.getValue();
		String endH = (Integer)endh.getValue() < 10 ? "0" + (Integer)endh.getValue() : "" + (Integer)endh.getValue();
		String startM = (Integer)startm.getValue() < 10 ? "0" + (Integer)startm.getValue() : "" + (Integer)startm.getValue();
		String endM = (Integer)endm.getValue() < 10 ? "0" + (Integer)endm.getValue() : "" + (Integer)endm.getValue();

		description = descriptionTextField.getText();
		starttime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+startH+":"+startM+":00";
		endtime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+endH+":"+endM+":00";
		capacity = (Integer) participantsSpinner.getValue();
		place = placeTextField.getText();

		if (list_1.isSelectionEmpty()){
            if(place.equals("")){
                new ErrorMessage("Error", "You must either choose place or room");
                return;
            }

			meeting = new Meeting(meetingID, starttime, endtime, description, place, -1, -1, Frame.getUserName(), null);
		}else{
            roomS = list_1.getSelectedValue().toString();
			room = Integer.parseInt(roomS.substring(roomS.indexOf(" ") + 1));

			meeting = new Meeting(meetingID, starttime, endtime, description, place, room, capacity, Frame.getUserName(), null);
		}

		Frame.getDataStorage().updateMeetingByMeeting(meeting);

        ArrayList<String> currentAdmins = Frame.getDataStorage().getMeetingAdminsByMeetingID(meetingID);
        ArrayList<String> currentMembers = Frame.getDataStorage().meetingInvites().getMeetingMembersByMeetingID(meetingID);
        ArrayList<String> currentExternalMembers = Frame.getDataStorage().externalUsers().getExternalUsersByMeetingID(meetingID);

        ArrayList<Object> tempAdminAdd = new ArrayList<>();
        ArrayList<Object> tempAdminRemove = new ArrayList<>();
        ArrayList<Object> tempMembersAdd = new ArrayList<>();
        ArrayList<Object> tempMembersRemove = new ArrayList<>();
        ArrayList<Object> tempExternalMembersAdd = new ArrayList<>();
        ArrayList<Object> tempExternalMembersRemove = new ArrayList<>();

		for (String i:admin){
			if(!currentAdmins.contains(i)) tempAdminAdd.add(new MeetingAdmin(meetingID, i));
			members.add(i);
		}

		for (String i:members){
            if(!currentMembers.contains(i)) tempMembersAdd.add(new MeetingInvite(meetingID, i));
		}
		
		for (ExternalUser i: externalUsers){
            if(!currentExternalMembers.contains(i.getEmail())) tempExternalMembersAdd.add(new ExternalUser(i.getEmail(), meetingID, i.getName(), i.getPhoneNumber()));
		}

		if(admin.size() > 0){
            for(String currentAdmin : currentAdmins){
                if(!admin.contains(currentAdmin)) tempAdminRemove.add(new MeetingAdmin(meetingID, currentAdmin));
            }

            if(tempAdminRemove.size() > 0) Frame.getClient().sendChanges(tempAdminRemove, "delete");
            Frame.getClient().sendChanges(tempAdminAdd, "insert");
        }

        if(members.size() > 0){
            for(String currentMember : currentMembers){
                if(!members.contains(currentMember)) tempMembersRemove.add(new MeetingInvite(meetingID,currentMember));
            }

            if(tempMembersRemove.size() > 0) Frame.getClient().sendChanges(tempMembersRemove, "delete");
            Frame.getClient().sendChanges(tempMembersAdd, "insert");
        }

        if(externalUsers.size() > 0){
            for(String currentExternalMember : currentExternalMembers){
                if(!externalUsers.contains(Frame.getDataStorage().externalUsers().getExternalUserByEmailAndMeetingID(meetingID, currentExternalMember))) tempExternalMembersRemove.add(new ExternalUser(meetingID, currentExternalMember));
            }

            if(tempExternalMembersRemove.size() > 0) Frame.getClient().sendChanges(tempExternalMembersRemove, "delete");
            Frame.getClient().sendChanges(tempExternalMembersAdd, "insert");
        }

		frame.setFrame("mainScreen");
	}
	
	public void refresh(){
		this.revalidate();
		this.repaint();
	}
}
