/*
get an array of numbers. add one to it and output it. */

import java.util.Arrays;
import java.util.Scanner;

class PlusOne{

      public static int[] plusOne(int[] digits){
            int n =  digits.length;

            int[] result = Arrays.copyOf(digits, n);  // aynı ürünleri doldurdum.

            for(int i = n -1; i >= 0; i--){
                  if(result[i] < 9){
                        result[i]++;
                        return result;
                  }

                  result[i] = 0;  
            }
            int[] allNines = new int[n + 1];
            allNines[0] = 1;
            return allNines;
      } 

      public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            boolean posInt = true;

            int[] arrayInt = new int[0];

            while(posInt){
                  System.out.print("enter an array of numbers (-1 to exit): ");
                  if(scanner.hasNextInt()){
                        int number = scanner.nextInt();
                        scanner.nextLine();

                        if( number >= 0 && number <= 9){
                              int[] newValues = Arrays.copyOf(arrayInt, arrayInt.length + 1);
                              newValues[newValues.length - 1] = number;
                              arrayInt = newValues;
                        }
                        
                        else{
                              posInt = false;
                        }
                  }

                  else{
                        posInt = false;
                  }

            }

            System.out.println("your array : " + Arrays.toString(arrayInt));
            int[] result = plusOne(arrayInt);
            System.out.println("+1 array: " + Arrays.toString(result));
            scanner.close();
      }
}