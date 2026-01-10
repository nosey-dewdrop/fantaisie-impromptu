package Exercism.problemSet;

import java.util.*;
public class LeapYear {
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

    public static boolean leapYearShort(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true; // 100'e bölünmüyorsa ama 4'e bölünüyorsa artık yıl.
        }
        return false; // 4'e bile bölünemiyorsa artık yıl değil.
    }
    
    public static boolean shortest(int year){
        return (year % 100 == 0) ? (year % 400 == 0) : (year % 4 == 0);
    }
    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();

        boolean leapYear = isLeapYear(year);
        if(leapYear){System.out.println("It is a leap year!");}
        else{System.out.println("It is not a leap year!");}

        boolean shortYear = shortest(year);
        if(shortYear){System.out.println("It is a leap year!");}
        else{System.out.println("It is not a leap year!");}
        scanner.close();
    }
}


