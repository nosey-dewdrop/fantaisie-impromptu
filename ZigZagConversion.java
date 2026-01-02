import java.util.Scanner;

class ZigZagConversion {

      public static int findRepetition(String str, int numRows) {

            int period = 2*numRows - 2;
            int periodCompleted = str.length()/period;
            int lambda = numRows-1;
            int preceedingChars = str.length() % period;
            System.out.println("the preceeding chars is: " + preceedingChars);

            if(preceedingChars == 0){
                  return lambda*periodCompleted;
            }
            else{
                  return lambda*periodCompleted + 1;
            }
      }

      public static int findTurningCount(String str, int numRows){

            int period = 2*numRows - 2;
            int periodCompleted = str.length()/period;
            int preceedingChars = str.length() % period;

            int turningCount = 2*(periodCompleted -1) + 1;
            
            if(preceedingChars > 1){
                  turningCount += 1;
            }

            return turningCount;    
      }
      
      public static boolean isTurningPoint(String str, int numRows, char ch){

            int period = 2 * numRows - 2;
            int posInCycle = index % period;  
            return (posInCycle == 0 || posInCycle == numRows - 1);

      }

      public static void main (String[] args) {

            Scanner scanner = new Scanner (System.in);

            System.out.print("enter the string to convert: ");
            String str = scanner.nextLine();

            System.out.print("enter the number of rows:");
            int numRows = 0;

            if(scanner.hasNextInt()){
                  numRows = scanner.nextInt();
                  scanner.nextLine();
            }

            int period = findRepetition(str, numRows);
            System.out.println("the repetition of the period is: " + period);
            System.out.println("has turning point: " + findTurningCount(str, numRows));

            char[][] zigzag = new char[numRows][period];

            for(int i = 0; i < str.length(); i++){
                  boolean isTurningPoint = isTurningPoint(str, numRows, str.charAt(i));
                  System.out.println("the char " + str.charAt(i) + " is turning point: " + isTurningPoint);
            }
            scanner.close();
      }

}
