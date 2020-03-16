package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
public class Chat extends JFrame{

	private JFrame frame;
	private int friendID;
	
	public Chat(Connection con, int FriendiD) {
		this.friendID = FriendiD;
		initialize(con);
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(12, 11, 563, 244);
		frame.getContentPane().add(list);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 262, 563, 111);
		frame.getContentPane().add(textArea);
		
		JButton button = new JButton("\u53D1\u9001");
		button.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		button.setBounds(472, 384, 103, 25);
		frame.getContentPane().add(button);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		btnNewButton.setBounds(352, 384, 103, 25);
		frame.getContentPane().add(btnNewButton);
		
		ResultSet rSet = Main.u.getMessage(friendID);
		ArrayList<String> display = new ArrayList<>();
		try {
			while(rSet.next()) {
				String sendName = "";
				ResultSet rSet2 = Main.u.getBasicInfo(rSet.getInt("SEND"));
				if(rSet2.next()) {
					sendName = rSet2.getString("USER_NICKNAME");
				}
				String content = rSet.getString("CONTENT");
				String time = rSet.getTime("TIME").toString();
				String diString = String.format("%s: %s (%s)", sendName, content, time);
				display.add(diString);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		list.setListData(display.toArray());
		
		 btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Friends m =new Friends(con);
			}
		});
		 
		 button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
				Main.u.sentMessage(friendID, textArea.getText());
				ResultSet rSet = Main.u.getMessage(friendID);
				ArrayList<String> display = new ArrayList<>();
				try {
					while(rSet.next()) {
						String sendName = "";
						ResultSet rSet2 = Main.u.getBasicInfo(rSet.getInt("SEND"));
						if(rSet2.next()) {
							sendName = rSet2.getString("USER_NICKNAME");
						}
						String content = rSet.getString("CONTENT");
						String time = rSet.getTime("TIME").toString();
						String diString = String.format("%s: %s (%s)", sendName, content, time);
						display.add(diString);
					}
					list.setListData(display.toArray());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			});
	}
}
