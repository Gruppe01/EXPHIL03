package gui;

import model.Meeting;

import javax.swing.DefaultListModel;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import persistence.data.Rooms;

public class CreateMeeting extends JPanel {
	private JScrollPane scrollPane;
	private DefaultListModel listModel;
	private UtilDateModel model;
	private JList list;
	private JTextPane textPane;
	private JLabel lblDescription;
	private JLabel lblSelectTimeStart;
	private JLabel lblSelectTimeEnd;
	private JButton btnCancel;
	private JButton btnEditMembers;
	private JButton btnCreateMeeting;
	private JLabel lblParticipants;
	private JLabel label;
	private JLabel label_1;
	private JSpinner starth;
	private JSpinner startm;
	private JSpinner endh;
	private JSpinner endm;
	private JSpinner participantsSpinner;
	private JScrollPane scrollPane_1;
	private JTextField placeTextField;
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
		
		listModel = new DefaultListModel<>();
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		
		JLabel lblAvail = new JLabel("Available rooms");
		lblAvail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvail.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblAvail);
		
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
				create();
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
		
		starth = new JSpinner();
		starth.setModel(new SpinnerNumberModel(12, 0, 23, 1));
		starth.setBounds(121, 173, 59, 20);
		add(starth);
		starth.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();
				
			}
		});
		
		endh = new JSpinner();
		endh.setModel(new SpinnerNumberModel(13, 0, 23, 1));
		endh.setBounds(121, 204, 59, 20);
		add(endh);
		endh.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();
				
			}
		});
		
		startm = new JSpinner();
		startm.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		startm.setBounds(200, 173, 59, 20);
		add(startm);
		startm.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();
			}
		});
		
		endm = new JSpinner();
		endm.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		endm.setBounds(200, 204, 59, 20);
		add(endm);
		endm.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();
			}
		});
		
		participantsSpinner = new JSpinner();
		participantsSpinner.setModel(new SpinnerNumberModel(1, 1, null, 1));
		participantsSpinner.setBounds(449, 227, 125, 20);
		add(participantsSpinner);
		participantsSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				roomLoader();
			}
		});
		
		
		textPane = new JTextPane();
		textPane.setBounds(121, 31, 143, 103);
		add(textPane);
		
		model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.getJFormattedTextField().setText("Choose date");
		datePicker.setTextEditable(false);
		datePicker.setToolTipText("");
		datePicker.setBounds(274, 31, 145, 23);
		add(datePicker);
		Calendar cal = Calendar.getInstance();
		model.setDate(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1), cal.get(Calendar.DATE));
		model.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				roomLoader();
			}
		});
		
		JLabel lblPlace = new JLabel("Place: ");
		lblPlace.setBounds(10, 151, 46, 14);
		add(lblPlace);
		
		placeTextField = new JTextField();
		placeTextField.setBounds(121, 145, 138, 20);
		add(placeTextField);
		placeTextField.setColumns(10);
		
		
		
		roomLoader();
		
//		scrollPane_1 = new JScrollPane();
//		scrollPane_1.setBounds(274, 103, 132, 86);
//		add(scrollPane_1);
	}
	
	private void roomLoader(){
		
		listModel.removeAllElements();
		
		int capacity;
		String startTime;
		String endTime;
		
		String month = model.getMonth()+1<10 ? "0" + model.getMonth()+1 : "" + model.getMonth()+1;
		String day = model.getDay()<10 ? "0" + model.getDay() : "" + model.getDay();
		String startH = (Integer)starth.getValue() < 10 ? "0" + (Integer)starth.getValue() : "" + (Integer)starth.getValue();
		String endH = (Integer)endh.getValue() < 10 ? "0" + (Integer)endh.getValue() : "" + (Integer)endh.getValue();
		String startM = (Integer)startm.getValue() < 10 ? "0" + (Integer)startm.getValue() : "" + (Integer)startm.getValue();
		String endM = (Integer)endm.getValue() < 10 ? "0" + (Integer)endm.getValue() : "" + (Integer)endm.getValue();
		
		startTime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+startH+":"+startM+":00";
		endTime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+endH+":"+endM+":00";
		
		capacity = (Integer)participantsSpinner.getValue();
		
		ArrayList<Integer> room = frame.getClient().getDataStorage().getAvailableRooms(startTime, endTime, capacity);
		
		for (int i:room){
			listModel.addElement(room);
		}
		
		
	}
	
	private void create(){
		
		String starttime;
		String endtime;
		int capacity;
		String description;
		String place;
		String roomS;
		int room;
		
		String month = model.getMonth()+1<10 ? "0" + model.getMonth()+1 : "" + model.getMonth()+1;
		String day = model.getDay()<10 ? "0" + model.getDay() : "" + model.getDay();
		String startH = (Integer)starth.getValue() < 10 ? "0" + (Integer)starth.getValue() : "" + (Integer)starth.getValue();
		String endH = (Integer)endh.getValue() < 10 ? "0" + (Integer)endh.getValue() : "" + (Integer)endh.getValue();
		String startM = (Integer)startm.getValue() < 10 ? "0" + (Integer)startm.getValue() : "" + (Integer)startm.getValue();
		String endM = (Integer)endm.getValue() < 10 ? "0" + (Integer)endm.getValue() : "" + (Integer)endm.getValue();
		
		description = textPane.getText();
		starttime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+startH+":"+startM+":00";
		endtime = Integer.toString(model.getYear())+"-"+month+"-"+day+"T"+endH+":"+endM+":00";
		capacity = (Integer)participantsSpinner.getValue();
		roomS = list.getSelectedValue().toString();
		room = Integer.parseInt(roomS);
		place = placeTextField.getText();
		ArrayList<Object> meeting = new ArrayList<>();
		
		if (list.isSelectionEmpty()){

			meeting.add(new Meeting(starttime, endtime, description, place, frame.getUserName()));
		}
		else{
			meeting.add(new Meeting(starttime, endtime, description, room, capacity, frame.getUserName()));
		}
		
		frame.getClient().sendChanges(meeting, "insert");
		
		frame.setFrame("mainScreen");
	}
}
