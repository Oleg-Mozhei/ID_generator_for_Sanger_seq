import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.sql.*;


public class LabelsPrinter{
	static final ArrayList<String> prepaid_labels = new ArrayList<>();

	public static void main(String[] args){
		System.out.println("Hello world!");
		System.out.println("If you get error, try to run java -cp \".:/usr/share/java/*\" LabelsPrinter");

		try {
			LabelsPrinter printer = new LabelsPrinter();
			printer.printLabels(prepaid_labels);

		} catch (IOException | SQLException e){
			e.printStackTrace();
		}
		
	}

	public LabelsPrinter() throws SQLException, IOException{
    final Connection conn = getConnection();

    String query = "SELECT * FROM prepaid";
    Statement statement = conn.createStatement();
    ResultSet rs = statement.executeQuery(query);
    while (rs.next()) {
      String id = rs.getString(1);
      prepaid_labels.add(id);
      //System.out.println("id from prepaid table: " + id);
    }
    conn.close();
  }

	public void printLabels(ArrayList<String> labels) throws IOException{
		for (int i = 0; i < labels.size(); i++){
			if (labels.get(i).length() != 7){
				System.out.println("Labels should be 7 letters long");
				System.out.println("Current label " + labels.get(i) + " is " + labels.get(i).length() + " letters!");
				System.out.println("I'm going to stop application!");
				System.exit(0);
			}
			copyTemplate(labels.get(i) + ".svg");
			appendToFile(labels.get(i) + ".svg");
		}

	}

	private void copyTemplate(String new_file_name) throws IOException{
		Path copied = Paths.get(new_file_name);
    	Path originalPath = Paths.get("template.svg");
    	Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
 
	}

	private void appendToFile(String fileName) throws IOException {

		String temp[] = fileName.split("\\.(?=[^\\.]+$)");
		String label = temp[0];

		String str ="<text\n";
str+="       xml:space=\"preserve\"\n";
str+="       style=\"font-style:normal;font-weight:normal;font-size:10.58333302px;line-height:1.25;font-family:sans-serif;letter-spacing:0px;word-spacing:0px;fill:#000000;fill-opacity:1;stroke:none;stroke-width:0.26458332\"\n";
str+="       x=\"5.8637156\"\n";
str+="       y=\"27.635866\"\n";
str+="       id=\"text4618\"><tspan\n";
str+="         sodipodi:role=\"line\"\n";
str+="         id=\"tspan4616\"\n";
str+="         x=\"5.8637156\"\n";
str+="         y=\"27.635866\"\n";
str+="         style=\"font-style:normal;font-variant:normal;font-weight:normal;font-stretch:normal;font-size:8.46666622px;font-family:sans-serif;-inkscape-font-specification:'sans-serif, Normal';font-variant-ligatures:normal;font-variant-caps:normal;font-variant-numeric:normal;font-feature-settings:normal;text-align:start;writing-mode:lr-tb;text-anchor:start;stroke-width:0.26458332\">" + label + "</tspan></text>\n";
str+="    <path\n";
str+="       style=\"fill:none;stroke:#000000;stroke-width:0.70674074px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1\"\n";
str+="       d=\"m 4.7531935,31.23807 c 42.8325825,-2.927216 42.8325825,0 42.8325825,0\"\n";
str+="       id=\"path4620\"\n";
str+="       inkscape:connector-curvature=\"0\" />\n";
str+="  </g>\n";
str+="</svg>\n";;
	BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
    writer.append(str);
    
    writer.close();
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