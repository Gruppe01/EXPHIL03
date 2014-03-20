package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;

import model.Meeting;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.ListSelectionModel;
import java.awt.Color;

public class WeekCalendar extends JPanel {
	private JTextPane statusPane;
	private JTable table;
	private Integer week;
	private Integer year;
	private Integer month;
	private Integer day;
	private String hourminsec;
	private JLabel lblWeek;
	private JComboBox collegues;
	private DefaultListModel model;
	private GregorianCalendar GCalendar;
	private String date;
	private DefaultTableModel tablemodel;
	private Meeting selectedData;
	
	private void removeMeeting(){
		String selectedCell;
		Meeting meeting = getSelectedData();
		String starttime = meeting.getStarttime().substring(11, 16);
		System.out.println(starttime);
		String endtime = meeting.getEndtime().substring(11, 16);
		System.out.println(endtime);
		for(int row = 0; row < tablemodel.getRowCount(); row++){
			selectedCell = (String) tablemodel.getValueAt(row, 0);
			if(selectedCell.equals(starttime)){
				for(int newrow = row; row < tablemodel.getRowCount(); newrow++){
					tablemodel.setValueAt(null, newrow, 2);
					selectedCell = (String) tablemodel.getValueAt(newrow, 0);
					System.out.println("Selectedcell: " + selectedCell);
					if(selectedCell.equals(endtime)){
						break;
					}
				}
			}
		}
	}
	
	public String getDate(){
		return date;
	}
	
	private void setDate(){
		GCalendar.set(year, month, day);
		date = GCalendar.get(Calendar.YEAR) + "-" + GCalendar.get(Calendar.MONTH) + "-" + GCalendar.get(Calendar.DAY_OF_MONTH) + " " + hourminsec;
	}
	
	
	private void lastWeek(){
		if(week == 1){
			week = 52;
		}else{
			week = week - 1;
		}
		day = day - 7;
		lblWeek.setText("Week " + week.toString());
		setDate();
		System.out.println(getDate());
	}
	private void nextWeek(){
		if(week == 52){
			week = 1;
		}else{
			week = week + 1;
		}
		day = day + 7;
		lblWeek.setText("Week " + week.toString());
		setDate();
		System.out.println(getDate());
	}
	
	private Meeting getSelectedData(){
		selectedData = null;

        int[] selectedRow = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();

        for (int i = 0; i < selectedRow.length; i++) {
          for (int j = 0; j < selectedColumns.length; j++) {
            selectedData = (Meeting) table.getValueAt(selectedRow[i], selectedColumns[j]);
          }
        }
        System.out.println("Selected: " + selectedData);
        if(selectedData != null){
        	statusPane.setText(selectedData.toString());
        }else{
        	statusPane.setText("No meeting selected");
        }
		return selectedData;
	}
	
	public void addMeetingtoCalendar(Meeting meeting, String starttime, String endtime, int day){
		String selectedCell;
		starttime = starttime.substring(11, 16);
		System.out.println(starttime);
		endtime = endtime.substring(11, 16);
		System.out.println(endtime);
		for(int row = 0; row < tablemodel.getRowCount(); row++){
			selectedCell = (String) tablemodel.getValueAt(row, 0);
			if(selectedCell.equals(starttime)){
				for(int newrow = row; row < tablemodel.getRowCount(); newrow++){
					tablemodel.setValueAt(meeting, newrow, day);
					selectedCell = (String) tablemodel.getValueAt(newrow, 0);
					System.out.println("Selectedcell: " + selectedCell);
					if(selectedCell.equals(endtime)){
						break;
					}
				}
			}
		}
	}
	
