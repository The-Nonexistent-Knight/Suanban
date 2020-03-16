package test;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class Mark_Book extends JFrame{

	private JFrame frame;
	private int score,idBook;
	private String comment,state,bookName;


	public Mark_Book(Connection con,int idBook,String bookName) {
		score = 1;
		comment = "";
		state = "";
		this.bookName = bookName;
		this.idBook = idBook;
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		ResultSet resultSetComment = Main.u.getComment(Main.u.idUSER, idBook, true);
		try {
			if(resultSetComment.next()) {
				score = resultSetComment.getInt("SCORE");
				comment = resultSetComment.getString("SHORT_COMMENT");
				state = resultSetComment.getString("STATUS");
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		frame = new JFrame();
		frame.setBounds(100, 100, 540, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel(bookName);
		label.setBounds(155, 11, 218, 60);
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 30));
		frame.getContentPane().add(label);
		
		String[] sc={"1","2","3","4","5"};
		JComboBox comboBox = new JComboBox(sc);
		comboBox.setBounds(238, 156, 71, 22);
		comboBox.setFont(new Font("ËÎÌå", Font.PLAIN, 20));
		frame.getContentPane().add(comboBox);
		comboBox.setSelectedIndex(score - 1);
		
		JLabel label_1 = new JLabel("\u8BC4\u5206\uFF1A");
		label_1.setBounds(155, 156, 75, 24);
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		frame.getContentPane().add(label_1);
		
		JTextArea textArea = new JTextArea(comment);
		textArea.setBounds(68, 206, 388, 149);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("\u5B8C\u6210");
		btnNewButton.setBounds(210, 385, 103, 39);
		btnNewButton.setFont(new Font("Ó×Ô²", Font.PLAIN, 25));
		frame.getContentPane().add(btnNewButton);
		
		JRadioButton radioButton = new JRadioButton("\u60F3\u8BFB");
		radioButton.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		radioButton.setBounds(77, 85, 103, 39);
		frame.getContentPane().add(radioButton);
		radioButton.setSelected(state.equals("WANT_TO_READ"));
		
		JRadioButton radioButton_1 = new JRadioButton("\u5728\u8BFB");
		radioButton_1.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		radioButton_1.setBounds(206, 85, 103, 39);
		frame.getContentPane().add(radioButton_1);
		radioButton_1.setSelected(state.equals("READING"));
		
		JRadioButton radioButton_2 = new JRadioButton("\u8BFB\u8FC7");
		radioButton_2.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		radioButton_2.setBounds(341, 85, 103, 39);
		frame.getContentPane().add(radioButton_2);
		radioButton_2.setSelected(state.equals("READED"));
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		group.add(radioButton_2);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				try {
					if (radioButton.isSelected()) state="WANT_TO_READ"; else if (radioButton_1.isSelected())state="READING";else state="READED";
			        comment=textArea.getText();
			        score=comboBox.getSelectedIndex()+1;
			        Main.u.commentbook(idBook, state, comment, null, score);
					Book_info m =new Book_info(con,idBook);//tem!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});
	}
}
