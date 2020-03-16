package test;
import java.sql.*;
import java.util.ArrayList;

public interface BookMovieOperation {
    public ResultSet getBookInfo(int bookID);

    public ResultSet getMovieInfo(int movieID);
    // consider the country in country_book and country_movie
    public ResultSet getBookPeople(int bookID);

    public ResultSet getMoviePeopleDirector(int movieID);
    
    public ResultSet getMoviePeopleActor(int movieID);
    
    public ResultSet getMovieCountry(int movieID);

    public ResultSet searchMovie(String searchTitle, int timeIntervalLeft, int timeIntervalRight,
                                 String searchPeople, ArrayList<String> searchTag,String country);

    public ResultSet searchBook(String searchTitle, int timeIntervalLeft, int timeIntervalRight, String sbearchPeople,
                                ArrayList<String> searchTag,String country);

    public ResultSet searchPeople(String searchName);
    
    public ResultSet searchFestival(String name,int year);

    public ResultSet getFestival(int festivalID);

    public ResultSet getPeopleMovie(int peopleID);

    public ResultSet getPeopleBook(int peopleID);

    public ResultSet getPeopleInfo(int peopleID);

    public ResultSet getMovieTag(int movieID);

    public ResultSet getBookTag(int bookID);

    public ResultSet getHighCoreBook();

    public ResultSet getHighCoreMovie();
    
    public ResultSet getMovieAward(int movieID);

    public ResultSet getAwardFestival(int awardID);

    public ResultSet getAwardMovie(int awardID);
    
    public ResultSet getFestivalAward(int festivalID);

}
 