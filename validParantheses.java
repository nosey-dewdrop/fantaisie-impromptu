import java.util.Scanner;

public class validParantheses {
      
      public static boolean isValid(String s) {
            int braceCount = 0;
            int bracketCount = 0;
            int curlyCount = 0;

            char[] charArray = s.toCharArray();

            for(char c: charArray){
                  if(c == '('){
                        braceCount++;
                  }
                  if(c == '['){
                        bracketCount++;
                  }
                  if(c == '{'){
                        curlyCount++;
                  }

            }

      }     

      public static void main (String[] args){
            Scanner scanner = new Scanner(System.in);

            System.out.println("enter the parentheses string:");
            String str = scanner.nextLine();

            boolean result = isValid(str);
            System.out.println("is the parentheses string valid? " + result);
            scanner.close();
      }
}
