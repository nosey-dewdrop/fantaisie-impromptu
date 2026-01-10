
import java.util.*;
public class Leap {
    public static boolean isLeapYear(int year){

        boolean status = false;
        if(year%4 == 0){
            if(year%100 == 0){
                return year%400==0;
            }
            return true;
        }
        return false;
    }
}

