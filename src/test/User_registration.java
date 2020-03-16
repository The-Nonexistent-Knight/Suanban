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
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class User_registration extends JFrame{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_2;
	private JTextField textField_5;

	public User_registration(Connection con) {
		initialize(con);
		frame.setVisible(true);
	}

	private void initialize(Connection con) {
//		int age[]=new int[100];
//		for (int i=1;i<=99;i++) age[i]=i;
		
	
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 583);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(252, 106, 180, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(252, 169, 180, 32);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label = new JLabel("\u8D26\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(187, 111, 84, 24);
		frame.getContentPane().add(label);
		
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(187, 170, 80, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.setBounds(444, 112, 65, 25);
		frame.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u8D26\u53F7\u4E0D\u53EF\u7528\uFF0C\u8BF7\u91CD\u65B0\u8F93\u5165");
		label_1.setForeground(Color.RED);
		label_1.setBounds(250, 148, 200, 16);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5F53\u524D\u7528\u6237\u540D\u53EF\u7528");
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(252, 148, 210, 16);
		frame.getContentPane().add(label_2);
		
		label_1.setVisible(false);
		label_2.setVisible(false);
		
		JLabel label_3 = new JLabel("\u6027\u522B\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(188, 229, 65, 24);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u6635\u79F0\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(187, 290, 70, 24);
		frame.getContentPane().add(label_4);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(251, 288, 180, 32);
		frame.getContentPane().add(textField_3);
		
		JLabel label_5 = new JLabel("\u57CE\u5E02\uFF1A");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(187, 343, 70, 24);
		frame.getContentPane().add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(251, 341, 180, 32);
		frame.getContentPane().add(textField_4);
		
		JLabel label_6 = new JLabel("\u5E74\u9F84\uFF1A");
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		label_6.setBounds(187, 388, 70, 24);
		frame.getContentPane().add(label_6);
		
		
		
		JLabel label_8 = new JLabel("\u6CE8\u518C");
		label_8.setFont(new Font("华文行楷", Font.PLAIN, 40));
		label_8.setBounds(272, 34, 120, 52);
		frame.getContentPane().add(label_8);
		
		JButton button_1 = new JButton("\u5B8C\u6210\u6CE8\u518C");
		button_1.setFont(new Font("华文隶书", Font.PLAIN, 20));
		button_1.setBounds(252, 474, 140, 25);
		frame.getContentPane().add(button_1);
		
		JRadioButton radioButton = new JRadioButton("\u7537");
		radioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		radioButton.setBounds(252, 231, 45, 25);
		frame.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u5973");
		radioButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		radioButton_1.setBounds(347, 231, 45, 25);
		frame.getContentPane().add(radioButton_1);
		
		JLabel label_7 = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		label_7.setBounds(152, 439, 110, 24);
		frame.getContentPane().add(label_7);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(252, 431, 234, 32);
		frame.getContentPane().add(textField_2);
		
		textField_5 = new JTextField();
		textField_5.setBounds(252, 389, 76, 24);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Main.u.isAccountExist(textField.getText())) {label_1.setVisible(true); label_2.setVisible(false);}
				else {label_2.setVisible(true); label_1.setVisible(false);};
			}
		});
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButton_1.setSelected(false);
			}
		});
		radioButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radioButton.setSelected(false);
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gender=null;
				if (radioButton.isSelected())  gender="M"; else gender="F";
				Main.u.register(textField.getText(), textField_1.getText(), textField_3.getText(), gender, Integer.valueOf(textField_5.getText()), textField_4.getText(), textField_2.getText());
				frame.dispose();
				testWindowsBuilderButton u=new testWindowsBuilderButton(con);
			}
		});
	}
}
