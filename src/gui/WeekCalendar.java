package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.DefaultComboBoxModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.ListSelectionModel;

public class WeekCalendar extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public WeekCalendar() {
		setLayout(null);
		
		JButton button = new JButton("<");
		button.setBounds(165, 22, 55, 23);
		add(button);
		
		JButton button_1 = new JButton(">");
		button_1.setBounds(398, 22, 55, 23);
		add(button_1);
		
		JLabel lblWeek = new JLabel("Week *");
		lblWeek.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeek.setBounds(230, 23, 158, 21);
		add(lblWeek);
		
		JLabel lblNewLabel = new JLabel("Colleagues calendar:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(513, 48, 126, 14);
		add(lblNewLabel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(513, 102, 117, 23);
		add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(513, 136, 117, 129);
		add(scrollPane);
		
		JLabel lblSelected = new JLabel("Selected members");
		lblSelected.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblSelected);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(513, 276, 117, 23);
		add(btnRemove);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(129, 56, 374, 226);
		add(scrollPane_1);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(
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
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		scrollPane_1.setViewportView(table);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Arne", "Simon", "Robin", "Simen", "Russel", "Sara", "Susanne"}));
		comboBox.setBounds(513, 73, 117, 20);
		AutoCompleteDecorator.decorate(comboBox);
		add(comboBox);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setBounds(29, 48, 46, 14);
		add(lblStatus);
		
		JTextPane txtpnInvitedAttendingDeclined = new JTextPane();
		txtpnInvitedAttendingDeclined.setEditable(false);
		txtpnInvitedAttendingDeclined.setText("Invited: \r\n\r\nAttending:\r\nDeclined:");
		txtpnInvitedAttendingDeclined.setBounds(24, 73, 95, 129);
		add(txtpnInvitedAttendingDeclined);
		
		JButton btnRemoveSche = new JButton("Remove meeting from my schedule");
//		btnRemoveSche.setLayout(new BorderLayout());
//		JLabel label1 = new JLabel("Remove meeting");
//		JLabel label2 = new JLabel("from my schedule");
//		btnRemoveSche.add(BorderLayout.NORTH, label1);
//		btnRemoveSche.add(BorderLayout.SOUTH, label2);
		btnRemoveSche.setToolTipText("");
		btnRemoveSche.setBounds(165, 293, 288, 31);
		add(btnRemoveSche);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(30, 276, 89, 44);
		add(btnBack);

	}
}
