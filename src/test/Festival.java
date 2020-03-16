package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Festival {

	private JFrame frame;
	private int idFestival;
	/**
	 * Create the application.
	 * @param con 
	 * @param id 
	 */
	public Festival(Connection con, int id) {
		this.idFestival=id;
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String festivalName = "";
		String festivalDate = "";
		ResultSet result = null;
		try {
			result=Main.b.getFestival(idFestival);
			
			if(result.next()) {
				festivalName=result.getString("FESTIVAL_NAME");
				festivalDate=result.getString("FESTIVAL_DATE");
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		
		JList list = new JList();
		list.setBounds(64, 71, 289, 318);
		frame.getContentPane().add(list);
		
		ArrayList<String> awardOfFestival = new ArrayList<>();
		ArrayList<String> movieID = new ArrayList<>();
		ResultSet resultSet=null;
		ResultSet resultSet2=null;
		try {
			resultSet=Main.b.getFestivalAward(idFestival);
			
			while(resultSet.next()) {
				resultSet2=Main.b.getMovieInfo(Integer.valueOf(resultSet.getString("AWARD_MOVIE")));
				while(resultSet2.next()) {
				awardOfFestival.add(resultSet.getString("AWARD_NAME")+" Award"+":"+resultSet2.getString("MOVIE_TITLE"));
				movieID.add(resultSet.getString("AWARD_MOVIE"));
				}
			}list.setListData(awardOfFestival.toArray());
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		JButton button = new JButton("\u67E5\u770B");
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(51, 410, 123, 33);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE\u4E3B\u9875");
		button_1.setFont(new Font("宋体", Font.PLAIN, 20));
		button_1.setBounds(230, 410, 123, 33);
		frame.getContentPane().add(button_1);
		
		JLabel label = new JLabel(festivalDate);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(22, 25, 84, 35);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel(festivalName);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 22));
		lblNewLabel.setBounds(99, 24, 283, 35);
		frame.getContentPane().add(lblNewLabel);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					int id=Integer.valueOf(movieID.get(list.getSelectedIndex()));
					try {
						Movie_info m =new Movie_info(con,id);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					Main_interface m =new Main_interface(con);
			}
		});
	}
}
