package LeetCode.completed;

public class firstOccurence {

    public static int firstSeen(String haystack, String needle){

        if(haystack.length() == 0 || needle.length() == 0 || needle.length() > haystack.length()){
            return -1;
        }
        else{
            return haystack.indexOf(needle);
        }
    }


    public static void main (String args[]){

        String haystack = "hello";
        String needle = "ll";

        int index = firstSeen(haystack, needle);
        System.out.println(index);
    }
}
