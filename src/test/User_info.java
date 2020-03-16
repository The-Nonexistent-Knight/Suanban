package test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class User_info extends JFrame{

	private JFrame frame;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField textField_2;

	public User_info(Connection con) throws SQLException {
		initialize(con);
		frame.setVisible(true);
	}


	private void initialize(Connection con) throws SQLException{
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ResultSet rst=Main.u.getBasicInfo(Main.u.idUSER);
		rst.next();
		boolean gen=false;
		
		if(rst.getString("USER_GENDER").equals("M")) gen=true; else gen= false;
		
		textField_1 = new JTextField(rst.getString("USER_PASSWORD"));
		textField_1.setBounds(252, 169, 180, 32);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(187, 111, 84, 24);
		frame.getContentPane().add(label);
		
		
		
		JLabel label_4 = new JLabel("\u6635\u79F0\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(187, 290, 70, 24);
		frame.getContentPane().add(label_4);
		
		textField_3 = new JTextField(rst.getString("USER_NICKNAME"));
		textField_3.setColumns(10);
		textField_3.setBounds(251, 288, 180, 32);
		frame.getContentPane().add(textField_3);
		
		JLabel label_5 = new JLabel("\u57CE\u5E02\uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(187, 343, 70, 24);
		frame.getContentPane().add(label_5);
		
		textField_4 = new JTextField(rst.getString("USER_CITY"));
		textField_4.setColumns(10);
		textField_4.setBounds(251, 341, 180, 32);
		frame.getContentPane().add(textField_4);
		
		JLabel label_6 = new JLabel("\u5E74\u9F84\uFF1A");
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		label_6.setBounds(187, 388, 70, 24);
		frame.getContentPane().add(label_6);
		
		JLabel label_8 = new JLabel("\u4E2A\u4EBA\u4FE1\u606F");
		label_8.setFont(new Font("华文行楷", Font.PLAIN, 40));
		label_8.setBounds(272, 34, 160, 52);
		frame.getContentPane().add(label_8);
		
		JLabel lblNewLabel_1 = new JLabel(rst.getString("USER_ACCOUNT"));
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(252, 105, 179, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label_3 = new JLabel("\u6027\u522B\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(187, 227, 80, 24);
		frame.getContentPane().add(label_3);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u7537");
		rdbtnNewRadioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		rdbtnNewRadioButton.setBounds(252, 229, 54, 25);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("\u5973");
		radioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		radioButton.setBounds(346, 229, 54, 25);
		frame.getContentPane().add(radioButton);
		
		JButton btnNewButton = new JButton("\u5B8C\u6210\u4FEE\u6539");
		btnNewButton.setFont(new Font("隶书", Font.PLAIN, 20));
		btnNewButton.setBounds(272, 480, 133, 25);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(187, 169, 84, 24);
		frame.getContentPane().add(label_1);
		
		
		textField = new JTextField(Integer.toString(rst.getInt("USER_AGE")));
		textField.setBounds(252, 391, 70, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(145, 434, 109, 24);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField(rst.getString("USER_STATEMENT"));
		textField_2.setBounds(252, 437, 180, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose(); //13402
					String gender="";
					if (radioButton.isSelected()) gender="F"; else gender="M";
					Main.u.updateUserInfo(lblNewLabel_1.getText(), textField_1.getText(), textField_3.getText(), gender, Integer.valueOf(textField.getText()), textField_4.getText(), textField_2.getText());
					Main_interface m =new Main_interface(con);
					
			}
		});
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButton.setSelected(false);
			}
		});
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnNewRadioButton.setSelected(false);
			}
		});
		
		if (gen) rdbtnNewRadioButton.setSelected(true); else radioButton.setSelected(true);;
	} 

}
