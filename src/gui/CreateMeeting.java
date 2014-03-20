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
import java.util.Calendar;

import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import persistence.data.Rooms;

public class CreateMeeting extends JPanel {
	private JScrollPane scrollPane;
	private DefaultListModel listModel;
	private JList list;
	private JTextField ParticipantsTextField;
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
		
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		
		JLabel lblAvail = new JLabel("Available rooms");
		lblAvail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAvail.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblAvail);
		
		ParticipantsTextField = new JTextField();
		ParticipantsTextField.setBounds(449, 225, 125, 20);
		add(ParticipantsTextField);
		ParticipantsTextField.setColumns(10);
		ParticipantsTextField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				roomLoader();
			}
		});
		
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
		
		Rooms rooms = frame.getClient().getDataStorage().getRooms();
		
		for (int i=1; i<x; i++){
			
		}
		
		
	}
	
	private void create(){
		
		String starttime;
		String endtime;
		String description;
		
		if (list.isSelectionEmpty()){
			new Meeting(frame.getUser(), starttime, endtime, description, place);
		}
		else{
			new Meeting(frame.getUser(), starttime, endtime, description, capacity, room);
		}
		
		frame.setFrame("mainScreen");
	}
}
