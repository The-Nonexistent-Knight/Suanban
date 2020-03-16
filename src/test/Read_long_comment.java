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

import javax.swing.JTextField;
import javax.swing.JButton;

public class Read_long_comment extends JFrame{

	private JFrame frame;
	private JTextField textField;
	
	private int writerID, elementID;
	boolean isBook;
	
	public Read_long_comment(Connection con, int writerID, int elementID, boolean isBook) {
		this.elementID = elementID;
		this.isBook = isBook;
		this.writerID = writerID;
		initialize(con);
		frame.setVisible(true);
	}


	private void initialize(Connection con) {
		String writerName = "";
		ResultSet resultSetUser = null;
		resultSetUser = Main.u.getBasicInfo(writerID);
		try {
			if(resultSetUser.next()) {
				writerName = resultSetUser.getString("USER_NICKNAME");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		ResultSet resultSetLongComment = null;
		String content = "";
		if(isBook) {
			resultSetLongComment = Main.u.getLongCommentBook(writerID, elementID);
			try {
				if(resultSetLongComment.next()) {
					content = resultSetLongComment.getString("LONG_COMMENT");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			resultSetLongComment = Main.u.getLongCommentMovie(writerID, elementID);
			try {
				if(resultSetLongComment.next()) {
					content = resultSetLongComment.getString("LONG_COMMENT");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 881, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6807\u9898");
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		label.setBounds(297, 25, 192, 44);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel(writerName);
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		label_1.setBounds(340, 80, 63, 21);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField(content);
		textField.setBounds(24, 112, 814, 400);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u8FD4\u56DE");
		btnNewButton.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		btnNewButton.setBounds(355, 534, 134, 35);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				try {
					if(isBook) {
						Book_info m =new Book_info(con,elementID);//tem!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					}else{
						Movie_info m = new Movie_info(con, elementID);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});
	}

}
