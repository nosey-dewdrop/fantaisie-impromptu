package Codewars;
import java.util.*;
public class Multiples35 {

  public static int solution(int number) {
    int sum = 0;
    int num = 1;
    
    while(num < number){
      if(num % 3 == 0 || num % 5 == 0){
        sum+=num;
      }
      else if(num % 3 == 0){
        sum+= num;
      }
      else if(num % 5 == 0){
        sum+= num;
      }
      
      num++;
    }
    return sum;
  }
  public static void main (String args[]){    
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter a number: ");
    int number = scanner.nextInt();
    
    int sum = solution(number);
    
    System.out.println(sum);
    scanner.close();
  }
}

