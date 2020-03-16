package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

public class Friends extends JFrame{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 * @param con 
	 */
	public Friends(Connection con) {
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 436, 577);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		textField.setBounds(60, 74, 190, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u597D\u53CB\u6635\u79F0\u641C\u7D22\uFF1A");
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		label.setBounds(60, 30, 167, 33);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		button.setBounds(272, 79, 81, 25);
		frame.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("\u597D\u53CB\u5217\u8868\uFF1A");
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		label_1.setBounds(60, 137, 106, 33);
		frame.getContentPane().add(label_1);
		
		JList list = new JList();
		ResultSet resultSet = Main.u.getFriends();
        ArrayList<String> resultString = new ArrayList<>();
        ArrayList<Integer> resultInt = new ArrayList<>();
        try {
			while(resultSet.next()) {
				int friendID = resultSet.getInt(1);
				resultInt.add(friendID);
				ResultSet resultSet2 = Main.u.getBasicInfo(friendID);
				if(resultSet2.next()) {
					resultString.add(resultSet2.getString("USER_NICKNAME"));
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		list.setBounds(60, 209, 210, 267);
		frame.getContentPane().add(list);
		list.setListData(resultString.toArray());
		
		JButton button_1 = new JButton("\u79C1\u804A");
		button_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		button_1.setBounds(300, 274, 81, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u8FD4\u56DE");
		button_2.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		button_2.setBounds(300, 383, 81, 25);
		frame.getContentPane().add(button_2);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
            Main.u.addFriends(textField.getText());
            ResultSet resultSet = Main.u.getFriends();
            ArrayList<String> resultString = new ArrayList<>();
            ArrayList<Integer> resultInt = new ArrayList<>();
            try {
				while(resultSet.next()) {
					int friendID = resultSet.getInt(1);
					resultInt.add(friendID);
					ResultSet resultSet2 = Main.u.getBasicInfo(friendID);
					if(resultSet2.next()) {
						resultString.add(resultSet2.getString("USER_NICKNAME"));
					}			
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            list.setListData(resultString.toArray());
            
           
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					int ID=resultInt.get(list.getSelectedIndex());
					Chat m =new Chat(con,ID);
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
	}

}
