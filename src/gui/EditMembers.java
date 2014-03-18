package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class EditMembers extends JFrame {
	/**
	 * Create the panel.
	 */
	
	private JPanel contentPane;
	
	public EditMembers() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(697,335);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 45, 260, 139);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblMeetingMembers = new JLabel("Meeting members");
		lblMeetingMembers.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblMeetingMembers);
		
		JButton btnMakeAdmin = new JButton("Make admin");
		btnMakeAdmin.setBounds(408, 11, 119, 23);
		contentPane.add(btnMakeAdmin);
		
		JButton btnDeleteAdmin = new JButton("Delete admin");
		btnDeleteAdmin.setBounds(549, 11, 119, 23);
		contentPane.add(btnDeleteAdmin);
		
		JButton btnDeleteMember = new JButton("Delete member");
		btnDeleteMember.setBounds(408, 195, 129, 23);
		contentPane.add(btnDeleteMember);
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(579, 214, 89, 47);
		contentPane.add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		JButton button = new JButton(">>");
		button.setBounds(316, 103, 67, 23);
		contentPane.add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 136, 139);
		contentPane.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		
		JLabel lblGroups = new JLabel("Groups");
		lblGroups.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblGroups);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(156, 45, 141, 139);
		contentPane.add(scrollPane_2);
		
		JList list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		
		JLabel lblGroupMembers = new JLabel("Group members");
		lblGroupMembers.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(lblGroupMembers);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 214, 89, 47);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnSelectAll = new JButton("Select all");
		btnSelectAll.setBounds(187, 195, 89, 23);
		contentPane.add(btnSelectAll);
		
		JButton btnAddExternal = new JButton("Add external");
		btnAddExternal.setBounds(167, 11, 119, 23);
		contentPane.add(btnAddExternal);
		btnAddExternal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddExternal addExternal = new AddExternal();
				addExternal.setVisible(true);
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Search...", "Simon", "Robin", "Simen", "Sindre", "Peder", "Arne", "Kjell-Elvis"}));
		comboBox.setBounds(27, 12, 104, 20);
		AutoCompleteDecorator.decorate(comboBox);
		contentPane.add(comboBox);
		
		setContentPane(contentPane);
	}

}
