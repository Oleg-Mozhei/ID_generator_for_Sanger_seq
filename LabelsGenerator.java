import java.util.Random;
import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class LabelsGenerator{
  static Random r = new Random();
  final Connection conn;

  public LabelsGenerator() throws SQLException, IOException{
  	conn = getConnection();
 
    }

  public static void main(String[] args){
  	System.out.println("If you get error, try to run java -cp \".:/usr/share/java/*\" LabelsGenerator");
  	try {
  LabelsGenerator generator = new LabelsGenerator();

  System.out.println("hello world!");

  String result = "";
  for (int i = 0; i < 7; i++){
  	result+=getOneSymbol();
  }

  System.out.println(result);

  //LU8WXNG
} catch (SQLException | IOException e){
	e.printStackTrace();
}
  }


  public static char getOneSymbol(){
    int a = 0;
    while(true){
      //System.out.println("I want to get 49-57 and 65-90");
      a = r.nextInt(35);    //0-34
      a = a + 49;           //49-83
      if (a > 57){a+=7;}    //49-57 65-90
      if (a!= 73 || a!=76 || a!= 79){
        break;
      }
    }
    //System.out.println("I got " + a);
    return ((char) a);


  }


  private Connection getConnection() throws SQLException, IOException{

/*
        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

*/
        String username = "php_client";
        String url = "jdbc:mysql://localhost/sequencedb?allowPublicKeyRetrieval=true&serverTimezone=Europe/Moscow&useSSL=false";
        String password = "iwantmoremoney";

        return DriverManager.getConnection(url, username, password);
    }
}