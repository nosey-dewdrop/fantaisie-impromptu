import java.io.*;
import java.util.Scanner;
public class adventOfCode_day1 {

      public static int placement = 50;

      public static int calculateLeftRight(String direction){
            char coordinate = direction.charAt(0);
            String num = direction.substring(1, direction.length());
            int number = Integer.valueOf(num);

            if(coordinate == 'L'){
                  placement -= number;
                  if(placement < 0){
                        placement += 100;
                        placement = placement % 100;
                  }
            }
            
            else if(coordinate == 'R'){
                  placement += number;
                  placement = placement % 100;
            }
            else{
                  System.out.println("INVALIDDDDDD");
                  return 0;
            }
            return placement;
            
      }
      public static void main (String [] args){

            try{

                  Scanner scanner = new Scanner(new File("input.txt")); // why error?
                  System.out.println("file found!");
                  int count = 0;

                  int steps = 0;
                  while(scanner.hasNextLine()){

                        String line = scanner.nextLine();

                        int placement = 0;
                        if(!line.isEmpty()){
                              placement += calculateLeftRight(line);

                              if (placement == 0){
                                    count++;
                              }
                              System.out.println("line read: " + line);
                              System.out.println("placement this turn: " + placement);
                        }
                        steps ++;
                  }

                  scanner.close();
                  System.out.print("\nfinal placement is: " + placement);
                  System.out.print("\ncount is: " + count);
            }

            catch(FileNotFoundException e){
                  
            }
      }
}
