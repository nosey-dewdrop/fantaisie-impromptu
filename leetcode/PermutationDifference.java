package leetcode;
import java.util.*;

public class permutationDifference {

    public static int findPermutationDifference(String s, String t) {
        char[] sArray = s.toCharArray();
        int sum = 0;

        for(int i = 0; i < sArray.length; i++){

            int position = t.indexOf(s.charAt(i));
            if(position > i){
                int difference = position - i;
                sum += difference;
            }
            else{
                int difference = i - position;
                sum += difference;
            }
        }
        return sum;
    }

    public static String shuffleString(String s){

        Random random = new Random();
        char[] word = s.toCharArray(); // d,a,m,l,a

        ArrayList<Character> list = new ArrayList<>();
        
        for(int i = 0; i < word.length; i++){
            list.add(word[i]);
        }

        String newWord = "";
        for(int j = 0; j < s.length(); j++){
            int randomIndex = random.nextInt(list.size());
            newWord += Character.toString(list.get(randomIndex));
            list.remove(randomIndex);
        }

        return newWord;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.err.println("Enter a string: ");
        String s = scanner.nextLine();
        String t = shuffleString(s);
        int difference = findPermutationDifference(s, t);
        System.out.println(difference);
        scanner.close();
    }
}
