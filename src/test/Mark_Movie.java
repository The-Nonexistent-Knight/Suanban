package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class Mark_Movie extends JFrame{

	private JFrame frame;
	private String movieName;
	private int idMovie,score;
	private String state,comment;
	
	public Mark_Movie(Connection con, int idMovie, String movieName) {
		this.movieName = movieName;
		this.idMovie = idMovie;
		comment = "";
		score = 1;
		state = "";
		initialize(con);
		frame.setVisible(true);
	}


	private void initialize(Connection con) {
		ResultSet resultSetComment = Main.u.getComment(Main.u.idUSER, idMovie, false);
		try {
			if(resultSetComment.next()) {
				comment = resultSetComment.getString("SHORT_COMMENT");
				score = resultSetComment.getInt("SCORE");
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
		
		JLabel label = new JLabel(movieName);
		label.setFont(new Font("ËÎÌå", Font.PLAIN, 30));
		label.setBounds(68, 11, 373, 60);
		frame.getContentPane().add(label);
		
		String[] sc={"1","2","3","4","5"};
		JComboBox comboBox = new JComboBox(sc);
		comboBox.setFont(new Font("ËÎÌå", Font.PLAIN, 15));
		comboBox.setBounds(238, 156, 71, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel label_1 = new JLabel("\u8BC4\u5206\uFF1A");
		label_1.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		label_1.setBounds(155, 156, 75, 24);
		frame.getContentPane().add(label_1);
		
		JTextArea textArea = new JTextArea(comment);
		textArea.setBounds(68, 206, 388, 149);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton = new JButton("\u5B8C\u6210");
		btnNewButton.setFont(new Font("Ó×Ô²", Font.PLAIN, 25));
		btnNewButton.setBounds(210, 385, 103, 39);
		frame.getContentPane().add(btnNewButton);
		
		JRadioButton radioButton = new JRadioButton("\u60F3\u770B");
		radioButton.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		radioButton.setBounds(92, 78, 103, 39);
		frame.getContentPane().add(radioButton);
		radioButton.setSelected(state.equals("WANT_TO_WATCH"));
		
		JRadioButton radioButton_1 = new JRadioButton("\u770B\u8FC7");
		radioButton_1.setFont(new Font("ËÎÌå", Font.PLAIN, 25));
		radioButton_1.setBounds(291, 78, 103, 39);
		frame.getContentPane().add(radioButton_1);
		radioButton_1.setSelected(state.equals("WATCHED"));
		
        ButtonGroup bb = new ButtonGroup();
        bb.add(radioButton);
        bb.add(radioButton_1);
		
        comboBox.setSelectedIndex(score - 1);
		
        
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				try {
					if (radioButton.isSelected()) state="WANT_TO_WATCH"; else state="WATCHED";
			        comment=textArea.getText();
			        score=comboBox.getSelectedIndex() + 1;
					Movie_info m =new Movie_info(con, idMovie);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					Main.u.commentMovie(idMovie, state, comment, null, score);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});
	}
}
