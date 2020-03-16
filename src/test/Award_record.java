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

public class Award_record {

	private JFrame frame;
	private int idMovie;

	
	public Award_record(Connection con, int idMovie) {
		this.idMovie=idMovie;
		initialize(con);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param con 
	 */
	private void initialize(Connection con) {
		frame = new JFrame();
		frame.setBounds(100, 100, 402, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JList list = new JList();
		list.setBounds(60, 38, 243, 306);
		frame.getContentPane().add(list);
		
		ArrayList<String> awardList = new ArrayList<>();
		ArrayList<String> festivalID = new ArrayList<>();
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		try {
			resultSet=Main.b.getMovieAward(idMovie);
			
			while(resultSet.next()) {
				resultSet2=Main.b.getFestival(Integer.valueOf(resultSet.getString("AWARD_FESTIVAL")));
				while(resultSet2.next()) {
				awardList.add(resultSet2.getString("FESTIVAL_NAME")+":"+resultSet.getString("AWARD_NAME")+" Award");
				festivalID.add(resultSet.getString("AWARD_FESTIVAL"));
				}
			}list.setListData(awardList.toArray());
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		
	
		
		JButton button = new JButton("\u67E5\u770B");
		button.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		button.setBounds(40, 378, 103, 25);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.setFont(new Font("ËÎÌו", Font.PLAIN, 20));
		button_1.setBounds(200, 378, 103, 25);
		frame.getContentPane().add(button_1);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					int id=Integer.valueOf(festivalID.get(list.getSelectedIndex()));
					Festival m =new Festival(con,id);
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					frame.dispose();
					try {
						
						Movie_info m =new Movie_info(con,idMovie);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
	}
}
