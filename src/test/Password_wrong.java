package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

public class Password_wrong {

	private JFrame frame;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * @param con 
	 */
	public Password_wrong(Connection con) {
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
		
		JLabel label = new JLabel("\u5BC6\u7801\u9519\u8BEF!");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 25));
		label.setBounds(77, 30, 119, 42);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.setBounds(77, 94, 103, 25);
		frame.getContentPane().add(button);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				testWindowsBuilderButton u=new testWindowsBuilderButton(con);
			}
		});
	}

}
