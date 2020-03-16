package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

public class List {

	private JFrame frame;
	private JTextField textField;


	public List(Connection con) {
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 649, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6211\u6536\u85CF\uFF08\u521B\u5EFA\uFF09\u7684\u4E66/\u5F71\u5355");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(12, 40, 241, 41);
		frame.getContentPane().add(label);
		
		JList list = new JList();
		list.setBounds(22, 92, 218, 311);
		frame.getContentPane().add(list);
		
		textField = new JTextField();
		textField.setBounds(349, 47, 167, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(528, 48, 74, 25);
		frame.getContentPane().add(btnNewButton);
		
		JList list_1 = new JList();
		list_1.setBounds(337, 92, 194, 311);
		frame.getContentPane().add(list_1);
		
		JButton button = new JButton("\u6536\u85CF");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(545, 201, 74, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(412, 438, 119, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u521B\u5EFA\u4E66\u5355");
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(251, 438, 119, 25);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u521B\u5EFA\u5F71\u5355");
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		button_3.setBounds(93, 438, 119, 25);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u67E5\u770B");
		button_4.setFont(new Font("宋体", Font.PLAIN, 20));
		button_4.setBounds(251, 201, 74, 25);
		frame.getContentPane().add(button_4);
		
		button_1.addActionListener(new ActionListener() {//返回主页
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
		
		button_3.addActionListener(new ActionListener() {//创建影单
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					List_name m =new List_name(con,true);
			}
		});
		
		button_2.addActionListener(new ActionListener() {//创建书单
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					List_name m =new List_name(con,false);
			}
		});
		
		ArrayList<String> dispName = new ArrayList<>();
		ArrayList<Integer> listID = new ArrayList<>();
		ResultSet rSet = Main.u.getCollectList();
		try {
			while(rSet.next()) {
				dispName.add(rSet.getString("LIST_NAME"));
				listID.add(rSet.getInt("idLIST"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		list.setListData(dispName.toArray());
		
		JButton button_5 = new JButton("\u5220\u9664");
		button_5.setFont(new Font("宋体", Font.PLAIN, 20));
		button_5.setBounds(251, 259, 74, 25);
		frame.getContentPane().add(button_5);
		
		
		
		
		
		ArrayList<String> dispStrings = new ArrayList<>();
		ArrayList<Integer> listIDs = new ArrayList<>();
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				String listName = textField.getText();
				dispStrings.clear();
				listID.clear();
				ResultSet reSet = Main.u.findList(listName);
				try {
					while(reSet.next()) {
						dispStrings.add(reSet.getString("LIST_NAME"));
						listIDs.add(reSet.getInt("idLIST"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				list_1.setListData(dispStrings.toArray());
		}
	});
		button_4.addActionListener(new ActionListener() {   //查看
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					int ID= listID.get(list.getSelectedIndex());
					Li m =new Li(con, ID);
			}
		});
		
		button_5.addActionListener(new ActionListener() {   //删除
			public void actionPerformed(ActionEvent e) {				
				int listID1 = listID.get(list.getSelectedIndex());
				Main.u.collectList(false, listID1);
				dispName.clear();
				listID.clear();
				ResultSet reSet = Main.u.getCollectList();
				try {
					while(reSet.next()) {
						dispName.add(reSet.getString("LIST_NAME"));
						listID.add(reSet.getInt("idLIST"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				list.setListData(dispName.toArray());	
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					int listID1 = listIDs.get(list_1.getSelectedIndex());
					Main.u.collectList(true, listID1);
					dispName.clear();
					listID.clear();
					ResultSet reSet = Main.u.getCollectList();
					try {
						while(reSet.next()) {
							dispName.add(reSet.getString("LIST_NAME"));
							listID.add(reSet.getInt("idLIST"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.setListData(dispName.toArray());
			}
		});

	}
	
	
	}


