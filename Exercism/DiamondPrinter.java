package Exercism;
import java.util.ArrayList;
import java.util.List;

public class DiamondPrinter {
    public static List<String> printToList(char a) {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        char ch = Character.toUpperCase(a);
        int index = ch - 'A';

        List<String> diamond = new ArrayList<>();
    
        int num = index; // daha az olacak.
        int i = 0;

        while(num >= 0){
            char current = letters.charAt(i);
            String preDot = "";
            String postDot = "";

            for(int j = 0; j < num; j++){
                preDot += ".";
            }
            for(int k = 0; k < index-num; k++){
                postDot += ".";
            }

            String stringFirst = preDot +Character.toString(current)+ postDot;
            String reverse = "";
            String allINeed = stringFirst.substring(0, index);

            for(int d = allINeed.length()-1; a >= 0; a--){
                char currentCh = allINeed.charAt(d);
                reverse += Character.toString(currentCh);
            }

            String last = stringFirst + reverse;
            diamond.add(last);

            num--;
            i++;
        }

        //lower index kadar.
        return diamond;

    }

    public static void main(String[] args) {
        List<String> diamondShape = new ArrayList<>();
        diamondShape = printToList('f');

        for(String diamond : diamondShape){
            System.out.println(diamond);
        }
    }
}
