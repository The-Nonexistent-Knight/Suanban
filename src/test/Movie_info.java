package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Movie_info extends JFrame{

	private JFrame frame;
	private int idMovie;
	private String movieName,release_day,runtime,language,IMDB,score,intro,sourcelink;

	public Movie_info(Connection con, int eID) throws SQLException{
		this.idMovie = eID;
		movieName = null;
		initialize(con);
		frame.setVisible(true);
	}

	private void initialize(Connection con) throws SQLException{
		ArrayList<String> tagList = new ArrayList<>();
		String tag ="";
		ResultSet resultSet000=null;
		try {
			resultSet000=Main.b.getMovieTag(idMovie);
			while(resultSet000.next()) {
				tagList.add(resultSet000.getString("TAG_NAME"));	
			}
			for(int i=0;i<tagList.size();i++) {
				if(i<tagList.size()-1) tag+=tagList.get(i)+"/";
				else tag+=tagList.get(i);
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		String country="";
		ResultSet resultSet001=null;
		try {
			resultSet001=Main.b.getMovieCountry(idMovie);
			if(resultSet001.next()) country=resultSet001.getString("COUNTRY_CONTINENT")+" "+resultSet001.getString("COUNTRY_NAME");
			
			
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		ResultSet rst=Main.b.getMovieInfo(idMovie);
		rst.next();
		movieName=rst.getString("MOVIE_TITLE");
		release_day=rst.getString("MOVIE_RELEASED_DAY");
		runtime=rst.getString("MOVIE_RUNTIME");
		language=rst.getString("MOVIE_LANGUAGE");
		IMDB=rst.getString("MOVIE_IMDB");
		score=rst.getString("final_score");
		intro=rst.getString("MOVIE_INTRO");
		sourcelink=rst.getString("MOVIE_SOURCE_LINK");
		frame = new JFrame();
		frame.setBounds(100, 100, 1111, 806);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(movieName);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 45));
		lblNewLabel.setBounds(46, -9, 524, 112);
		frame.getContentPane().add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(394, 242, 152, 100);
		frame.getContentPane().add(list);
		
		JList list_2 = new JList();
		list_2.setBounds(394, 92, 152, 100);
		frame.getContentPane().add(list_2);
		
		ArrayList<String> director = new ArrayList<>();
		ArrayList<String> actor = new ArrayList<>();
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			resultSet=Main.b.getMoviePeopleDirector(idMovie);
			while(resultSet.next()) {
				director.add(resultSet.getString("PEOPLE_FIRST_NAME_FOREIGNER")+resultSet.getString("PEOPLE_SURNAME_FOREIGNER"));	
			}
			
			if(!director.isEmpty()) {
			list_2.setListData(director.toArray());}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		try {
			resultSet2=Main.b.getMoviePeopleActor(idMovie);
			while(resultSet2.next()) {
				actor.add(resultSet2.getString("PEOPLE_FIRST_NAME_FOREIGNER")+resultSet2.getString("PEOPLE_SURNAME_FOREIGNER"));
			}
			if(!actor.isEmpty()) list.setListData(actor.toArray());
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		getContentPane();
		JLabel label = new JLabel("\u5BFC\u6F14");
		label.setFont(new Font("宋体", Font.PLAIN, 25));
		label.setBounds(309, 131, 67, 35);
		frame.getContentPane().add(label);
		
		JLabel label_2 = new JLabel("\u6F14\u5458");
		label_2.setFont(new Font("宋体", Font.PLAIN, 25));
		label_2.setBounds(309, 230, 67, 35);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u4E0A\u6620\u65E5\u671F"+":"+release_day);
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(46, 298, 299, 35);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u7C7B\u578B:"+tag);
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		label_4.setBounds(46, 362, 325, 35);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u7247\u957F"+":"+runtime+" min");
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		label_5.setBounds(46, 422, 170, 35);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u5730\u533A:"+country);
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		label_6.setBounds(46, 483, 274, 35);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u8BED\u8A00"+":"+language);
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		label_7.setBounds(46, 542, 170, 35);
		frame.getContentPane().add(label_7);
		
		JLabel lblImdb = new JLabel("IMDB\u7F16\u7801"+":"+IMDB);
		lblImdb.setFont(new Font("宋体", Font.PLAIN, 20));
		lblImdb.setBounds(46, 604, 229, 35);
		frame.getContentPane().add(lblImdb);
		
		JTextArea textArea = new JTextArea(intro);
		textArea.setBounds(332, 395, 280, 282);
		frame.getContentPane().add(textArea);
		
		JLabel label_8 = new JLabel("\u8BC4\u5206"+":"+score);
		label_8.setFont(new Font("宋体", Font.PLAIN, 25));
		label_8.setBounds(32, 121, 201, 45);
		frame.getContentPane().add(label_8);
		
		ResultSet rSetComment = Main.u.getComment(idMovie, false);
		String[] contentComment = new String[3];
		int[] commentUserId = new int[3];
		int index = 0;
		while(rSetComment.next()) {
			commentUserId[index] = rSetComment.getInt("USER");
			contentComment[index++] = rSetComment.getString("SHORT_COMMENT");
			if(index == 3)
				break;
		}
		
		JTextArea textArea_1 = new JTextArea(contentComment[0]);
		textArea_1.setBounds(711, 46, 232, 137);
		frame.getContentPane().add(textArea_1);
		
		JTextArea textArea_2 = new JTextArea(contentComment[1]);
		textArea_2.setBounds(711, 261, 232, 137);
		frame.getContentPane().add(textArea_2);
		
		JTextArea textArea_3 = new JTextArea(contentComment[2]);
		textArea_3.setBounds(711, 462, 232, 137);
		frame.getContentPane().add(textArea_3);
		
		JButton btnNewButton = new JButton("\u70B9\u8D5E");
		btnNewButton.setBounds(955, 107, 67, 25);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("\u70B9\u8D5E");
		button.setBounds(955, 317, 67, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u70B9\u8D5E");
		button_1.setBounds(955, 514, 67, 25);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u83B7\u5956\u7ECF\u5386");
		button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		button_2.setBounds(32, 665, 126, 35);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u6807\u8BB0");
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		button_3.setBounds(52, 209, 106, 45);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u5199\u957F\u8BC4");
		button_4.setFont(new Font("宋体", Font.PLAIN, 20));
		button_4.setBounds(711, 693, 106, 25);
		frame.getContentPane().add(button_4);
		
		JButton button_5 = new JButton("\u770B\u957F\u8BC4");
		button_5.setFont(new Font("宋体", Font.PLAIN, 20));
		button_5.setBounds(859, 693, 106, 25);
		frame.getContentPane().add(button_5);
		
		JButton button_6 = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button_6.setFont(new Font("宋体", Font.PLAIN, 25));
		button_6.setBounds(377, 708, 152, 35);
		frame.getContentPane().add(button_6);
		
		JTextArea textArea_4 = new JTextArea();
		textArea_4.setBounds(738, 623, 249, 32);
		frame.getContentPane().add(textArea_4);
		textArea_4.setText(sourcelink);
		
		JLabel label_1 = new JLabel("\u64AD\u653E\u7F51\u5740\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(637, 623, 115, 35);
		frame.getContentPane().add(label_1);
		
		button_3.addActionListener(new ActionListener() {//标记电影
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Mark_Movie m =new Mark_Movie(con, idMovie, movieName);
			}
		});
		
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
		
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				Long_Comment_list m =new Long_Comment_list(con, idMovie, false);
		}
	});

		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Write_long_comment m =new Write_long_comment(con,idMovie, false);
			}
		});
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Award_record m =new Award_record(con,idMovie);
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {//点赞1
			public void actionPerformed(ActionEvent e) {
					Main.u.likeComment(commentUserId[0], idMovie, false);
			}
		});
		
		button.addActionListener(new ActionListener() {//点赞2
			public void actionPerformed(ActionEvent e) {
					Main.u.likeComment(commentUserId[1], idMovie, false);
			}
		});
		
		button_1.addActionListener(new ActionListener() {//点赞3
			public void actionPerformed(ActionEvent e) {
					Main.u.likeComment(commentUserId[2], idMovie, false);
			}
		});
	}
	}
