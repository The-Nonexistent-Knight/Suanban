package test;
import java.sql.*;


public class USER implements UserOperation{
    public int idUSER;
    public static Connection con = null;

    public USER(Connection conn){
        con = conn;

    }

    @Override
    public boolean isAccountExist(String account) {
        String sql = "select * from suan_ban.user where USER_ACCOUNT = '" + account + "'";
        ResultSet resultSet = null;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean register(String account, String password, String nikeName, String gender, int age, String city, String statement) {
        String sql = "insert into suan_ban.USER (USER_ACCOUNT, USER_PASSWORD, USER_NICKNAME, USER_GENDER, USER_AGE, USER_CITY, USER_STATEMENT)" +
                "values(?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nikeName);
            preparedStatement.setString(4, gender);
            if(age != -1)
                preparedStatement.setInt(5, age);
            else
                preparedStatement.setNull(5, Types.INTEGER);
            preparedStatement.setString(6 ,city);
            preparedStatement.setString(7, statement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateUserInfo(String account, String password, String nikeName, String gender, int age, String city, String statement) {
        String sql = "update suan_ban.user set USER_ACCOUNT = ?, USER_PASSWORD = ?, USER_NICKNAME = ?, " +
                "USER_GENDER = ?, USER_AGE = ?, USER_CITY = ?, USER_STATEMENT = ? where idUSER = " + idUSER;

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nikeName);
            preparedStatement.setString(4, gender);
            if(age != -1)
                preparedStatement.setInt(5, age);
            else
                preparedStatement.setNull(5, Types.INTEGER);
            preparedStatement.setString(6 ,city);
            preparedStatement.setString(7, statement);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean login(String account, String password) {
        String sql = "select idUSER from suan_ban.user where USER_ACCOUNT = ? AND USER_PASSWORD = ?";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, account);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                idUSER = resultSet.getInt("idUSER");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public ResultSet getBasicInfo(int userID) {
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.user where idUSER = " + userID;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet searchUSER(String nickname) {
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.user where USER_NICKNAME = '" + nickname +"'";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public void createList(boolean isBook, String listName) {
        String sql = "insert into suan_ban.list (LIST_NAME, LIST_USER, LIST_DATE, LIST_TYPE) values (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, listName);
            preparedStatement.setInt(2, this.idUSER);
            preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setString(4, isBook ? "B" : "M");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void collectList(boolean add, int listID) {
        if(add) {
            String sql = "insert into suan_ban.list_user_collection(LIST, USER) VALUES (?, ?)";
            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, listID);
                preparedStatement.setInt(2, this.idUSER);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            boolean isCreat = false;
            ResultSet resultSet = null;
            String sql1 = "select * from suan_ban.list where LIST_USER = "+ idUSER + " && idLIST = " + listID;
            try {
                Statement statement = con.createStatement();
                resultSet = statement.executeQuery(sql1);
                isCreat = resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(!isCreat) {
                String sql = "delete FROM suan_ban.list_user_collection WHERE LIST = ? and user = ?";
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setInt(1, listID);
                    preparedStatement.setInt(2, this.idUSER);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                String sql2 = "delete FROM suan_ban.list_user_collection WHERE LIST = ?";
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(sql2);
                    preparedStatement.setInt(1, listID);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String sql = "delete FROM suan_ban.list WHERE idLIST = ? and LIST_USER = ?";
                try {
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setInt(1, listID);
                    preparedStatement.setInt(2, this.idUSER);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ResultSet findList(String name) {
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.list where LIST_NAME like '%" + name + "%';";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public int getCurrentList(String name) {
        ResultSet resultSet = null;
        String sql = "select idLIST, LIST_USER from suan_ban.list where LIST_NAME = '" + name + "';";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int getListCreator(int idLIST) {
        ResultSet resultSet = null;
        String sql = "select LIST_USER from suan_ban.list where idLIST = " + idLIST + ";";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public boolean isCollectList(int listID) {
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.list_user_collection where LIST =" + listID + "&& USER = " + this.idUSER;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet getCollectList() {
        ResultSet resultSet = null;
        String sql = "(select l.* from suan_ban.list_user_collection lc join suan_ban.list l on lc.LIST = l.idLIST " +
                "where lc.USER = " + idUSER + ") UNION (SELECT * from suan_ban.list where LIST_USER = " + idUSER + ");";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public String typeOfList(int listID){
        String list_type = "";
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.list where idLIST =" +listID;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next())
                list_type = resultSet.getString("LIST_TYPE");
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list_type;
    }

    @Override
    public ResultSet elementsInList(int listID) {

        String list_type = typeOfList(listID);
        ResultSet resultSet = null;
        String sql2;
        try {
            PreparedStatement preparedStatement = null;
            if(list_type.equals("M")){
                sql2 = "select m.* from (suan_ban.list_movie l join suan_ban.movie m on l.MOVIE = m.idMOVIE) where LIST = ?";
                preparedStatement = con.prepareStatement(sql2);
                preparedStatement.setInt(1, listID);
            }
            if(list_type.equals("B")){
                sql2 = "select b.* from suan_ban.book b join suan_ban.list_book on b.idBOOK = list_book.BOOK where LIST = ?";
                preparedStatement = con.prepareStatement(sql2);
                preparedStatement.setInt(1, listID);
            }

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public void editList(int listID, boolean add, int addElementID) {
        ResultSet resultSet = null;
        String list_type = typeOfList(listID);
        if(add){
            String sql2;
            try {
                PreparedStatement preparedStatement = null;
                if(list_type.equals("M")){
                    sql2 = "insert into suan_ban.list_movie (LIST, MOVIE) values (?, ?)";
                    preparedStatement = con.prepareStatement(sql2);
                }
                if(list_type.equals("B")){
                    sql2 = "insert into suan_ban.list_book (LIST, BOOK) values (?, ?)";
                    preparedStatement = con.prepareStatement(sql2);
                }
                preparedStatement.setInt(1, listID);
                preparedStatement.setInt(2, addElementID);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql2;
            try {
                PreparedStatement preparedStatement = null;
                if(list_type.equals("M")){
                    sql2  = "delete FROM suan_ban.list_movie WHERE LIST = ? and MOVIE = ?";
                    preparedStatement = con.prepareStatement(sql2);
                    preparedStatement.setInt(1, listID);
                    preparedStatement.setInt(2, addElementID);
                }
                if(list_type.equals("B")){
                    sql2  = "delete FROM suan_ban.list_book WHERE LIST = ? and BOOK = ?";
                    preparedStatement = con.prepareStatement(sql2);
                    preparedStatement.setInt(1, listID);
                    preparedStatement.setInt(2, addElementID);
                }
                preparedStatement.executeUpdate();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void likeMovie(int movieID) {
        String sql = "update suan_ban.movie set MOVIE_RECOMMAND_NUM = MOVIE_RECOMMAND_NUM + 1 where idMOVIE = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, movieID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commentMovie(int movieID, String status, String shortComment, String longComment, int score) {
        boolean isExist = false;
        try{
            ResultSet resultSet = null;
            String sql = "SELECT * from suan_ban.user_comment_movie where MOVIE = " + movieID + "&& USER =" + this.idUSER;
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
                preparedStatement.setInt(1, this.idUSER);
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
                    "where MOVIE = " + movieID + " && USER = " + this.idUSER, status.equals("WANT_TO_WATCH") ? "WATCHED_TIME" : "?", status.equals("WATCHED") ? "WANT_TO_WATCH" : "?") ;
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
    public void commentbook(int bookID, String status, String shortComment, String longComment, int score) {
        boolean isExist = false;
        try{
            ResultSet resultSet = null;
            String sql = "SELECT * from suan_ban.user_comment_book where BOOK = " + bookID + "&& USER =" + this.idUSER;
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
                preparedStatement.setInt(1, this.idUSER);
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
                            "where BOOK = " + bookID + " && USER = " + this.idUSER,
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

    @Override
    public void longCommentBook(int bookID, String longComment) {
        boolean isExist = false;
        try{
            ResultSet resultSet = null;
            String sql = "SELECT * from suan_ban.user_comment_book where BOOK = " + bookID + " && USER = " + this.idUSER;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            isExist = resultSet.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(!isExist) {
            String sql = "insert into suan_ban.user_comment_book (USER, BOOK, STATUS, LIKE_NUM, READED_TIME) values (" +
                    + idUSER + ", " + bookID + ", " + "'READED'" + ", " + 0 + " ,current_date" +
                    ");";
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(sql);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            String sql = "update suan_ban.user_comment_book set LONG_COMMENT = '" + longComment + "'" + "where BOOK = " +
                    bookID + " AND USER = " + idUSER;
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(sql);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void longCommentMovie(int movieID, String longComment) {
        boolean isExist = false;
        try{
            ResultSet resultSet = null;
            String sql = "SELECT * from suan_ban.user_comment_movie where MOVIE = " + movieID + "&& USER =" + this.idUSER;
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            isExist = resultSet.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        if(!isExist) {
            String sql = "insert into suan_ban.user_comment_movie (USER, MOVIE, STATUS, LIKE_NUM, WATCHED_TIME) values (" +
                    + idUSER + ", " + movieID + ", " + "'WATCHED'" + ", " + 0 + ", current_date " +
                    ");";
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(sql);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }else {
            String sql = "update suan_ban.user_comment_movie set LONG_COMMENT = '" + longComment + "'" + "where MOVIE = " +
                    movieID + " AND USER = " + idUSER;
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(sql);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public ResultSet getLongCommentMovie(int userID, int movieID){
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.user_comment_movie where USER = " + userID + " && MOVIE = " + movieID +
                " AND LONG_COMMENT is not null ";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getLongCommentMovie(int movieID){
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.user_comment_movie where MOVIE = " + movieID +
                " AND LONG_COMMENT is not null ";;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getLongCommentBook(int userID, int bookID){
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.user_comment_book where USER = " + userID + " && BOOK = " + bookID +
                " AND LONG_COMMENT is not null ";;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getLongCommentBook(int bookID){
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.user_comment_book where BOOK = " + bookID +
                " AND LONG_COMMENT is not null ";;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

//    private void changeScoreBook(int bookID, int prevScore, int afterScore){
//        String sql = "update suan_ban.book set BOOK_SCORE = BOOK_SCORE - ? + ? where idBOOK = ?";
//        try {
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setInt(1, prevScore);
//            preparedStatement.setInt(2, afterScore);
//            preparedStatement.setInt(3, bookID);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void changeScoreMovie(int movieID, int prevScore, int afterScore){
//        String sql = "update suan_ban.movie set MOVIE_SCORE = MOVIE_SCORE - ? + ? where idMOVIE = ?";
//        try {
//            PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setInt(1, prevScore);
//            preparedStatement.setInt(2, afterScore);
//            preparedStatement.setInt(3, movieID);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void likeComment(int userID, int elementID, boolean isBook) {
        if(isBook) {
            String sql = "update suan_ban.user_comment_book set LIKE_NUM = LIKE_NUM + 1 where BOOK = " +
                    elementID + " AND USER = " + userID;
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql = "update suan_ban.user_comment_movie set LIKE_NUM = LIKE_NUM + 1 where MOVIE = " +
                    elementID + " AND USER = " + userID;
            try {
                Statement statement = con.createStatement();
                statement.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void sentMessage(int userID, String content) {
        String sql = "insert into suan_ban.message(SEND, RECIEVE, CONTENT, TIME) values (?,?,?,?);";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, this.idUSER);
            preparedStatement.setInt(2, userID);
            preparedStatement.setString(3, content);
            Time time = new Time(System.currentTimeMillis());
            preparedStatement.setTime(4, time);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet getMessage(int userID) {
        ResultSet resultSet = null;
        String sql = "select * from suan_ban.message where (SEND = " + this.idUSER + " AND RECIEVE = " + userID
                + ") OR (SEND = " + userID + " AND RECIEVE = " + this.idUSER + ")"
                + " ORDER BY " +
                "TIME ASC";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getFriendBookList(int friendID) {
        ResultSet resultSet = null;
        String sql = "select l.LIST_NAME, BOOK.* from suan_ban.list l join suan_ban.list_book b on l.idLIST = b.LIST" +
                " join suan_ban.book on b.BOOK = book.idBOOK where l.LIST_USER = " + friendID
                + " order by idLIST DESC";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getFriendMovieList(int friendID) {
        ResultSet resultSet = null;
        String sql = "select l.LIST_NAME, MOVIE.* from suan_ban.list l join suan_ban.list_movie m on l.idLIST = m.LIST" +
                " join suan_ban.movie on m.MOVIE = movie.idMOVIE where l.LIST_USER = " + friendID
                + " order by idLIST DESC";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getComment(int elementID, boolean isBOOK) {
        ResultSet resultSet = null;
        if(!isBOOK) {
            String sql = "SELECT * from suan_ban.user_comment_movie c join suan_ban.user on c.USER = user.idUSER" +
                    " where c.MOVIE = " + elementID + " order by LIKE_NUM desc";
            try {
                Statement statement = con.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql = "SELECT * from suan_ban.user_comment_book c join suan_ban.user on c.USER = user.idUSER" +
                    " where c.BOOK = " + elementID + " order by LIKE_NUM desc";
            try {
                Statement statement = con.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }

    public ResultSet getComment(int userID,int elementID, boolean isBOOK){
        ResultSet resultSet = null;
        if(!isBOOK) {
            String sql = "SELECT * from suan_ban.user_comment_movie c join suan_ban.user on c.USER = user.idUSER" +
                    " where c.MOVIE = " + elementID + " AND c.USER = " + userID + " order by LIKE_NUM desc";
            try {
                Statement statement = con.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql = "SELECT * from suan_ban.user_comment_book c join suan_ban.user on c.USER = user.idUSER" +
                    " where c.BOOK = " + elementID + " AND c.USER = " + userID + " order by LIKE_NUM desc";
            try {
                Statement statement = con.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }



    @Override
    public ResultSet getFriendComment(int friendID, boolean isBOOK) {
        ResultSet resultSet = null;
        if(!isBOOK) {
            String sql = "SELECT * from suan_ban.user_comment_movie c join suan_ban.user on c.USER = user.idUSER" +
                    " where c.USER = " + friendID + " order by c.LIKE_NUM desc";
            try {
                Statement statement = con.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql = "SELECT * from suan_ban.user_comment_book c join suan_ban.user on c.USER = user.idUSER" +
                    " where c.USER = " + friendID + " order by c.LIKE_NUM desc";
            try {
                Statement statement = con.createStatement();
                resultSet = statement.executeQuery(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultSet;
    }

    @Override
    public ResultSet getFriends() {
        String sql = "select FOLLOWING from suan_ban.friend_user where FOLLOWER = " + idUSER;
        int friendID;
        ResultSet resultSet = null;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addFriends(String nickname) {
        String sql = "select idUSER from suan_ban.user where USER_NICKNAME = '" + nickname + "';";
        int friendID;
        ResultSet resultSet = null;
        boolean flag = false;
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            flag = resultSet.next();
            if(flag){
                friendID = resultSet.getInt("idUSER");
                String sql2 = "insert into suan_ban.friend_user(FOLLOWER, FOLLOWING) VALUES (?,?)";
                PreparedStatement preparedStatement = con.prepareStatement(sql2);
                preparedStatement.setInt(1, idUSER);
                preparedStatement.setInt(2, friendID);
                preparedStatement.executeUpdate();
            }
            return flag;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ResultSet getMarkedElement(int idUSER) {
        ResultSet resultSet = null;
        String sql = "(select MOVIE_TITLE as name ,movie as elementID, min(d) as d, 'M' as type from " +
                "(select MOVIE, WANT_TO_WATCH as d from suan_ban.user_comment_movie where user =  " + idUSER +
                " union " +
                "select MOVIE, WATCHED_TIME as d from suan_ban.user_comment_movie where user = " + idUSER +
                " ) as a join suan_ban.movie m on a.MOVIE = m.idMOVIE group by MOVIE)" +
                "union " +
                "(select BOOK_TITLE as name ,BOOK as elementID, min(d) as d, 'B' as type from " +
                "(select BOOK, WANT_TO_READ_TIME as d from suan_ban.user_comment_book where USER =  " +idUSER +
                " union " +
                "select BOOK, READING_TIME as d from suan_ban.user_comment_book where USER =  " + idUSER +
                " union " +
                "select BOOK, READED_TIME as d from suan_ban.user_comment_book where USER =  " + idUSER +
                " ) as b join suan_ban.book bo on b.BOOK = bo.idBOOK group by BOOK);";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
