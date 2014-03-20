package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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

import com.sun.glass.events.WindowEvent;

public class EditMembers extends JFrame {
	/**
	 * Create the panel.
	 */
	
	private JPanel contentPane;
	private CreateMeeting meeting;
	private EditMembers working;
	private JList meetingList;
	private DefaultListModel listModelMembers;
	private JList groupMembersList;
	private DefaultListModel listModelgroupMembers;
	private ArrayList<String> members = new ArrayList<>();
	private ArrayList<String> admin = new ArrayList<>();
	
	public EditMembers(final CreateMeeting in) {
		
		meeting = in;
		working = this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(697,335);
		listModelgroupMembers = new DefaultListModel<>();
		listModelMembers = new DefaultListModel<>();
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 45, 260, 139);
		contentPane.add(scrollPane);
		
		meetingList = new JList(listModelMembers);
		scrollPane.setViewportView(meetingList);
		
		JLabel lblMeetingMembers = new JLabel("Meeting members");
		lblMeetingMembers.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblMeetingMembers);
		
		JButton btnMakeAdmin = new JButton("Make admin");
		btnMakeAdmin.setBounds(408, 11, 119, 23);
		contentPane.add(btnMakeAdmin);
		btnMakeAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = meetingList.getSelectedIndex();
				if (selectedIndex != -1) {
					String selected = (String) meetingList.getSelectedValue();
					selected = selected.replaceAll(" (Admin)", "");
					for ( int i = 0;  i < members.size(); i++){
			            String tempName = members.get(i);
			            if(tempName.equals(selected)){
			                admin.add(members.get(i));
			            	members.remove(i);
			            }
			        }
					updateMembersList();
				}
			}
		});
		
		JButton btnDeleteAdmin = new JButton("Delete admin");
		btnDeleteAdmin.setBounds(549, 11, 119, 23);
		contentPane.add(btnDeleteAdmin);
		btnDeleteAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = meetingList.getSelectedIndex();
				if (selectedIndex != -1) {
					String selected = (String) meetingList.getSelectedValue();
					selected = selected.replaceAll(" (Admin)", "");
					for ( int i = 0;  i < admin.size(); i++){
			            String tempName = admin.get(i);
			            if(tempName.equals(selected)){
			                admin.remove(i);
			                members.add(admin.get(i));
			            }
			        }
					updateMembersList();
				}
			}
		});
		
		
		JButton btnDeleteMember = new JButton("Delete member");
		btnDeleteMember.setBounds(408, 195, 129, 23);
		contentPane.add(btnDeleteMember);
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = meetingList.getSelectedIndex();
				if (selectedIndex != -1) {
					String selected = (String) meetingList.getSelectedValue();
					selected = selected.replaceAll(" (Admin)", "");
					for ( int i = 0;  i < admin.size(); i++){
			            String tempName = admin.get(i);
			            if(tempName.equals(selected)){
			                admin.remove(i);
			            }
			        }
					for ( int i = 0;  i < members.size(); i++){
			            String tempName = members.get(i);
			            if(tempName.equals(selected)){
			                members.remove(i);
			            }
			        }
					updateMembersList();
				}
			}
		});
		
		JButton btnDone = new JButton("Done");
		btnDone.setBounds(579, 214, 89, 47);
		contentPane.add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meeting.setMembers(members);
				meeting.setAdmins(admin);
				meeting.enableEditButton();
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnAdd = new JButton(">>");
		btnAdd.setBounds(316, 103, 67, 23);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = groupMembersList.getSelectedIndex();
				if (selectedIndex != -1) {
					String selected = (String) groupMembersList.getSelectedValue();
					members.add(selected);
			        }
					updateMembersList();
				}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 45, 136, 139);
		contentPane.add(scrollPane_1);
		
		JList GroupsList = new JList();
		scrollPane_1.setViewportView(GroupsList);
		
		JLabel lblGroups = new JLabel("Groups");
		lblGroups.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_1.setColumnHeaderView(lblGroups);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(156, 45, 141, 139);
		contentPane.add(scrollPane_2);
		
		groupMembersList = new JList(listModelgroupMembers);
		scrollPane_2.setViewportView(groupMembersList);
		
		JLabel lblGroupMembers = new JLabel("Group members");
		lblGroupMembers.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane_2.setColumnHeaderView(lblGroupMembers);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(10, 214, 89, 47);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meeting.enableEditButton();
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
				AddExternal addExternal = new AddExternal(working);
				addExternal.setVisible(true);
			}
		});
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Search...", "Simon", "Robin", "Simen", "Sindre", "Peder", "Arne", "Kjell-Elvis"}));
		comboBox.setBounds(27, 12, 104, 20);
		AutoCompleteDecorator.decorate(comboBox);
		contentPane.add(comboBox);
		
		setResizable(false);
		setContentPane(contentPane);
		
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	meeting.enableEditButton();
            }
        });
	}
	
	private void updateMembersList(){
		listModelMembers.removeAllElements();
		for (String i:admin){
			listModelMembers.addElement(i+" (Admin)");
		}
		for (String i:members){
			listModelMembers.addElement(i);
		}
	}

}
