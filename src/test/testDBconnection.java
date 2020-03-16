package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class testDBconnection {

	Connection connection = null;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Database load successfully£¡");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    Connection getConn(){
        String url="jdbc:mysql://localhost:3306/suan_ban?useSSL=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        String userName="root";
        String passWord="123456";
        try {
            connection=DriverManager.getConnection(url,userName,passWord);
            if (connection!=null) {
                System.out.println("Connected to database successfully£¡");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public static void main(String[] args) throws SQLException {
    	testDBconnection test=new testDBconnection();
        Connection con=test.getConn();
        try {
            System.out.println(con.getCatalog());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Statement state=con.createStatement();
//        String s1="insert into movie values(1,'The Shawshank Redemption','1994-09-10',120,'English','D123',9.8,'A good movie',1000,'youku',0)";
//        state.executeUpdate(s1);
//        String s2="DELETE FROM movie WHERE MOVIE_RELEASED_DAY = '1994-09-10'";
//        state.executeUpdate(s2);
    }
}
