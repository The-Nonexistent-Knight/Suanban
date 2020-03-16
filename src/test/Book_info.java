package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class Book_info extends JFrame{

	private JFrame frame;
	private int idBook;
	private String title,publish_date,page,price,layout,publisher,ISBN,intro,score;

	public Book_info(Connection con, int eID) throws SQLException{
		idBook = eID;
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con)throws SQLException {
		
		ResultSet rst=Main.b.getBookInfo(idBook);
		rst.next();
		title=rst.getString("BOOK_TITLE");
		publish_date=rst.getString("BOOK_PUBLISH_DATE");
		page=rst.getString("BOOK_PAGE_NUM");
		price=rst.getString("BOOK_PRICE");
		if(rst.getString("BOOK_LAYOUT").equals("1")) layout="平装";else layout="精装";
		publisher=rst.getString("BOOK_PUBLISH");
		ISBN=rst.getString("BOOK_ISBN");
		intro=rst.getString("BOOK_INTRO");
		score=rst.getString("final_score");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1111, 806);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 40));
		lblNewLabel.setBounds(29, 89, 337, 112);
		frame.getContentPane().add(lblNewLabel);
		
		JList list_2 = new JList();
		list_2.setBounds(425, 48, 152, 180);
		frame.getContentPane().add(list_2);
		
		ArrayList<String> author =new ArrayList<>();
		ResultSet resultSet=null;
		try {
			resultSet=Main.b.getBookPeople(idBook);
			
			while(resultSet.next()) {
				author.add(resultSet.getString("PEOPLE_FIRST_NAME_FOREIGNER")+resultSet.getString("PEOPLE_SURNAME_FOREIGNER"));
			}
			list_2.setListData(author.toArray());
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel("\u4F5C\u8005");
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		label.setBounds(329, 117, 67, 35);
		frame.getContentPane().add(label);
		
		JLabel label_3 = new JLabel("\u51FA\u7248\u793E:"+publisher);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(46, 298, 241, 35);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u51FA\u7248\u65E5\u671F:"+publish_date);
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(46, 362, 232, 35);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u9875\u6570:"+page);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(46, 422, 170, 35);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u5B9A\u4EF7:"+price);
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		label_6.setBounds(46, 483, 170, 35);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u88C5\u5E27:"+layout);
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		label_7.setBounds(46, 542, 170, 35);
		frame.getContentPane().add(label_7);
		
		JLabel lblImdb = new JLabel("ISBN\u7F16\u7801:"+ISBN);
		lblImdb.setFont(new Font("宋体", Font.PLAIN, 20));
		lblImdb.setBounds(46, 604, 241, 35);
		frame.getContentPane().add(lblImdb);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(327, 306, 274, 282);
		frame.getContentPane().add(textArea);
		
		JLabel label_8 = new JLabel("\u8BC4\u5206:"+score);
		label_8.setFont(new Font("宋体", Font.PLAIN, 25));
		label_8.setBounds(31, 31, 217, 45);
		frame.getContentPane().add(label_8);
		
		ResultSet rSetComment = Main.u.getComment(idBook, true);
		String[] contentComment = new String[3];
		int[] commentUserId = new int[3];
		int index = 0;
		while(rSetComment.next()) {
			commentUserId[index] = rSetComment.getInt("USER");
			contentComment[index++] = rSetComment.getString("SHORT_COMMENT");
			if(index == 3)
				break;
		}
		
		JTextArea textArea_1 = new JTextArea(contentComment[0]);
		textArea_1.setBounds(711, 46, 232, 137);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea(contentComment[1]);
		textArea_2.setBounds(711, 252, 232, 137);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea(contentComment[2]);
		textArea_3.setBounds(711, 451, 232, 137);
		frame.getContentPane().add(textArea_3);
		
		JButton btnNewButton = new JButton("\u70B9\u8D5E");
		btnNewButton.setBounds(955, 107, 67, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u70B9\u8D5E");
		button.setBounds(955, 305, 67, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u70B9\u8D5E");
		button_1.setBounds(955, 507, 67, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u6807\u8BB0");
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		button_2.setBounds(39, 220, 106, 45);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u5199\u957F\u8BC4");
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		button_3.setBounds(698, 645, 106, 25);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u770B\u957F\u8BC4");
		button_4.setFont(new Font("宋体", Font.PLAIN, 20));
		button_4.setBounds(850, 647, 111, 25);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button_5.setFont(new Font("宋体", Font.PLAIN, 25));
		button_5.setBounds(375, 674, 152, 35);
		frame.getContentPane().add(button_5);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Main.u.likeComment(commentUserId[0], idBook, true);
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Main.u.likeComment(commentUserId[1], idBook, true);
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Main.u.likeComment(commentUserId[2], idBook, true);
			}
		});
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				Mark_Book m =new Mark_Book(con,idBook ,title);
		}
	});
		
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				Write_long_comment m =new Write_long_comment(con, idBook, true);
		}
	});
		
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				Long_Comment_list m =new Long_Comment_list(con, idBook, true);
		}
	});
	}
		

}
