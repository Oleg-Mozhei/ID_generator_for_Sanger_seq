import java.util.Random;

public class LabelsGenerator{
  static Random r = new Random();

  public static void main(String[] args){
  System.out.println("hello world!");

  LabelsGenerator l = new LabelsGenerator();     //connect to add during initialization
  String label = l.generateLabel();
  System.out.println(label);


  //LU8WXNG

  }

  public String generateNewLabel(){
    return null;
  }

  public String generateLabel(){
    String label = "";
    for (int i = 0; i < 7; i++){
      label +=getAllowedSymbol();
    }
    //System.out.println(label);
    return label;
  }


  public char getAllowedSymbol(){
    int a = 0;
    while(true){
      //System.out.println("I want to get 49-57 and 65-90");
      a = r.nextInt(35);    //0-34
      a+=49;                //49 - 83 
      if (a > 57){a+=7;}    //49-57 & 65-90
      if (a!= 73 || a!=76 || a!= 79){     //exclude 73, 76 and 79
        break;
      }
    }
    //System.out.println("I got " + a);
    return ((char) a);


  }
}
