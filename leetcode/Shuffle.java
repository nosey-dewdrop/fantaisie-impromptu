package leetcode;

import java.util.*;

public class Shuffle {
    
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

        char randomChar = (char) ('a' + random.nextInt(26));
        int randomNum = random.nextInt(newWord.length());
        newWord = newWord.substring(0, randomNum) + Character.toString(randomChar) + newWord.substring(randomNum);
        return newWord;
    }

    public static char findTheDifference(String s, String t) {
        int sumT = 0;
        int sumS = 0;
        for (char ch : s.toCharArray()) {
            sumS += (int) ch;
        }
        for (char ch : t.toCharArray()) {
            sumT += (int) ch;
        }
        int difference = sumT-sumS;
        char ch = (char) difference;
        return ch;

    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        String s = scanner.nextLine();
        String t = shuffleString(s);
        System.out.println(t);

        char ch = findTheDifference(s, t);
        System.out.println(ch);

        scanner.close();

    }
}