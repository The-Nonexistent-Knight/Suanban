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

public class Mark_History extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @param con 
	 */
	public Mark_History(Connection con) {
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		ResultSet resultSet = Main.u.getMarkedElement(Main.u.idUSER);
		ArrayList<String> elementName = new ArrayList<>();
		ArrayList<Integer> elementID = new ArrayList<>();
		ArrayList<String> elementType = new ArrayList<>();
		try {
			while(resultSet.next()) {
				elementID.add(resultSet.getInt("elementID"));
				elementName.add(resultSet.getString("name") + "(collected on " + resultSet.getString("d") +")");
				elementType.add(resultSet.getString("type"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList list = new JList(elementName.toArray());
		list.setBounds(36, 31, 278, 344);
		frame.getContentPane().add(list);
		
		JButton btnNewButton = new JButton("\u67E5\u770B");
		btnNewButton.setFont(new Font("ËÎÌו", Font.PLAIN, 25));
		btnNewButton.setBounds(326, 113, 103, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u8FD4\u56DE");
		button.setFont(new Font("ËÎÌו", Font.PLAIN, 25));
		button.setBounds(326, 244, 103, 39);
		frame.getContentPane().add(button);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					int eID = elementID.get(list.getSelectedIndex());
					boolean isBook = elementType.get(list.getSelectedIndex()).equals("B");
					try {
						if(isBook) {
							Book_info book_info = new Book_info(con, eID);
						}else {
							Movie_info movie_info = new Movie_info(con, eID);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
	}

}
