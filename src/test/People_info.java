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

import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JButton;

public class People_info extends JFrame {

	private JFrame frame;
	private String name,gender,birthday,birthplace,xz,intro;
	private int idPeople;


	public People_info(Connection con, int iD) throws SQLException{
		this.idPeople=iD;
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) throws SQLException{
		ArrayList<String> production = new ArrayList<>();
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			resultSet=Main.b.getPeopleMovie(idPeople);
			
			while(resultSet.next()) {
				production.add(resultSet.getString("MOVIE_TITLE"));
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		try {
			resultSet2=Main.b.getPeopleBook(idPeople);
			
			while(resultSet2.next()) {
				production.add(resultSet2.getString("BOOK_TITLE"));
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		ResultSet rst=Main.b.getPeopleInfo(idPeople);
		rst.next();
		if (rst.getString("PEOPLE_BIRTH_PLACE").equals("china")) name=rst.getString("PEOPLE_NAME_CHINESE");
		else name=rst.getString("PEOPLE_FIRST_NAME_FOREIGNER")+rst.getString("PEOPLE_SURNAME_FOREIGNER");
		if(rst.getString("PEOPLE_GENDER").equals("M")) gender="男"; else gender="女";
		birthday=rst.getString("PEOPLE_BIRTHDAY");
		birthplace=rst.getString("PEOPLE_BIRTH_PLACE");
		xz=rst.getString("PEOPLE_CONSTELLATION");
		intro=rst.getString("PEOPLE_INTRO");
		frame = new JFrame();
		frame.setBounds(100, 100, 648, 681);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel(name);
		label.setFont(new Font("宋体", Font.PLAIN, 35));
		label.setBounds(204, 11, 305, 61);
		frame.getContentPane().add(label);
		
		JTextArea textArea = new JTextArea(intro);
		textArea.setBounds(311, 76, 280, 246);
		frame.getContentPane().add(textArea);
		
		JList list = new JList(production.toArray());
		list.setBounds(156, 359, 280, 216);
		frame.getContentPane().add(list);
		
		JLabel label_1 = new JLabel("\u4F5C\u54C1\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 25));
		label_1.setBounds(60, 329, 85, 30);
		frame.getContentPane().add(label_1);
		
		JButton button = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(232, 597, 136, 33);
		frame.getContentPane().add(button);
		
		JLabel label_2 = new JLabel("\u6027\u522B\uFF1A:"+gender);
		label_2.setFont(new Font("宋体", Font.PLAIN, 25));
		label_2.setBounds(24, 76, 219, 30);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u751F\u65E5\uFF1A:"+birthday);
		label_3.setFont(new Font("宋体", Font.PLAIN, 25));
		label_3.setBounds(24, 140, 219, 30);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u51FA\u751F\u5730\uFF1A:"+birthplace);
		label_4.setFont(new Font("宋体", Font.PLAIN, 25));
		label_4.setBounds(24, 201, 275, 30);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u661F\u5EA7\uFF1A:"+xz);
		label_5.setFont(new Font("宋体", Font.PLAIN, 25));
		label_5.setBounds(24, 262, 219, 30);
		frame.getContentPane().add(label_5);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
	}
}
