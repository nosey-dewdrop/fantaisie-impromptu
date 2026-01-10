
import java.util.*;
public class Leap {
    public static boolean isLeapYear(int year){

        boolean status = false;
        if(year%4 == 0){
            if(year%100 == 0){
                if(year%400 != 0){
                    status = false;
                }
                else{
                    status = true;
                }
            }
            else if(year % 200 == 0){
                if(year % 400 != 0){
                    status = false;
                }
                else{
                    status = true;
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
}

