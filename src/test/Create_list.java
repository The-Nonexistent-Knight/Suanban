package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JList;

public class Create_list extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private int currentListID;

	public Create_list(Connection con, boolean b, int currentListID) {
		this.currentListID = currentListID;
		initialize(con,b);
		frame.setVisible(true);
	}


	private void initialize(Connection con, boolean b) {
		frame = new JFrame();
		frame.setBounds(100, 100, 805, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(89, 52, 191, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		btnNewButton.setBounds(280, 56, 88, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("\u8F93\u5165\u4F60\u8981\u6DFB\u52A0\u7684\u5185\u5BB9\uFF1A");
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		label.setBounds(29, 11, 217, 40);
		frame.getContentPane().add(label);
		
		JList list = new JList();
		list.setBounds(45, 128, 217, 302);
		frame.getContentPane().add(list);
		
		JButton button = new JButton("\u786E\u5B9A\u6DFB\u52A0");
		button.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		button.setBounds(280, 252, 123, 25);
		frame.getContentPane().add(button);
		
		JList list_1 = new JList();
		list_1.setBounds(428, 128, 217, 302);
		frame.getContentPane().add(list_1);
		
		JButton button_1 = new JButton("\u5220\u9664");
		button_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		button_1.setBounds(664, 252, 113, 25);
		frame.getContentPane().add(button_1);
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u4E66/\u5F71\u5355\uFF1A");
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		label_1.setBounds(446, 90, 157, 27);
		frame.getContentPane().add(label_1);
		
		JButton button_2 = new JButton("\u5B8C\u6210\u521B\u5EFA");
		button_2.setFont(new Font("Ó×Ô²", Font.PLAIN, 25));
		button_2.setBounds(297, 484, 147, 37);
		frame.getContentPane().add(button_2);
		
		ArrayList<String> resultString = new ArrayList<>();
		ArrayList<Integer> resultId = new ArrayList<>();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					String str=textField.getText();
					//0 - 8099
					resultId.clear();
					resultString.clear();
					if(b) {
						ResultSet resultSet = Main.b.searchMovie(str, 0, 8099,null, null, null);
						try {
							while(resultSet.next()) {
								resultString.add(resultSet.getString("MOVIE_TITLE"));
								resultId.add(resultSet.getInt("idMOVIE"));
							}
							list.setListData(resultString.toArray());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					//	Main.u.editList(listID, add, addElementID); resultId.get(list.getLeadSelectionIndex());
						
					}else {
						ResultSet resultSet = Main.b.searchBook(str, 0, 8099,null, null, null);
						try {
							while(resultSet.next()) {
								resultString.add(resultSet.getString("BOOK_TITLE"));
								resultId.add(resultSet.getInt("idBOOK"));
							}
							list.setListData(resultString.toArray());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
			}
		});
		ArrayList<String> disStrings = new ArrayList<>();
		ArrayList<Integer>elementsIDs = new ArrayList<>(); 
		button.addActionListener(new ActionListener() {//tianjia
			public void actionPerformed(ActionEvent e) {
				disStrings.clear();
				elementsIDs.clear();
				if(b) {
					int ID=resultId.get(list.getSelectedIndex());
					Main.u.editList(currentListID, true, ID);
					
					ResultSet rSet = Main.u.elementsInList(currentListID);
					try {
						while(rSet.next()) {
							disStrings.add(rSet.getString("MOVIE_TITLE"));
							elementsIDs.add(rSet.getInt("idMOVIE"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list_1.setListData(disStrings.toArray());
				}else {
					int ID=resultId.get(list.getSelectedIndex());
					Main.u.editList(currentListID, true, ID);
					
					ResultSet rSet = Main.u.elementsInList(currentListID);
					try {
						while(rSet.next()) {
							disStrings.add(rSet.getString("BOOK_TITLE"));
							elementsIDs.add(rSet.getInt("idBOOK"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list_1.setListData(disStrings.toArray());
				}
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int ID=elementsIDs.get(list_1.getSelectedIndex());
				Main.u.editList(currentListID, false, ID);
				disStrings.clear();
				elementsIDs.clear();
				if(b) {
					ResultSet rSet = Main.u.elementsInList(currentListID);
					try {
						while(rSet.next()) {
							disStrings.add(rSet.getString("MOVIE_TITLE"));
							elementsIDs.add(rSet.getInt("idMOVIE"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list_1.setListData(disStrings.toArray());
				}else {
					ResultSet rSet = Main.u.elementsInList(currentListID);
					try {
						while(rSet.next()) {
							disStrings.add(rSet.getString("BOOK_TITLE"));
							elementsIDs.add(rSet.getInt("iBOOK"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list_1.setListData(disStrings.toArray());
				}
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					List m =new List(con);
			}
		});
	}

}
