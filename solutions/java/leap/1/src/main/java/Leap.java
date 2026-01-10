
import java.util.*;
public class Leap {
    public static boolean isLeapYear(int year){

        boolean status = false;
        if(year%4 == 0){
            if(year%100 == 0){
                if(year%400 == 0){
                    status = true;
                }
                else{
                    status = false;
                }
            }
            else{
                status = true;
            }
        }
        else{
            status = false;
        }

        return status;
    }

    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();

        boolean leapYear = isLeapYear(year);
        if(leapYear){System.out.println("It is a leap year!");}
        else{System.out.println("It is not a leap year!");}
        scanner.close();
    }
}

