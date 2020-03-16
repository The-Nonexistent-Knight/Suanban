package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

public class warning extends JFrame {

	private JFrame frame;
	private int id;

	/**
	 * Launch the application.


	/**
	 * Create the application.
	 * @param iD 
	 * @param con 
	 */
	public warning(Connection con, int iD) {
		id=iD;
		initialize(con);
		frame.setVisible(true);
	}
	

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 283, 207);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u4F60\u6CA1\u6709\u6743\u9650\uFF01");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 25));
		label.setBounds(51, 29, 157, 42);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.setBounds(77, 94, 103, 25);
		frame.getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Li n=new Li(con, id);
			}
		});

		
	}
}
