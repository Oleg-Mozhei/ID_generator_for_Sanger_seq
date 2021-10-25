import java.util.Random;
import java.sql.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class LabelsGenerator{
  static Random r = new Random();
  
  ArrayList<String> labels = new ArrayList<>();

  public static void main(String[] args){
  	System.out.println("If you get error, try to run java -cp \".:/usr/share/java/*\" LabelsGenerator");
  	try {
      LabelsGenerator generator = new LabelsGenerator();
      System.out.println("hello world!");
      ArrayList<String> newLabels = generator.generateUniqueLabels(5);
      generator.add_labels_to_prepaid(newLabels, "testing");
      } catch (SQLException | IOException e){
	    e.printStackTrace();
      }
  }

  public void add_labels_to_prepaid(ArrayList<String> labels, String purpose) throws SQLException, IOException{
    final Connection conn = getConnection();

    for (int i = 0; i < labels.size(); i++){
      String query = "INSERT INTO prepaid (label_id, purchase_date, purpose) VALUES ('" + labels.get(i) + "', CURDATE(), '" + purpose + "');";
      Statement statement = conn.createStatement();
      ResultSet rs = statement.executeQuery(query);
    }

    conn.close();

  }

  public LabelsGenerator() throws SQLException, IOException{
    final Connection conn = getConnection();

    String query = "SELECT * FROM finished";
    Statement statement = conn.createStatement();
    ResultSet rs = statement.executeQuery(query);
    while (rs.next()) {
      String id = rs.getString(1);
      labels.add(id);
      //System.out.println("id from finished table: " + id);
    }

    query = "SELECT * FROM processing";
    statement = conn.createStatement();
    rs = statement.executeQuery(query);
    while (rs.next()) {
      String id = rs.getString(1);
      labels.add(id);
      //System.out.println("id from processing table: " + id);
    }
    query = "SELECT * FROM queue";
    statement = conn.createStatement();
    rs = statement.executeQuery(query);
    while (rs.next()) {
      String id = rs.getString(1);
      labels.add(id);
      //System.out.println("id from queue table: " + id);
    }
    query = "SELECT * FROM prepaid";
    statement = conn.createStatement();
    rs = statement.executeQuery(query);
    while (rs.next()) {
      String id = rs.getString(1);
      labels.add(id);
      //System.out.println("id from prepaid table: " + id);
    }
    conn.close();
  }

  public ArrayList<String> generateUniqueLabels(int labels_to_generate_quantity){
    ArrayList<String> result = new ArrayList<>();

    for (int i = 0; i < labels_to_generate_quantity; i++){
  	  String label = "EMHAG0Q";
  	  while(labels.contains(label)){
  		  label = getLabel();
  	  }
  	System.out.println("Generated unique label: " + label);
    result.add(label);
    }
    return result;
  }

  private static char getOneSymbol(){
    int a = 0;
    while(true){
      //System.out.println("I want to get 49-57 and 65-90");
      a = r.nextInt(35);    //0-34
      a = a + 49;           //49-83
      if (a > 57){a+=7;}    //49-57 65-90
      if (a == 73 || a == 76 || a == 79){
      } else {
      	//System.out.println(a);
        break;
      }
    }
    //System.out.println("I got " + a);
    return ((char) a);
  }

  private static String getLabel(){
  	String result = "";
  	for (int i = 0; i < 7; i++){
  		result+=getOneSymbol();
  	}
  	return result;
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