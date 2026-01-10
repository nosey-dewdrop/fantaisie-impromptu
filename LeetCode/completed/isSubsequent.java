package LeetCode.completed;

// import java.util.*;
public class isSubsequent{
    public static void main(String[] args) {
        boolean status = compareStrings("daml", "mal");
        System.out.println(status);
    }

    public static boolean compareStrings(String bigFish, String littleOne){

        boolean status;
        if(littleOne.length() == 0 || littleOne.length() > bigFish.length()){
            return false;
        }   
        
        int i = 0;
        String sub = "";
        for(int j = 0; j < bigFish.length(); j++){
            if(littleOne.charAt(i) == bigFish.charAt(j)){
                sub += littleOne.charAt(i);
                i++;
            }
        }
        if(i == littleOne.length()){
            status = true;
        }
        else{
            status = false;
        }
    
        System.out.println(sub);
        return status;
        
    }
}