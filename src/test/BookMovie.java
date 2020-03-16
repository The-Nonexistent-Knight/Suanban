package test;
import java.sql.*;
import java.util.ArrayList;

public class BookMovie implements BookMovieOperation {
    private static Connection con = null;
    public BookMovie(Connection conn){
        con = conn;
    }

    public ResultSet getBookInfo(int bookID){
        ResultSet resultSet = null;
        String sql = "select *, BOOK_SCORE / num.n as final_score from suan_ban.BOOK, (select count(*) as n from suan_ban.user_comment_book where BOOK = "+ bookID +") as num where idBOOK = " + bookID +" ;";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getMovieInfo(int movieID){
        ResultSet resultSet=null;
        String sql = "select *, MOVIE_SCORE / num.n as final_score from suan_ban.MOVIE, (select count(*) as n from suan_ban.user_comment_movie where MOVIE = "+ movieID +") as num where idMOVIE = " + movieID +";";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    // consider the country in country_book and country_movie
    public ResultSet getBookPeople(int bookID){
        ResultSet resultSet=null;
        String sql = "select p.*   from (suan_ban.BOOK_PEOPLE join suan_ban.BOOK b on BOOK_PEOPLE.BOOK = b.idBOOK join suan_ban.PEOPLE p on BOOK_PEOPLE.PEOPLE = p.idPEOPLE) where idBOOK="+bookID+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getMoviePeopleDirector(int movieID){
        ResultSet resultSet=null;
        String sql = "select p.*   from (suan_ban.MOVIE_PEOPLE join suan_ban.MOVIE m on MOVIE_PEOPLE.MOVIE = m.idMOVIE join suan_ban.PEOPLE p on MOVIE_PEOPLE.PEOPLE = p.idPEOPLE) where idMOVIE="+movieID+" and TYPE = 'D'";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getMoviePeopleActor(int movieID) {
    	ResultSet resultSet=null;
        String sql = "select p.*   from (suan_ban.MOVIE_PEOPLE join suan_ban.MOVIE m on MOVIE_PEOPLE.MOVIE = m.idMOVIE join suan_ban.PEOPLE p on MOVIE_PEOPLE.PEOPLE = p.idPEOPLE) where idMOVIE="+movieID+" and TYPE = 'A'";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };
    
    public ResultSet getMovieCountry(int movieID) {
    	ResultSet resultSet=null;
        String sql = "select * from suan_ban.country inner join movie on idCOUNTRY = MOVIE_COUNTRY where IDmovie = "+movieID+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };
    
    public ResultSet searchMovie(String searchTitle, int timeIntervalLeft, int timeIntervalRight, String searchPeople, ArrayList<String> searchTag,String searchCountry){
        ResultSet resultSet=null;
 //       Date left = new Date(timeIntervalLeft - 1900, 0, 1);
//        Date right = new Date(timeIntervalRight - 1900, 11, 31);
        String tag=null;
        String sql ="select distinct table1.* from(select m.* from (suan_ban.MOVIE_PEOPLE join suan_ban.MOVIE m on MOVIE_PEOPLE.MOVIE = m.idMOVIE join suan_ban.PEOPLE p on MOVIE_PEOPLE.PEOPLE = p.idPEOPLE) " +
                "where MOVIE_TITLE like '%"+ ((searchTitle == null) ? "" : searchTitle) +"%' " +
                "and concat(PEOPLE_FIRST_NAME_FOREIGNER, PEOPLE_SURNAME_FOREIGNER) like '%"+(searchPeople == null ? "" : searchPeople)+"%' " +
                "and MOVIE_RELEASED_DAY between "+timeIntervalLeft+" and "+timeIntervalRight+" " +
                "and MOVIE_COUNTRY in(select idCOUNTRY from COUNTRY where COUNTRY_NAME like '%"+(searchCountry == null ? "" : searchCountry)+"%')) table1 ";
//                " inner join (SELECT m.* from (suan_ban.TAG_MOVIE join suan_ban.MOVIE m on TAG_MOVIE.MOVIE = m.idMOVIE join suan_ban.TAG t on TAG_MOVIE.TAG=t.idTAG) where TAG_NAME like '%"+(tag == null ? "" : tag)+"%' ) table2" +
//                " on table1.idMOVIE = table2.idMOVIE";
        String newSql=null;
        if(searchTag!=null&&!searchTag.isEmpty()){
        	sql=sql+" inner join (SELECT m.* from (TAG_MOVIE join MOVIE m on tag_movie.MOVIE = m.idMOVIE join TAG t on TAG_MOVIE.TAG=t.idTAG) where t.idTAG in(select idTAG from tag where ";
        for(int i=0;i<searchTag.size();i++){
            
            tag=searchTag.get(i);
            if(i==0){
                newSql="TAG_NAME like '%"+tag+"%' ";
            }
            else{
                newSql="or TAG_NAME like '%"+tag+"%' ";
            }
           sql=sql+newSql;

        }
        sql=sql+")) table2 on table1.idMOVIE = table2.idMOVIE";
        }else{
        }

        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet searchBook(String searchTitle, int timeIntervalLeft, int timeIntervalRight, String searchPeople,
                                ArrayList<String> searchTag,String searchCountry){
        ResultSet resultSet=null;
//        Date left = new Date(timeIntervalLeft - 1900, 0, 1);
//        Date right = new Date(timeIntervalRight - 1900, 11, 31);
        String tag=null;
        String newSql=null;
        String sql ="select distinct table1.* from (select b.* from (suan_ban.BOOK_PEOPLE join suan_ban.BOOK b on BOOK_PEOPLE.BOOK = b.idBOOK join suan_ban.PEOPLE p on BOOK_PEOPLE.PEOPLE = p.idPEOPLE) " +
                "where BOOK_TITLE like '%"+(searchTitle == null ? "" : searchTitle)+"%' " +
                "and  concat(PEOPLE_FIRST_NAME_FOREIGNER,PEOPLE_SURNAME_FOREIGNER) like '%"+(searchPeople == null ? "" : searchPeople)+"%' " +
                "and BOOK_PUBLISH_DATE between "+timeIntervalLeft+" and "+timeIntervalRight+" " +
                "and book_country in(select idCOUNTRY from COUNTRY where COUNTRY_NAME like '%"+(searchCountry == null ? "" : searchCountry)+"%') ) table1 " ;
//                "inner join (SELECT b.* from (suan_ban.BOOK_TAG join suan_ban.BOOK b on BOOK_TAG.BOOK = b.idBOOK join suan_ban.TAG t on BOOK_TAG.TAG=t.idTAG) where TAG_NAME like '%"+(searchTag == null ? "" : searchTag)+"%')table2" +
//                " on table1.idBOOK = table2.idBOOK";

        if(searchTag!=null&&!searchTag.isEmpty()){
        	sql=sql+"inner join (SELECT b.* from (BOOK_TAG join BOOK b on BOOK_TAG.BOOK = b.idBOOK join TAG t on BOOK_TAG.TAG=t.idTAG) where t.idTAG in(select idTAG from tag where ";
            for(int i=0;i<searchTag.size();i++){
                
                tag=searchTag.get(i);
                if(i==0){
                    newSql="TAG_NAME like '%"+tag+"%' ";
                }
                else{
                    newSql="or TAG_NAME like'%"+tag+"%' ";
                }
                sql=sql+newSql;

            }
            sql=sql+")) table2 on table1.idBOOK = table2.idBOOK";
        }else{
        }
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet searchPeople(String searchName){
        ResultSet resultSet=null;
        String sql = "select *  from suan_ban.people where  concat(PEOPLE_FIRST_NAME_FOREIGNER,PEOPLE_SURNAME_FOREIGNER) like '%"+(searchName == null ? "" : searchName)+"%'";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };

    public ResultSet searchFestival(String name,int year){
        ResultSet resultSet=null;
        String sql = "select * from festival where FESTIVAL_NAME like '%"+name+"%' and FESTIVAL_DATE = "+year+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    
    public ResultSet getFestival(int festivalID){
        ResultSet resultSet=null;
        String sql = "select *  from suan_ban.FESTIVAL where idFestival = "+festivalID+" ";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getPeopleMovie(int peopleID){
        ResultSet resultSet = null;
        String sql = "select *   " +
                "from (suan_ban.MOVIE_PEOPLE join suan_ban.MOVIE m on MOVIE_PEOPLE.MOVIE = m.idMOVIE join suan_ban.PEOPLE p on MOVIE_PEOPLE.PEOPLE = p.idPEOPLE) " +
                "where idPEOPLE="+peopleID+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getPeopleBook(int peopleID){
        ResultSet resultSet=null;
        String sql = "select *   " +
                "from (suan_ban.BOOK_PEOPLE join suan_ban.BOOK b on BOOK_PEOPLE.BOOK = b.idBOOK join suan_ban.PEOPLE p on BOOK_PEOPLE.PEOPLE = p.idPEOPLE) " +
                "where idPEOPLE="+peopleID+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getPeopleInfo(int peopleID){
        ResultSet resultSet=null;
        String sql = "select * from suan_ban.PEOPLE where idPEOPLE = " + peopleID +"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getMovieTag(int movieID){
        ResultSet resultSet=null;
        String sql = "select t.*   from " +
                "(suan_ban.TAG_MOVIE join suan_ban.MOVIE m on TAG_MOVIE.MOVIE = m.idMOVIE join suan_ban.TAG t on TAG_MOVIE.TAG=t.idTAG) " +
                "where idMOVIE="+movieID+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };

    public ResultSet getBookTag(int bookID){
        ResultSet resultSet=null;
        String sql = "select t.*  from (suan_ban.BOOK_TAG join suan_ban.BOOK b on BOOK_TAG.BOOK = b.idBOOK join suan_ban.TAG t on BOOK_TAG.TAG=t.idTAG) where idBOOK="+bookID+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };

    public ResultSet getHighCoreBook(){
        ResultSet resultSet=null;
        String sql = "select b.* from suan_ban.book as b join (select BOOK, count(*) as n  from suan_ban.user_comment_book group by book) as num\n" +
                "                    on b.idBOOK = num.BOOK\n" +
                "order by b.BOOK_SCORE / num.n DESC  limit 10;";
            try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };

    public ResultSet getHighCoreMovie() {
    	 ResultSet resultSet=null;
         String sql = "select m.* from suan_ban.movie as m join (select MOVIE, count(*) as n  from suan_ban.user_comment_movie group by MOVIE) as num\n" +
                 "                  on m.idMOVIE = num.MOVIE\n" +
                 "order by m.MOVIE_SCORE / num.n DESC  limit 10;";
         try {
             Statement statement = con.createStatement();
             resultSet = statement.executeQuery(sql);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return resultSet;
	};
	
    public ResultSet getMovieAward(int movieID){
        ResultSet resultSet=null;
        String sql = "select * from suan_ban.award where AWARD_MOVIE = " + movieID +"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };

    public ResultSet getAwardFestival(int awardID){
        ResultSet resultSet=null;
        String sql = "select * from suan_ban.festival inner join award on AWARD_FESTIVAL = festival.idFestival where award.idAWARD = " + awardID +"  ";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };

    public ResultSet getAwardMovie(int awardID){
        ResultSet resultSet=null;
        String sql = "select * from suan_ban.movie inner join award on AWARD_MOVIE = idMOVIE where award.idAWARD = " + awardID +"  ";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    };
    
    public ResultSet getFestivalAward(int festivalID) {
    	ResultSet resultSet=null;
        String sql = "select * from suan_ban.AWARD where AWARD_FESTIVAL = "+festivalID+"";
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

	
}
