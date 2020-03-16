package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Long_Comment_list extends JFrame{

	private JFrame frame;

	private boolean isBook;
	private int elementID;
	public Long_Comment_list(Connection con, int elementID, boolean isBook) {
		this.isBook = isBook;
		this.elementID = elementID;
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 427, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ResultSet resultSet = null;
		ArrayList<String> arrayListContent = new ArrayList<>();
		ArrayList<Integer> arrayListUserID = new ArrayList<>();
		if(isBook) {
			try {
				resultSet = Main.u.getLongCommentBook(elementID);
				while(resultSet.next()) {
					arrayListContent.add(resultSet.getString("LONG_COMMENT").substring(0, Math.min(resultSet.getString("LONG_COMMENT").length(), 20)) + "...");
					arrayListUserID.add(resultSet.getInt("USER"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			resultSet = Main.u.getLongCommentMovie(elementID);
			try {
				while(resultSet.next()) {
					String addContent = resultSet.getString("LONG_COMMENT").substring(0, Math.min(resultSet.getString("LONG_COMMENT").length(), 20)) + "...";
					arrayListContent.add(addContent);
					arrayListUserID.add(resultSet.getInt("USER"));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		JList list = new JList(arrayListContent.toArray());
		list.setBounds(57, 28, 293, 365);
		frame.getContentPane().add(list);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		button.setBounds(140, 428, 103, 25);
		frame.getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				int writerID = arrayListUserID.get(list.getSelectedIndex());
				
				Read_long_comment m =new Read_long_comment(con, writerID, elementID, isBook);
		}
	});
		
	}
	}


