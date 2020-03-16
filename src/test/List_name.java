package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;

public class List_name {

	private JFrame frame;
	private JTextField textField;


	public List_name(Connection con, boolean b) {
		initialize(con,b);
		frame.setVisible(true);
	}


	private void initialize(Connection con, boolean b) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(87, 104, 250, 48);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u8F93\u5165\u4E66/\u5F71\u5355\u540D\u79F0\uFF1A");
		label.setFont(new Font("ËÎÌו", Font.PLAIN, 25));
		label.setBounds(75, 37, 213, 45);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("\u5B8C\u6210");
		button.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		button.setBounds(158, 178, 103, 33);
		frame.getContentPane().add(button);
		
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=textField.getText();
				Main.u.createList(!b, name);
				frame.dispose();
				Create_list m =new Create_list(con, b, Main.u.getCurrentList(name));
			}
		});
	}
}
