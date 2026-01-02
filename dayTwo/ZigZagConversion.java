package dayTwo;
import java.util.Scanner;

class ZigZagConversion {

      public static int findPeriod(String str, int numRows) {
            int period = str.length()/(2*numRows -2);
            int lambda = numRows-1;

            int preceedingChars = str.length() % period;

            if(preceedingChars == 0){
                  return lambda*period;
            }
            else{
                  return lambda*period + 1;
            }
      }

      public static void main (String[] args) {

            Scanner scanner = new Scanner (System.in);

            System.out.println("enter the string to convert:");
            String s = scanner.nextLine();
            scanner.nextLine();

            System.out.println("enter the number of rows:");
            int numRows = 0;

            if(scanner.hasNextInt()){
                  numRows = scanner.nextInt();
                  scanner.nextLine();
            }

            int period = findPeriod(s, numRows);
            System.out.println("the period is: " + period);

            scanner.close();
      }

}
