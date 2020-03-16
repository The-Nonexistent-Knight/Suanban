package test;
import java.util.ArrayList;

public interface DataManipulation {
    public void openDataSource();
    public void closeDataSource();
    public int addOnebook_people(int idbook,int idpeople);
    public int addOnebook_tag(int idbook,int idtag);
    public int addOneMoviegetid(String title, String date, String runtime, String language,
                                String imdb, String intro, String link, int is,int idcountry);
    public int addOnePeople(String firstname, String surname, String gender, String birthday, String birthplace,
                            String constellation, String intro, int idcountry);
    public int addOneCountry(String countryname,String continent);
    public int addOneBookgetid(String title, String date, int page, int price, int layout
            , String publish, String isbn, String intro,int id);
    public int addOneAwardgetid(String award_name,int idfestival,int idmovie,int idpeople);
    public int addOneFestival(String festival_name,int date,int idcountry, String city) ;
    public int addOnefriend_user(int user1,int user2);
    public void createList(boolean isBook, String listName,int iduser);
    public int addOnelist_book(int idlist,int idbook);
    public int addOnelist_movie(int idlist,int idmovie);
    public int addOnelist_user_collection(int idlist,int iduser);
    public int addOneMessage(int user1,int user2,String content, String time);
    public int addOneTaggetid(String tag_name);
    public int addOneUser(String account,String password,String nickname,String gender,int age,
                          String city,String statement);
    public void commentMovie(int idUSER,int movieID, String status, String shortComment, String longComment, int score);
    public void commentbook(int idUSER,int bookID, String status, String shortComment, String longComment, int score);
    public int addOnemovie_people(int idmovie,int idpeople,String type);
    public int addOnetag_movie(int idtag,int idmovie);
    public ArrayList<ArrayList<Object>> select(String column, String table, String cond);
}
