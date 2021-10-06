import java.util.Random;
import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class LabelsGenerator{
  static Random r = new Random();
  final Connection conn;

  public LabelsGenerator(){
  try{
        conn = getConnection();
    } catch(Exception ex){
        ex.printStackTrace();
    }
    }

  public static void main(String[] args){
  LabelsGenerator generator = new LabelsGenerator();

  System.out.println("hello world!");

  System.out.print(getFirstSymbol());
  System.out.print(getFirstSymbol());
  System.out.print(getFirstSymbol());
  System.out.print(getFirstSymbol());
  System.out.print(getFirstSymbol());
  System.out.print(getFirstSymbol());
  System.out.print(getFirstSymbol());
  System.out.println();

  //LU8WXNG

  }


  public static char getFirstSymbol(){
    int a = 0;
    while(true){
      //System.out.println("I want to get 49-57 and 65-90");
      a = r.nextInt(35) + 49;   //49 - 83
      if (a > 57){a+=8;}
      if (a!= 73 || a!=76 || a!= 79){
        break;
      }
    }
    //System.out.println("I got " + a);
    return ((char) a);


  }


  private Connection getConnection() throws SQLException, IOException{

        Properties props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("database.properties"))){
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }
}