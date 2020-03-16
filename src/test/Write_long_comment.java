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
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Write_long_comment extends JFrame{

	private JFrame frame;
	private JTextField textField;

	private boolean isBook;
	private int elementID;
	private String content;
	public Write_long_comment(Connection con, int elementID, boolean isBook) {
		this.elementID = elementID;
		this.isBook = isBook;
		content = "";
		initialize(con);
		frame.setVisible(true);
	}


	private void initialize(Connection con) {
		ResultSet resultSet = null;
		if(isBook) {
			resultSet = Main.u.getLongCommentBook(Main.u.idUSER, elementID);
			try {
				if(resultSet.next()) {
					content = resultSet.getString("LONG_COMMENT");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			resultSet = Main.u.getLongCommentMovie(Main.u.idUSER, elementID);
			try {
				if(resultSet.next()) {
					content = resultSet.getString("LONG_COMMENT");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 917, 654);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6807\u9898");
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		label.setBounds(207, 32, 63, 30);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		textField.setBounds(282, 26, 362, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5185\u5BB9\uFF1A");
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		label_1.setBounds(35, 68, 96, 42);
		frame.getContentPane().add(label_1);
		
		JTextArea textArea = new JTextArea(content);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		textArea.setBounds(35, 109, 825, 428);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("\u5B8C\u6210");
		btnNewButton.setFont(new Font("Ó×Ô²", Font.PLAIN, 25));
		btnNewButton.setBounds(381, 556, 114, 30);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					content = textArea.getText();
					if(isBook) {
						Main.u.longCommentBook(elementID, content);
					}else {
						Main.u.longCommentMovie(elementID, content);
					}
					if(isBook) {
						Book_info m =new Book_info(con,1);//!!!!!!!!!!!
					}else {
						Movie_info m = new Movie_info(con,1);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
	});
	}

}
