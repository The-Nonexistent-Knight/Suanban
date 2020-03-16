package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Li {

	private JFrame frame;
	private JTextField textField;
	private int ID,creator,tu;
	private boolean b;

	public Li(Connection con, int listID) {
		ID=listID;
		b = (Main.u.typeOfList(listID).equals("M"));
		tu=Main.u.idUSER;
		creator = Main.u.getListCreator(ID);
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 473, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JList list = new JList();
		ArrayList<String> disStrings = new ArrayList<>();
		ArrayList<Integer>elementsIDs = new ArrayList<>(); 
		//用于刷新 记得clear
		if(b) {
			
			
			ResultSet rSet = Main.u.elementsInList(ID);
			try {
				while(rSet.next()) {
					disStrings.add(rSet.getString("MOVIE_TITLE"));
					elementsIDs.add(rSet.getInt("idMOVIE"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			list.setListData(disStrings.toArray());
		}else {
			
			
			ResultSet rSet = Main.u.elementsInList(ID);
			try {
				while(rSet.next()) {
					disStrings.add(rSet.getString("BOOK_TITLE"));
					elementsIDs.add(rSet.getInt("idBOOK"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			list.setListData(disStrings.toArray());
		}
		//end
		list.setBounds(50, 67, 256, 314);
		frame.getContentPane().add(list);
		
		JLabel label = new JLabel("\u5355\u540D");
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		label.setBounds(101, 24, 157, 32);
		frame.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("\u5220\u9664");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(318, 283, 103, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u67E5\u770B");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(318, 319, 103, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button_1.setFont(new Font("宋体", Font.PLAIN, 25));
		button_1.setBounds(133, 403, 147, 45);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u6DFB\u52A0");
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(318, 245, 103, 25);
		frame.getContentPane().add(button_2);
		
		textField = new JTextField();
		textField.setBounds(318, 24, 129, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("\u641C\u7D22");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(318, 62, 103, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		
		JList list_1 = new JList();
		list_1.setBounds(318, 107, 111, 122);
		frame.getContentPane().add(list_1);
		
		button_1.addActionListener(new ActionListener() {   //返回主页
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
		
		
		
		button.addActionListener(new ActionListener() {   //查看
			public void actionPerformed(ActionEvent e) {				
				int eID=elementsIDs.get(list.getSelectedIndex());
				frame.dispose();
				if(b) {
					try {
						Movie_info movie_info = new Movie_info(con, eID);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					try {
						Book_info book_info = new Book_info(con, eID);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {   //删除
			public void actionPerformed(ActionEvent e) {
				   if (tu!=creator) {warning w=new warning(con,ID);frame.dispose();}else 
				   {
					int eID=elementsIDs.get(list.getSelectedIndex());
					Main.u.editList(ID, false, eID);
					disStrings.clear();
					elementsIDs.clear();
					if(b) {
						ResultSet rSet = Main.u.elementsInList(ID);
						try {
							while(rSet.next()) {
								disStrings.add(rSet.getString("MOVIE_TITLE"));
								elementsIDs.add(rSet.getInt("idMOVIE"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						list.setListData(disStrings.toArray());
					}else {
						ResultSet rSet = Main.u.elementsInList(ID);
						try {
							while(rSet.next()) {
								disStrings.add(rSet.getString("BOOK_TITLE"));
								elementsIDs.add(rSet.getInt("iBOOK"));
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						list.setListData(disStrings.toArray());
					}
				   }
			}
		});
		
		ArrayList<String> resultString = new ArrayList<>();
		ArrayList<Integer> resultId = new ArrayList<>();
		
		button_2.addActionListener(new ActionListener() {   //添加
			public void actionPerformed(ActionEvent e) {
				if (tu!=creator) {warning w=new warning(con,ID);frame.dispose();}else {
				disStrings.clear();
				elementsIDs.clear();
				if(b) {
					int eID=resultId.get(list_1.getSelectedIndex());
					Main.u.editList(ID, true, eID);
					
					ResultSet rSet = Main.u.elementsInList(ID);
					try {
						while(rSet.next()) {
							disStrings.add(rSet.getString("MOVIE_TITLE"));
							elementsIDs.add(rSet.getInt("idMOVIE"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.setListData(disStrings.toArray());
				}else {
					int eID=resultId.get(list.getSelectedIndex());
					Main.u.editList(ID, true, eID);
					
					ResultSet rSet = Main.u.elementsInList(ID);
					try {
						while(rSet.next()) {
							disStrings.add(rSet.getString("BOOK_TITLE"));
							elementsIDs.add(rSet.getInt("idBOOK"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list.setListData(disStrings.toArray());
				}
				}
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {   //搜索
			public void actionPerformed(ActionEvent e) {				
					String str=textField.getText();
					resultId.clear();
					resultString.clear();
					if(b) {
						ResultSet resultSet = Main.b.searchMovie(str, 0, 8099,null, null, null);
						try {
							while(resultSet.next()) {
								resultString.add(resultSet.getString("MOVIE_TITLE"));
								resultId.add(resultSet.getInt("idMOVIE"));
							}
							list_1.setListData(resultString.toArray());
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
							list_1.setListData(resultString.toArray());
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
			}
		});
		
	}
}
