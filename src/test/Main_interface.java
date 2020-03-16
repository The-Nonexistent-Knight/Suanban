package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class Main_interface extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private int ID,fID;


	public Main_interface(Connection con) {
		initialize(con);
		frame.setVisible(true);
	}

	private void initialize(Connection con) {
		ArrayList<String> peopleList = new ArrayList<>();
		ArrayList<String> peopleID = new ArrayList<>();
		ArrayList<String> movieList = new ArrayList<>();
		ArrayList<String> movieID = new ArrayList<>();
		ArrayList<String> bookList = new ArrayList<>();
		ArrayList<String> bookID = new ArrayList<>();
		ArrayList<String> festivalID = new ArrayList<>();
		ArrayList<String> festivalName = new ArrayList<>();
		
		String[] tag={"战争","爱情","科幻","喜剧","动作","犯罪","奇幻","悬疑"};
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1147, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ArrayList<String> highCoreBook = new ArrayList<>();
		ArrayList<String> highCoreMovie = new ArrayList<>();
		ResultSet resultSetBook = null;
		ResultSet resultSetMovie = null;
		try {
			resultSetBook = Main.b.getHighCoreBook();
			resultSetMovie = Main.b.getHighCoreMovie();
			while(resultSetBook.next()) {
				highCoreBook.add(resultSetBook.getString("BOOK_TITLE"));
				
			}
			while(resultSetMovie.next()) {
				highCoreMovie.add(resultSetMovie.getString("MOVIE_TITLE"));
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JList list = new JList(highCoreMovie.toArray());
		list.setBounds(58, 453, 179, 210);
		frame.getContentPane().add(list);
		
		
		JLabel label = new JLabel("\u9AD8\u5206\u7535\u5F71\u699C");
		label.setFont(new Font("幼圆", Font.PLAIN, 25));
		label.setBounds(77, 368, 132, 57);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u9AD8\u5206\u56FE\u4E66\u699C");
		label_1.setFont(new Font("幼圆", Font.PLAIN, 25));
		label_1.setBounds(359, 368, 132, 57);
		frame.getContentPane().add(label_1);
		
		JList list_1 = new JList(highCoreBook.toArray());
		list_1.setBounds(349, 453, 184, 210);
		frame.getContentPane().add(list_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u7535\u5F71");
		rdbtnNewRadioButton.setFont(new Font("方正舒体", Font.PLAIN, 25));
		rdbtnNewRadioButton.setBounds(667, 65, 105, 34);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("\u56FE\u4E66");
		radioButton.setFont(new Font("方正舒体", Font.PLAIN, 25));
		radioButton.setBounds(776, 65, 105, 34);
		frame.getContentPane().add(radioButton);
		
		JLabel label_2 = new JLabel("\u4FE1\u606F\u68C0\u7D22");
		label_2.setFont(new Font("方正舒体", Font.PLAIN, 35));
		label_2.setBounds(766, 5, 148, 67);
		frame.getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(705, 120, 231, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(948, 123, 105, 35);
		frame.getContentPane().add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u6218\u4E89");
		chckbxNewCheckBox.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox.setBounds(695, 248, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\u7231\u60C5");
		chckbxNewCheckBox_1.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox_1.setBounds(866, 248, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("\u52A8\u4F5C");
		chckbxNewCheckBox_2.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox_2.setBounds(695, 324, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("\u5947\u5E7B");
		chckbxNewCheckBox_3.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox_3.setBounds(866, 324, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox_3);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("\u79D1\u5E7B");
		chckbxNewCheckBox_4.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox_4.setBounds(695, 286, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox_4);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("\u559C\u5267");
		chckbxNewCheckBox_5.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox_5.setBounds(866, 286, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox_5);
		
		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("\u60AC\u7591");
		chckbxNewCheckBox_6.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox_6.setBounds(695, 358, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox_6);
		
		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("\u72AF\u7F6A");
		chckbxNewCheckBox_7.setFont(new Font("宋体", Font.PLAIN, 18));
		chckbxNewCheckBox_7.setBounds(866, 358, 117, 25);
		frame.getContentPane().add(chckbxNewCheckBox_7);
		
		JList list_2 = new JList();
		list_2.setBounds(719, 390, 265, 273);
		frame.getContentPane().add(list_2);
		
		JButton button = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button.setBounds(58, 303, 117, 31);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u597D\u53CB");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_1.setBounds(205, 303, 117, 31);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u4E66\u5355/\u5F71\u5355");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_2.setBounds(359, 303, 132, 31);
		frame.getContentPane().add(button_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\WindUpBird\\Desktop\\\u56FE\u72471.png"));
		lblNewLabel.setBounds(-36, 5, 347, 268);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label_3 = new JLabel("\u849C\u74E3");
		label_3.setFont(new Font("华文新魏", Font.PLAIN, 60));
		label_3.setBounds(341, 93, 179, 117);
		frame.getContentPane().add(label_3);
		
		JButton button_3 = new JButton("\u767B\u51FA");
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		button_3.setBounds(508, 24, 103, 25);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u6807\u8BB0\u5386\u53F2");
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		button_4.setBounds(526, 303, 117, 31);
		frame.getContentPane().add(button_4);
		
		JButton btnNewButton_1 = new JButton("\u67E5\u770B");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(1005, 493, 103, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel label_4 = new JLabel("\u9898\u76EE\uFF08\u4EBA\u540D\uFF09\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(565, 127, 148, 27);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u5E74\u4EFD\uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(650, 183, 63, 27);
		frame.getContentPane().add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBounds(715, 176, 74, 34);
		frame.getContentPane().add(textField_1);
		
		JLabel label_6 = new JLabel("~");
		label_6.setFont(new Font("宋体", Font.PLAIN, 40));
		label_6.setBounds(801, 183, 41, 50);
		frame.getContentPane().add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBounds(834, 176, 74, 34);
		frame.getContentPane().add(textField_2);
		
		JRadioButton radioButton_1 = new JRadioButton("\u4EBA\u7269");
		radioButton_1.setFont(new Font("方正舒体", Font.PLAIN, 25));
		radioButton_1.setBounds(878, 65, 105, 34);
		frame.getContentPane().add(radioButton_1);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(radioButton);
		group.add(radioButton_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(973, 176, 76, 34);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_7 = new JLabel("\u56FD\u5BB6\uFF1A");
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		label_7.setBounds(920, 176, 63, 34);
		frame.getContentPane().add(label_7);
		
		textField_4 = new JTextField();
		textField_4.setBounds(565, 176, 76, 34);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_8 = new JLabel("\u76F8\u5173\u4EBA\u5458\uFF1A");
		label_8.setFont(new Font("宋体", Font.PLAIN, 20));
		label_8.setBounds(473, 174, 110, 36);
		frame.getContentPane().add(label_8);
		
		JRadioButton radioButton_2 = new JRadioButton("\u7535\u5F71\u8282");
		radioButton_2.setFont(new Font("方正舒体", Font.PLAIN, 25));
		radioButton_2.setBounds(982, 65, 105, 34);
		frame.getContentPane().add(radioButton_2);
		
		group.add(radioButton_2);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				if (rdbtnNewRadioButton.isSelected()) {try {
					ID=Integer.valueOf(movieID.get(list_2.getSelectedIndex()));
					Movie_info m=new Movie_info(con, ID);//tem!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
				else if (radioButton.isSelected()) {try {
					ID=Integer.valueOf(bookID.get(list_2.getSelectedIndex()));
					Book_info m=new Book_info(con, ID);//tem!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}
				else if(radioButton_1.isSelected()) {try {
					ID=Integer.valueOf(peopleID.get(list_2.getSelectedIndex()));
					People_info m=new People_info(con,ID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}}else {
					fID=Integer.valueOf(festivalID.get(list_2.getSelectedIndex()));
					Festival festival=new Festival(con, fID);
					frame.dispose();
					
					
				}
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					User_info m =new User_info(con);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Friends m =new Friends(con);
			}
		});
		
		button_2.addActionListener(new ActionListener() { //待实现
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				List m =new List(con);
			}
		});
		
		button_3.addActionListener(new ActionListener() { //待实现
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				testWindowsBuilderButton u=new testWindowsBuilderButton(con);
			}
		});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Mark_History m =new Mark_History(con);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String credit=textField_4.getText();
				ArrayList<String> tags=new ArrayList<>();
				if (chckbxNewCheckBox.isSelected()) tags.add("战争");
				if (chckbxNewCheckBox_1.isSelected()) tags.add("爱情");
				if (chckbxNewCheckBox_2.isSelected()) tags.add("动作");
				if (chckbxNewCheckBox_3.isSelected()) tags.add("奇幻");
				if (chckbxNewCheckBox_4.isSelected()) tags.add("科幻");
				if (chckbxNewCheckBox_5.isSelected()) tags.add("喜剧");
				if (chckbxNewCheckBox_6.isSelected()) tags.add("悬疑");
				if (chckbxNewCheckBox_7.isSelected()) tags.add("犯罪");
				if (radioButton_1.isSelected()) {
				String text=textField.getText();
				ResultSet resultSet=null;
				peopleID.clear();
				peopleList.clear();
				try {
					resultSet=Main.b.searchPeople(text);
					while(resultSet.next()) {
						peopleList.add(resultSet.getString("PEOPLE_FIRST_NAME_FOREIGNER")+" "+resultSet.getString("PEOPLE_SURNAME_FOREIGNER"));
						peopleID.add(resultSet.getString("idPEOPLE"));
					}list_2.setListData(peopleList.toArray());
				}catch(SQLException e1) {
					e1.printStackTrace();
				}			
				}else if(rdbtnNewRadioButton.isSelected()) {
					String title=textField.getText();
					String yearLeft=textField_1.getText();
					if(yearLeft.isEmpty()) {
						yearLeft="1800";
					}
					String yearRight=textField_2.getText();
					if(yearRight.isEmpty()) {
						yearRight="2500";
					}
					String country=textField_3.getText();
					String relatedPeople=textField_4.getText();
					movieID.clear();
					movieList.clear();
					ResultSet resultSet = null;
					try {
					resultSet=Main.b.searchMovie(title, Integer.valueOf(yearLeft), Integer.valueOf(yearRight), relatedPeople, tags, country);
					while(resultSet.next()) {
						movieList.add(resultSet.getString("MOVIE_TITLE"));
						movieID.add(resultSet.getString("idMOVIE"));
					}list_2.setListData(movieList.toArray());
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
					
				}else if(radioButton.isSelected()){
					String title=textField.getText();
					String yearLeft=textField_1.getText();
					if(yearLeft.isEmpty()) {
						yearLeft="1800";
					}
					String yearRight=textField_2.getText();
					if(yearRight.isEmpty()) {
						yearRight="2500";
					}
					String country=textField_3.getText();
					String relatedPeople=textField_4.getText();
					bookID.clear();
					bookList.clear();
					ResultSet resultSet = null;
					try {
					resultSet = Main.b.searchBook(title, Integer.valueOf(yearLeft), Integer.valueOf(yearRight), relatedPeople, tags, country);
					while(resultSet.next()) {
						bookList.add(resultSet.getString("BOOK_TITLE"));
						bookID.add(resultSet.getString("idBOOK"));
					}list_2.setListData(bookList.toArray());
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				}else {
					String na=textField.getText();
					int year=Integer.valueOf(textField_1.getText());
					festivalID.clear();
					festivalName.clear();
					ResultSet resultSet=null;
					try {
						resultSet=Main.b.searchFestival(na, year);
						while(resultSet.next()) {
							festivalID.add(resultSet.getString("idFESTIVAL"));
							festivalName.add(resultSet.getString("FESTIVAL_DATE")+" "+resultSet.getString("FESTIVAL_NAME"));
						}
						list_2.setListData(festivalName.toArray());
					}catch(SQLException e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
	}
}