	/**
	 * Create the panel.
	 */
	public WeekCalendar(final Frame frame) {
		setLayout(null);
		
		
		GCalendar = new GregorianCalendar();
		week = GCalendar.get(Calendar.WEEK_OF_YEAR);
		year = GCalendar.get(Calendar.YEAR);
		month = GCalendar.get(Calendar.MONTH);
		day = GCalendar.get(Calendar.DAY_OF_MONTH);
		
		lblWeek = new JLabel("Week " + week.toString());
		lblWeek.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeek.setBounds(339, 23, 158, 21);
		add(lblWeek);
		
		JButton nextweek_button = new JButton(">");
		nextweek_button.setBounds(507, 22, 55, 23);
		add(nextweek_button);
		nextweek_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextWeek();	
			}
		});
		
		JButton lastweek_button = new JButton("<");
		lastweek_button.setBounds(274, 22, 55, 23);
		add(lastweek_button);
		lastweek_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastWeek();	
			}
		});
		
		model = new DefaultListModel();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(180, 56, 494, 247);
		add(scrollPane_1);
		
		table = new JTable();
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				getSelectedData();
		        
			}
		     

		    });
		tablemodel = new DefaultTableModel(
				new Object[][] {
					{"07:00", null, null, null, null, null, null, null},
					{"07:30", null, null, null, null, null, null, null},
					{"08:00", null, null, null, null, null, null, null},
					{"08:30", null, null, null, null, null, null, null},
					{"09:00", null, null, null, null, null, null, null},
					{"09:30", null, null, null, null, null, null, null},
					{"10:00", null, null, null, null, null, null, null},
					{"10:30", null, null, null, null, null, null, null},
					{"11:00", null, null, null, null, null, null, null},
					{"11:30", null, null, null, null, null, null, null},
					{"12:00", null, null, null, null, null, null, null},
					{"12:30", null, null, null, null, null, null, null},
					{"13:00", null, null, null, null, null, null, null},
					{"13:30", null, null, null, null, null, null, null},
					{"14:00", null, null, null, null, null, null, null},
					{"14:30", null, null, null, null, null, null, null},
					{"15:00", null, null, null, null, null, null, null},
					{"15:30", null, null, null, null, null, null, null},
					{"16:00", null, null, null, null, null, null, null},
					{"16:30", null, null, null, null, null, null, null},
					{"17:00", null, null, null, null, null, null, null},
					{"17:30", null, null, null, null, null, null, null},
					{"18:00", null, null, null, null, null, null, null},
					{"18:30", null, null, null, null, null, null, null},
					{"19:00", null, null, null, null, null, null, null},
					{"19:30", null, null, null, null, null, null, null},
					{"20:00", null, null, null, null, null, null, null},
					{"20:30", null, null, null, null, null, null, null},
					{"21:00", null, null, null, null, null, null, null},
					{"21:30", null, null, null, null, null, null, null},
					{"22:00", null, null, null, null, null, null, null},
					{"22:30", null, null, null, null, null, null, null},
					{"23:00", null, null, null, null, null, null, null},
					{"23:30", null, null, null, null, null, null, null},
					{"00:00", null, null, null, null, null, null, null},
					{"00:30", null, null, null, null, null, null, null},
					{"01:00", null, null, null, null, null, null, null},
					{"01:30", null, null, null, null, null, null, null},
					{"02:00", null, null, null, null, null, null, null},
					{"02:30", null, null, null, null, null, null, null},
					{"03:00", null, null, null, null, null, null, null},
					{"03:30", null, null, null, null, null, null, null},
					{"04:00", null, null, null, null, null, null, null},
					{"04:30", null, null, null, null, null, null, null},
					{"05:00", null, null, null, null, null, null, null},
					{"05:30", null, null, null, null, null, null, null},
					{"06:00", null, null, null, null, null, null, null},
					{"06:30", null, null, null, null, null, null, null},
				},
				new String[] {
					"Tid", "Man", "Tir", "Ons", "Tor", "Fre", "L\u00F8r", "S\u00F8n"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(tablemodel);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		scrollPane_1.setViewportView(table);
		collegues = new JComboBox();
		collegues.setModel(new DefaultComboBoxModel(new String[] {"", "Robin Sjøvoll", "Simon Borøy-Johnsen", "Thor Håkon Bredesen", "Simen", "Russel", "Sara", "Susanne"}));
		collegues.setBounds(10, 67, 160, 20);
		AutoCompleteDecorator.decorate(collegues);
		add(collegues);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(20, 98, 46, 14);
		add(lblStatus);
		
		JButton btnRemoveSche = new JButton("Remove meeting from my schedule");
		btnRemoveSche.setToolTipText("");
		btnRemoveSche.setBounds(292, 321, 288, 31);
		add(btnRemoveSche);
		btnRemoveSche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMeeting();
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 314, 89, 44);
		add(btnBack);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 123, 160, 180);
		add(scrollPane_2);
		
		statusPane = new JTextPane();
		statusPane.setText("");
		scrollPane_2.setViewportView(statusPane);
		statusPane.setEditable(false);
		
		JLabel lblOthersCalendar = new JLabel("Colleagues calendar:");
		lblOthersCalendar.setHorizontalAlignment(SwingConstants.CENTER);
		lblOthersCalendar.setBounds(0, 11, 140, 44);
		add(lblOthersCalendar);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrame("mainScreen");
			}
		});
		addMeetingtoCalendar(new Meeting("2014-03-14T08:00:00", "2014-03-14T10:00:00", "Fylla", 4, 2, "Robin"), "2014-03-14T08:00:00", "2014-03-14T10:00:00", 2);
	}
}
