import java.util.Random;

public class LabelsGenerator{
  static Random r = new Random();

  public static void main(String[] args){
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
}
