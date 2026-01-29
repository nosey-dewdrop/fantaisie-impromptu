package leetcode;
public class ShiftString {

    public static String stringShift(String s, int[][] shift) {

        String shifted = s;
        int iterations = shift.length;
        int i = 0;

        while(i < iterations){
            int direction =shift[i][0];
            int number = shift[i][1]%s.length();

            if(direction == 1){
                String a = shifted.substring(s.length()-number);
                String b = shifted.substring(0, s.length()-number);
                shifted = a + b;
            }
            else if(direction == 0){
                String a = shifted.substring(0,number);
                String b = shifted.substring(number);
                shifted = b + a;
            }
            else{
                System.out.println("NO Direction!");
            }
            i++;
        }
        return shifted;
    }

    public static void main(String[] args) {
        
        String s = "abcdefg";
        int [][] shift = {{1,1},{1,1},{0,2},{1,3}};

        String shifted = stringShift(s, shift);
        System.out.println(shifted);
    }
    
}
