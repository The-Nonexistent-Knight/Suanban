package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class testWindowsBuilderButton extends JFrame{
   
	private JFrame Main_frame;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					testWindowsBuilderButton window = new testWindowsBuilderButton();
//					window.Main_frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		Main_interface m=new Main_interface();
//		m.setVisible(true);
//	}

	/**
	 * Create the application.
	 * @param con 
	 */
 testWindowsBuilderButton(Connection con) {
	 Main_frame = new JFrame();
		Main_frame.setBounds(100, 100, 660, 540);
		Main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Main_frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("注册");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 25));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main_frame.dispose();
				User_registration m =new User_registration(con);
				m.setVisible(true);
			}
		});
		btnNewButton.setBounds(362, 340, 136, 47);
		Main_frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(247, 260, 180, 33);
		Main_frame.getContentPane().add(passwordField);
		
		
		JButton btnNewButton_1 = new JButton("登录");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 25));
		btnNewButton_1.setBounds(182, 340, 136, 47);
		Main_frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("蒜瓣");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 65));
		lblNewLabel.setBounds(258, 21, 136, 117);
		Main_frame.getContentPane().add(lblNewLabel);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(165, 188, 81, 22);
		Main_frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(177, 261, 63, 27);
		Main_frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setBounds(247, 179, 180, 33);
		Main_frame.getContentPane().add(textField);
		textField.setColumns(10);
		Main_frame.setVisible(true);	
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password=new String(passwordField.getPassword());
				if (Main.u.login(textField.getText(), password)) {
				Main_frame.dispose();			
				Main_interface m =new Main_interface(con);
				}else {
					Main_frame.dispose();
					Password_wrong m =new Password_wrong(con);
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */

}
