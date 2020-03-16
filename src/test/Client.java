package test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.IntBinaryOperator;

public class Client {
    private static Connection con = null;

    public static void main(String[] args) {
        DatabaseManipulation dm = new DatabaseManipulation();
        dm.openDataSource();
        String filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\countries.txt";
        ArrayList<String> country_name = readTxtFile(filePath, ";", 1);
        ArrayList<String> continent = readTxtFile(filePath, ";", 2);
        int k = 0;
		String[] tag={"战争","爱情","科幻","喜剧","动作","犯罪","奇幻","悬疑"};
        int size = tag.length;
//        for (int j = 0; j < size; j++) {
//            int idtag= dm.addOneTaggetid(tag[j]);
//        }
        size = continent.size();
//        while (k++<size)
//            dm.addOneCountry(country_name.get(k),continent.get(k));
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\movies.txt";
        ArrayList<String> title = readTxtFile(filePath, ";", 1);
        ArrayList<String> time = readTxtFile(filePath, ";", 3);
        ArrayList<String> runtime = readTxtFile(filePath, ";", 4);
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\language.txt";
        ArrayList<String> language = readTxtFile(filePath);
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\intro.txt";
        ArrayList<String> intro = readTxtFile(filePath);
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\charcollection.txt";
        ArrayList<String> chars = readTxtFile(filePath);
        size = title.size();
        int num = 1;
//        for (int j = 1; j < size; j++) {
//            String imdb = "IMDB" + (1 + Math.random() * (99999999 - 10000000 + 10000000)) + "";
//            int idcountry = (int) (1 + Math.random() * (185 - 1 + 1));
//            String link = "www." + chars.get(j % chars.size()) + ".com";
//            int is = 0;
//            if (j % 2 == 1)
//                is = 1;
//            int id = dm.addOneMoviegetid(title.get(j),
//                    time.get(j), runtime.get(j),
//                    language.get(j % language.size()), imdb, intro.get(j % intro.size()), link,
//                    is, idcountry);
//            if (id == 1) {
//                for (int i = 0; i < 4; i++) {
//                    int idtag = (int) (1 + Math.random() * (8 - 1 + 1));
//                    dm.addOnetag_movie(idtag, num);
//                }
//                num++;
//            }
//        }
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\people.txt";
        ArrayList<String> peoplefirstname = readTxtFile(filePath, ",", 1);
        ArrayList<String> peoplesurname = readTxtFile(filePath, ",", 2);
        ArrayList<String> gender = readTxtFile(filePath, ",", 5);
        ArrayList<String> born = readTxtFile(filePath, ",", 3);
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\char6.txt";
        ArrayList<String> place = readTxtFile(filePath);
        ArrayList<String> constellation = new ArrayList<>();
        constellation.add("Capricorn");
        constellation.add("Aquarius");
        constellation.add("Pisces");
        constellation.add("Aries");
        constellation.add("Taurus");
        constellation.add("Gemini");
        constellation.add("Cancer");
        constellation.add("Leo");
        constellation.add("Virgo");
        constellation.add("Libra");
        constellation.add("Scorpio");
        constellation.add("Sagittarius");
        size = peoplefirstname.size();
//        for (int j = 0; j < size; j++) {
//            int idcountry = (int) (1 + Math.random() * (185 - 1 + 1));
//            int id = dm.addOnePeople(peoplefirstname.get(j), peoplesurname.get(j), gender.get(j), born.get(j),
//                    place.get(j % place.size()), constellation.get(j % constellation.size()),
//                    intro.get(j % intro.size()), idcountry);
//        }
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\movie_people.txt";
        ArrayList<String> idmovie = readTxtFile(filePath, ",", 0);
        ArrayList<String> idpeople = readTxtFile(filePath, ",", 1);
        ArrayList<String> type = readTxtFile(filePath, ",", 2);
        size = idmovie.size();
//        for (int j = 1; j < size; j++) {
//            if (Integer.parseInt(idpeople.get(j))>9473)
//                continue;
//            dm.addOnemovie_people(Integer.parseInt(idmovie.get(j)),
//                    Integer.parseInt(idpeople.get(j)), type.get(j));
//        }
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\book_title.txt";
        ArrayList<String> booktitle = readTxtFile(filePath);
        size = booktitle.size();
        num = 1;
//        for (int j = 1; j < size; j++) {
//            int page = (int) (1 + Math.random() * (1500 - 500 + 500));
//            int price = (int) (1 + Math.random() * (100 - 20 + 20));
//            int layout = 0;
//            if (price % 2 == 1)
//                layout = 1;
//            String publish = place.get(j % place.size()) + " publish";
//            String isbn = "ISBN" + (1 + Math.random() * (99999999 - 10000000 + 10000000)) + "";
//            int idcountry = (int) (1 + Math.random() * (185 - 1 + 1));
//            int id = dm.addOneBookgetid(booktitle.get(j), time.get(j), page, price, layout, publish, isbn, intro.get(j % intro.size()), idcountry);
//            if (id == 1) {
//                for (int i = 0; i < 4; i++) {
//                    int idtag = (int) (1 + Math.random() * (8 - 1 + 1));
//                    dm.addOnebook_tag(num, idtag);
//                }
//                num++;
//            }
//        }
        size = idmovie.size();
//        for (int j = 1; j < size; j++) {
//            if (Integer.parseInt(idpeople.get(j))>9536)
//                continue;
//            dm.addOnebook_people(Integer.parseInt(idmovie.get(j)), Integer.parseInt(idpeople.get(j)));
//        }
        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\festival.txt";
        ArrayList<String> festivalname = readTxtFile(filePath);
        size = festivalname.size();
//        int numxx=0;
//        for (int j = 1; j < size; j++) {
//            int idcountry = (int) (1 + Math.random() * (185 - 1 + 1));
//            for (int y = 1960; y <= 2019; y++) {
//                dm.addOneFestival(festivalname.get(j), y, idcountry, place.get(j));
//               numxx++;
//                for (int i = 0; i < 10; i++) {
//                    int movie = (int) (1 + Math.random() * (9000 - 1 + 1));
//                    int people = (Integer) dm.select("people", "movie_people", "movie='" + movie + "'").get(0).get(0);
//                    if (people != -1)
//                        dm.addOneAwardgetid(place.get(j), numxx, movie, people);
//                }
//            }
//        }
//        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\password.txt";
//        ArrayList<String> password = readTxtFile(filePath);
//        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\count.txt";
//        ArrayList<String> count = readTxtFile(filePath);
//        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\nickname.txt";
//        ArrayList<String> nickname = readTxtFile(filePath);
//        filePath = "C:\\Users\\WindUpBird\\Desktop\\src\\comment.txt";
//        ArrayList<String> comment = readTxtFile(filePath);
//        filePath = "C:\\Users\\WindUpBird\\\\Desktop\\src\\long_comment.txt";
//        ArrayList<String> longcomment = readTxtFile(filePath);
//        size = password.size();
//        for (int i=0;i<2000;i++){
//            int age = (int) (1 + Math.random() * (99 - 1 + 1));
//            dm.addOneUser(count.get(i),password.get(i),nickname.get(i),gender.get(i),age,place.get(i%place.size()),intro.get(i%intro.size()) );
//        }
//        for (int i=1;i<=2000;i++){
//            for (int j=1;j<100;j++){
//                int id = (int) (1 + Math.random() * (9473- 1 + 1));
//                String status_b=null;
//                String status_m = null;
//                if (id%2==0){
//                    status_b="READED";
//                    status_m = "WATCHED";
//                }else {
//                    status_b="WANT_TO_READED";
//                    status_m="WANT_TO_WATCH";
//                }
//                int score=(int) (1 + Math.random() * (5- 0 + 0));
//                dm.commentMovie(i,id,status_m,longcomment.get(i%longcomment.size()),comment.get(i%comment.size()),score);
//                dm.commentbook(i,id,status_b,longcomment.get(i%longcomment.size()),comment.get(i%comment.size()),score);
//            }
//        }

        for (int i=1;i<=2000;i++){
        	int nameid=(int) (1 + Math.random() * (400- 1 + 1));
            dm.createList(true,place.get(nameid),i);
            dm.createList(false,place.get(nameid+1),i);
            for (int j=0;j<60;j++){
                int id = (int) (1 + Math.random() * (9473- 1 + 1));
                dm.addOnelist_book(2*i-1,id);
                id = (int) (1 + Math.random() * (9473- 1 + 1));
                dm.addOnelist_movie(2*i,id);
            }
        }

        dm.closeDataSource();
    }


    public static ArrayList<String> readTxtFile(String filePath, String x, int a) {
        ArrayList<String> s = new ArrayList<>();
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    s.add(lineTxt.split(x)[a]);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return s;
    }

    public static ArrayList<String> readTxtFile(String filePath) {
        ArrayList<String> s = new ArrayList<>();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    s.add(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        return s;
    }


}

