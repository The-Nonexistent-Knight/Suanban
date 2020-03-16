package test;
import javax.xml.crypto.Data;
import javax.xml.stream.events.DTD;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.SplittableRandom;
import java.util.StringTokenizer;

public class DatabaseManipulation implements DataManipulation {
    private static Connection con = null;
    ResultSet resultSet;
    private String dbname = "suan_ban";
    private String user = "root";
    private String pwd = "123456";


    @Override
    public void openDataSource() {
        try {
            // mysql 5.*
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver"); //mysql 8
        } catch (Exception e) {
            System.err.println("Cannot find the mysql driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = "jdbc:mysql://localhost:3306/suan_ban?useSSL=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        try {
            con = DriverManager.getConnection(url, user, pwd);
            if (!con.isClosed()) {
                System.out.println("Successfully connected to the database "
                        + dbname + " as " + user);
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public ArrayList<ArrayList<Object>> select(String column, String table, String cond) {
        String sql = "select "+column+" from "+table+" where "+cond;
        ArrayList<ArrayList<Object>> res = new ArrayList<>();
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData md = resultSet.getMetaData();
            int ccount = md.getColumnCount();
            while (resultSet.next()) {
                ArrayList<Object> c = new ArrayList<>();
                for (int i = 1; i <= ccount; i++)
                    c.add(resultSet.getObject(i));
                res.add(c);
            }
            if(res.isEmpty()){
                ArrayList<Object> c = new ArrayList<>();
                c.add(-1);
                res.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void closeDataSource() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int addOneAwardgetid(String award_name,int idfestival,int idmovie,int idpeople) {
        int result = 0;
        String sql = "insert into award (award_festival,award_movie,award_people,award_name) " +
                "values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, idfestival);
            preparedStatement.setInt(2, idmovie);
            preparedStatement.setInt(3, idpeople);
            preparedStatement.setString(4, award_name);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOneBookgetid(String title, String date, int page, int price, int layout
            , String publish, String isbn, String intro,int idcountry
    ) {
        int result = 0;
        String sql = "insert into book (book_title,book_publish_date,book_page_num," +
                "book_price,book_layout,book_publish,book_isbn,book_intro," +
                "book_score,book_recommand_num,book_country) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, title);
            if (date.equals("null")){
                preparedStatement.setString(2,  null);
            }else {
                preparedStatement.setInt(2, Integer.parseInt(date) );
            }
            preparedStatement.setInt(3, page);
            preparedStatement.setInt(4, price);
            preparedStatement.setInt(5, layout);
            preparedStatement.setString(6, publish);
            preparedStatement.setString(7, isbn);
            preparedStatement.setString(8, intro);
            preparedStatement.setDouble(9, 0);
            preparedStatement.setInt(10, 0);
            preparedStatement.setInt(11, idcountry);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int addOnebook_people(int idbook,int idpeople){
        int result = 0;
        String sql = "insert into book_people (book,people) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, idbook);
            preparedStatement.setInt(2, idpeople);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOnebook_tag(int idbook,int idtag){
        int result = 0;
        String sql = "insert into book_tag (book,tag) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, idbook);
            preparedStatement.setInt(2, idtag);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOneCountry(String countryname, String continent) {
        int result = 0;
        String sql = "insert into country (country_name,country_continent) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, countryname);
            preparedStatement.setString(2, continent);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOneFestival(String festival_name,int date,int idcountry, String city) {
        int result = 0;
        String sql = "insert into festival (festival_name,festival_date,festival_country,festival_city) " +
                "values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, festival_name);
            preparedStatement.setInt(2,  date);
            preparedStatement.setInt(3, idcountry);
            preparedStatement.setString(4, city);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOnefriend_user(int user1,int user2) {
        int result = 0;
        String sql = "insert into message (follower,following) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,user1);
            preparedStatement.setInt(2,user2);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void createList(boolean isBook, String listName,int iduser) {
        String sql = "insert into suan_ban.list (LIST_NAME, LIST_USER, LIST_DATE, LIST_TYPE) values (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, listName);
            preparedStatement.setInt(2, iduser);
            preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setString(4, isBook ? "B" : "M");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int addOnelist_book(int idlist,int idbook){
        int result = 0;
        String sql = "insert into list_book (list,book) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, idlist);
            preparedStatement.setInt(2, idbook);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOnelist_movie(int idlist,int idmovie){
        int result = 0;
        String sql = "insert into list_movie (list,movie) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, idlist);
            preparedStatement.setInt(2, idmovie);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOnelist_user_collection(int idlist,int iduser){
        int result = 0;
        String sql = "insert into list_user_collection (list,user) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, idlist);
            preparedStatement.setInt(2, iduser);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOneMessage(int user1,int user2,String content, String time) {
        int result = 0;
        String sql = "insert into message (send,recieve,content,time,idmessage) " +
                "values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,user1);
            preparedStatement.setInt(2,user2);
            preparedStatement.setString(3, content);
            preparedStatement.setTime(4, Time.valueOf(time));
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public int addOneUser(String account, String password, String nickname, String gender, int age,
                          String city, String statement) {
        int result = 0;
        String sql = "insert into user (user_account,user_password,user_nickname,user_gender," +
                "user_age,user_city,user_statement) " +
                "values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nickname);
            preparedStatement.setString(4, gender);
            preparedStatement.setInt(5, age);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, statement);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOneMoviegetid(String title, String date, String runtime, String language,
                                String imdb, String intro, String link, int is,int idcountry
    ) {
        int result = 0;
        String sql = "insert into movie (movie_title,movie_released_day," +
                "movie_runtime,movie_language,movie_imdb,movie_score," +
                "movie_intro,movie_recommand_num,movie_source_link,movie_is_running,movie_country) " +
                "values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, title);
            if (date.equals("null")){
                preparedStatement.setString(2,  null);
            }else {
                preparedStatement.setInt(2, Integer.parseInt(date) );
            }
            if (runtime.equals("null")){
                preparedStatement.setString(3, null);
            }else {
                preparedStatement.setInt(3, Integer.parseInt(runtime));
            }
            preparedStatement.setString(4, language);
            preparedStatement.setString(5, imdb);
            preparedStatement.setDouble(6, 0.0);
            preparedStatement.setString(7, intro);
            preparedStatement.setInt(8, 0);
            preparedStatement.setString(9, link);
            preparedStatement.setInt(10, is);
            preparedStatement.setInt(11, idcountry);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOnemovie_people(int idmovie,int idpeople,String type){
        int result = 0;
        String sql = "insert into movie_people (movie,people,type) " +
                "values (?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, idmovie);
            preparedStatement.setInt(2, idpeople);
            preparedStatement.setString(3, type);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOnePeople(String firstname, String surname, String gender, String birthday, String birthplace,
                            String constellation, String intro, int idcountry) {
        int result = 0;
        String sql = "insert into people (people_first_name_foreigner,people_surname_foreigner,people_gender," +
                "people_birthday,people_birth_place,people_constellation,people_intro," +
                "people_country) " +
                "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, firstname);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, gender);
            preparedStatement.setInt(4, Integer.parseInt(birthday) );
            preparedStatement.setString(5, birthplace);
            preparedStatement.setString(6, constellation);
            preparedStatement.setString(7, intro);
            preparedStatement.setInt(8, idcountry);

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addOneTaggetid(String tag_name) {
        int result = 0;
        String sql = "insert into tag (tag_name) " +
                "values (?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, tag_name);
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<Object>>x= select("idtag","tag","tag_name='"+tag_name+"'");
        int id=(Integer)x.get(0).get(0);
        return id;
    }

    @Override
    public int addOnetag_movie(int idtag,int idmovie){
        int result = 0;
        String sql = "insert into tag_movie (tag,movie) " +
                "values (?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setInt(1, idtag);
            preparedStatement.setInt(2, idmovie);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

 @Override
    public void commentMovie(int idUSER,int movieID, String status, String shortComment, String longComment, int score) {
        boolean isExist = false;
        try{
            ResultSet resultSet = null;
            String sql = "SELECT * from suan_ban.user_comment_movie where MOVIE = " + movieID + "&& USER =" + idUSER;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            isExist = resultSet.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(!isExist) {
            String sql = "INSERT INTO suan_ban.user_comment_movie(USER, MOVIE, LONG_COMMENT, STATUS, LIKE_NUM, SHORT_COMMENT, WATCHED_TIME, WANT_TO_WATCH, SCORE) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, idUSER);
                preparedStatement.setInt(2, movieID);
                if (longComment == null)
                    preparedStatement.setNull(3, Types.VARCHAR);
                else
                    preparedStatement.setString(3, longComment);
                preparedStatement.setString(4, status);
                preparedStatement.setInt(5, 0);
                if (shortComment == null)
                    preparedStatement.setNull(6, Types.VARCHAR);
                else
                    preparedStatement.setString(6, shortComment);
                if (status.equals("WANT_TO_WATCH")) {
                    preparedStatement.setNull(7, Types.DATE);
                    preparedStatement.setDate(8, new java.sql.Date(System.currentTimeMillis()));
                } else {
                    preparedStatement.setNull(8, Types.DATE);
                    preparedStatement.setDate(7, new java.sql.Date(System.currentTimeMillis()));
                }
                preparedStatement.setInt(9, score);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();

            }
            //changeScoreMovie(movieID, 0, score);
        }else{
            int prevScore = 0;
            String sqlPrevScore = "select MOVIE_SCORE from suan_ban.movie where idMOVIE = " + movieID;
            try{
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlPrevScore);
                if(resultSet.next()){
                    prevScore = resultSet.getInt(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            String sql =String.format("update suan_ban.user_comment_movie SET LONG_COMMENT = ?, STATUS = ?, SHORT_COMMENT = ?, WATCHED_TIME = %s, WANT_TO_WATCH = %s, SCORE = ? " +
                    "where MOVIE = " + movieID + " && USER = " + idUSER, status.equals("WANT_TO_WATCH") ? "WATCHED_TIME" : "?", status.equals("WATCHED") ? "WANT_TO_WATCH" : "?") ;
            try{
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, longComment);
                preparedStatement.setString(2, status);
                preparedStatement.setString(3, shortComment);
                if (status.equals("WANT_TO_WATCH")) {
                    preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                } else {
                    preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                }
                preparedStatement.setInt(5, score);
                preparedStatement.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
            //changeScoreMovie(movieID, prevScore, score);
        }
    }

    @Override
    public void commentbook(int idUSER,int bookID, String status, String shortComment, String longComment, int score) {
        boolean isExist = false;
        try{
            ResultSet resultSet = null;
            String sql = "SELECT * from suan_ban.user_comment_book where BOOK = " + bookID + "&& USER =" + idUSER;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            isExist = resultSet.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(!isExist) {
            String sql = "INSERT INTO suan_ban.user_comment_book(USER, BOOK, LONG_COMMENT, STATUS, LIKE_NUM, SHORT_COMMENT, " +
                    "WANT_TO_READ_TIME, READING_TIME, READED_TIME, SCORE) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, idUSER);
                preparedStatement.setInt(2, bookID);
                if (longComment == null)
                    preparedStatement.setNull(3, Types.VARCHAR);
                else
                    preparedStatement.setString(3, longComment);
                preparedStatement.setString(4, status);
                preparedStatement.setInt(5, 0);
                if (shortComment == null)
                    preparedStatement.setNull(6, Types.VARCHAR);
                else
                    preparedStatement.setString(6, shortComment);
                if (status.equals("WANT_TO_READ")) {
                    preparedStatement.setNull(8, Types.DATE);
                    preparedStatement.setNull(9, Types.DATE);
                    preparedStatement.setDate(7, new java.sql.Date(System.currentTimeMillis()));
                } else if(status.equals("READING")) {
                    preparedStatement.setNull(7, Types.DATE);
                    preparedStatement.setDate(8, new java.sql.Date(System.currentTimeMillis()));
                    preparedStatement.setNull(9, Types.DATE);
                }else{
                    preparedStatement.setNull(7, Types.DATE);
                    preparedStatement.setDate(9, new java.sql.Date(System.currentTimeMillis()));
                    preparedStatement.setNull(8, Types.DATE);
                }
                preparedStatement.setInt(10, score);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //changeScoreBook(bookID, 0, score);
        }else{
            int prevScore = 0;
            String sqlPrevScore = "select BOOK_SCORE from suan_ban.book where idBOOK = " + bookID;
            try{
                Statement statement = con.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlPrevScore);
                if(resultSet.next()){
                    prevScore = resultSet.getInt(1);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            String sql =String.format("update suan_ban.user_comment_book SET LONG_COMMENT = ?, STATUS = ?, SHORT_COMMENT = ?, " +
                            "WANT_TO_READ_TIME = %s, READING_TIME = %s, READED_TIME = %s ,SCORE = ? " +
                            "where BOOK = " + bookID + " && USER = " + idUSER,
                    !status.equals("WANT_TO_READ") ? "WANT_TO_READ_TIME" : "?",
                    !status.equals("READING") ? "READING_TIME" : "?",
                    !status.equals("READED") ? "READED_TIME" : "?") ;
            try{
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, longComment);
                preparedStatement.setString(2, status);
                preparedStatement.setString(3, shortComment);
                preparedStatement.setDate(4,new Date(System.currentTimeMillis()));
                preparedStatement.setInt(5, score);
                preparedStatement.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
            }
            //changeScoreBook(bookID, prevScore, score);
        }
    }
}
