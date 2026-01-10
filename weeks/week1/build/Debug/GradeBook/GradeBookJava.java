package week1.build.Debug.GradeBook;
import java.util.*;
public class GradeBookJava{

   public static void displayMessage( int courseNo ) {
      System.out.println( "Welcome to " + courseNo );
   }

   public static void main( String [] args ) {
      int cno;
      Scanner scan = new Scanner( System.in );

      System.out.print( "Enter course no: " );
      cno = scan.nextInt();
      displayMessage( cno );

      scan.close();
   }
}