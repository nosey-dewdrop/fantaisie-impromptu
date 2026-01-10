import java.util.*;
public class IsogramChecker {
    public boolean isIsogram(String input){
        String word = input.replaceAll(" ","").replaceAll("-","");
        word = word.toLowerCase();
        
        boolean isIsogram = true;
        int[] position = new int[26];
        char[] letters = word.toCharArray(); // d,a,m,l,a

        for(char letter : letters){
            position[letter-'a']++;
        }

        for(int pos : position){
            if(pos > 1){
                isIsogram = false;
                return isIsogram;
            }
        }
        return isIsogram;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IsogramChecker isogram = new IsogramChecker();

        System.out.println("Enter a word: ");
        String input = scanner.nextLine();
        boolean isIsogram = isogram.isIsogram(input);
    
        if(isIsogram){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }

        scanner.close();
    }
}
