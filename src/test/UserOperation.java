package test;
import java.sql.*;


public interface UserOperation {
    public boolean isAccountExist(String account);

    public boolean register(String account, String password, String nikeName,
            String gender, int age, String city, String statement);
    //return false if fail to register

    public boolean updateUserInfo(String account, String password, String nikeName,
                            String gender, int age, String city, String statement);

    public boolean login(String account, String password);
    //return true if success login;
    //judge if user is admin, set the value of Operation.isAdmin
    //set Operation.idUSER to current user

    public ResultSet getBasicInfo(int userID);

    public ResultSet searchUSER(String nickname);

    public void createList(boolean isBook, String listName);
    //    add element in LIST
    //    either BOOK / MOVIE list

    public void collectList(boolean add, int listID);
    //  add/delete element in LIST_USER_COLLECTION
    //  if add is true, collect list
    //  else cancel the collection

    public boolean isCollectList(int listID);

    public ResultSet getCollectList();

    public ResultSet findList(String name);

    public int getCurrentList(String name);

    public int getListCreator(int idLIST);

    public String typeOfList(int listID);

    public ResultSet elementsInList(int listID);
    // check the book/movie

    public void editList(int listID, boolean add, int addElementID);
    //  if add is true, add element into list; else delete

    public void likeMovie(int movieID);
    //  点赞

    public void commentMovie(int movieID, String status, String shortComment,
                             String longComment, int score);
    //  status = "WANT_TO_WATCH" OR "WATCHED"
    //  Program auto-fill "WATCHED_TIME" and "WANT_TO_WATCH_TIME"

    public ResultSet getMarkedElement(int idUSER);

    public void commentbook(int bookID, String status, String shortComment,
                            String longComment, int score);
    //  status = "READED" OR "READING" OR "WANT_TO_READ"

    public void longCommentBook(int bookID, String longComment);

    public void longCommentMovie(int movieID, String longComment);

    public ResultSet getLongCommentMovie(int userID, int movieID);

    public ResultSet getLongCommentMovie(int movieID);

    public ResultSet getLongCommentBook(int userID, int bookID);

    public ResultSet getLongCommentBook(int bookID);


    public void likeComment(int userID, int elementID, boolean isBook);

    public void sentMessage(int userID, String content);
    //  auto-fill TIME

    public ResultSet getMessage(int userID);
    //  sort by time

    public ResultSet getFriendBookList(int friendID);
    //  The list that friend collect or create in LIST and LIST_USER_COLLECTION
    //  sort by time

    public ResultSet getFriendMovieList(int friendID);

    public ResultSet getComment(int elementID, boolean isBOOK);

    public ResultSet getComment(int userID,int elementID, boolean isBOOK);

    public ResultSet getFriendComment(int friendID, boolean isBOOK);
    //  both book and movie should be considered
    //  in USER_COMMENT_MOVIE and USER_COMMENT_BOOK

    public ResultSet getFriends();

    public boolean addFriends(String nickname);
}
