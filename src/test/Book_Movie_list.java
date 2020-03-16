package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Book_Movie_list extends JFrame{

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_Movie_list window = new Book_Movie_list();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Book_Movie_list() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 963, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6211\u7684\u4E66/\u5F71\u5355");
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		label.setBounds(70, 94, 145, 58);
		frame.getContentPane().add(label);
		
		JList list = new JList();
		list.setBounds(23, 167, 237, 330);
		frame.getContentPane().add(list);
		
		JLabel label_1 = new JLabel("\u6536\u85CF\u7684\u4E66/\u5F71\u5355");
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		label_1.setBounds(330, 94, 177, 58);
		frame.getContentPane().add(label_1);
		
		JList list_1 = new JList();
		list_1.setBounds(294, 167, 237, 330);
		frame.getContentPane().add(list_1);
		
		JLabel label_2 = new JLabel("\u4E66/\u5F71\u5355\u68C0\u7D22");
		label_2.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		label_2.setBounds(624, 22, 177, 58);
		frame.getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		textField.setBounds(609, 79, 188, 37);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(820, 87, 103, 25);
		frame.getContentPane().add(btnNewButton);
		
		JList list_2 = new JList();
		list_2.setBounds(609, 167, 203, 330);
		frame.getContentPane().add(list_2);
		
		JButton button = new JButton("\u6536\u85CF");
		button.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		button.setBounds(832, 268, 103, 25);
		frame.getContentPane().add(button);
		
		JButton btnNewButton_1 = new JButton("\u521B\u5EFA\u4E66\u5355");
		btnNewButton_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		btnNewButton_1.setBounds(57, 46, 124, 37);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton button_1 = new JButton("\u521B\u5EFA\u5F71\u5355");
		button_1.setFont(new Font("Ó×Ô²", Font.PLAIN, 20));
		button_1.setBounds(320, 46, 124, 37);
		frame.getContentPane().add(button_1);
	}

}
