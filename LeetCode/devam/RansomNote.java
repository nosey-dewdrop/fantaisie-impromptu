package LeetCode.devam;
// import java.util.Random;

public class RansomNote {
    public static boolean canConstruct(String ransomNote, String magazine) {
        
        boolean status = false;
        double ransomLength = ransomNote.length();
        int mag = magazine.length();
        
        if(ransomLength > mag || ransomLength == 0|| mag == 0){
            status = false;
            return status;
        }

        else{

            String order = "";
            for(int i = 0; i < magazine.length(); i++){
                String current = Character.toString(magazine.charAt(i));
                if(ransomNote.contains(current)){
                    order += current;
                    // String temp = magazine.replaceAll(current, "");
                }
            }

            if(order.length()==ransomLength){
                status = true;
            }
            else{
                status = false;
                
            }
        }
        return status;
    }

    
        

    public static void main(String[] args) {
        boolean status = canConstruct("dam", "damla");
        System.out.println(status);


    }     
}
