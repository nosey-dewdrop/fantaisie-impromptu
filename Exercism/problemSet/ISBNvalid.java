package Exercism.problemSet;

public class ISBNvalid {

    // public static boolean isValid(String isbn){
    //     boolean valid = true;

    //     //Character.getNumericValue(singleChar)
    //     isbn = isbn.replaceAll("-","");

    //     int formula = 0;

    //     if(isbn.length()== 10 && isbn.substring(0, isbn.length()-2).matches("\\d+") && (isbn.charAt(isbn.length()-1) == 'X'||isbn.charAt(isbn.length()-1) == 'x')){
    //         if(isbn.charAt(isbn.length()-1) == 'X'||isbn.charAt(isbn.length()-1) == 'x'){
    //             isbn = isbn.substring(0, isbn.length()-2);
    //             formula = 
    //             Character.valueOf(isbn.charAt(0))*10 +  Character.valueOf(isbn.charAt(1))*9 +  Character.valueOf(isbn.charAt(2))*8 +  
    //             Character.valueOf(isbn.charAt(3))*7 +  Character.valueOf(isbn.charAt(4))*6 + Character.valueOf(isbn.charAt(5))*5 + 
    //             Character.valueOf(isbn.charAt(6))*4 + Character.valueOf(isbn.charAt(7))*3 + Character.valueOf(isbn.charAt(8))*2 + 10*1;
    //         }

    //         else{
    //             formula = 
    //             Character.valueOf(isbn.charAt(0))*10 +  Character.valueOf(isbn.charAt(1))*9 +  Character.valueOf(isbn.charAt(2))*8 +  
    //             Character.valueOf(isbn.charAt(3))*7 +  Character.valueOf(isbn.charAt(4))*6 + Character.valueOf(isbn.charAt(5))*5 + 
    //             Character.valueOf(isbn.charAt(6))*4 + Character.valueOf(isbn.charAt(7))*3 + Character.valueOf(isbn.charAt(8))*2 +  Character.valueOf(isbn.charAt(9))*1;
    //         }

    //         int finalVer = formula % 11;
    //         if(finalVer == 0){
    //             valid = true;
    //         }
    //         else{
    //             valid = false;
    //         }

    //     }
    //     else{
    //         return false;
    //     }
    //     return valid;
    // }
    // public static void main(String[] args) {
    //     String ISBN = "3-598-21508-8";
    //     boolean isValid = isValid(ISBN);
    //     System.out.println(isValid);
    // }

    public static boolean runTimeValidation(String isbn){
        isbn = isbn.replaceAll("-", "").replaceAll(" ", "");
        int numbers = isbn.length();

        if(isbn.isEmpty() || isbn == null){
            return false;
        }

        if( Character.isDigit(isbn.charAt(0)) && isbn.length()==10){ // yanlışlığını denemek için bile charAt denememesi gerekiyor maalesef...
            // o yüzden boşluğu kontrol eden kod bloğunu başa koymak zorundatyız.
            int sum = 0;

            for(int i = 0; i < numbers-1; i ++){
                if(Character.isDigit(isbn.charAt(i))){
                    sum+= Integer.parseInt(Character.toString(isbn.charAt(i)))*(numbers-i);
                }
                else{
                    return false;
                }
            }

            if(isbn.charAt(numbers-1) == 'X' || isbn.charAt(numbers-1) == 'x'){
                sum += 10*1;
            }
            else if(Character.isDigit(isbn.charAt(numbers-1))){
                sum += Integer.parseInt(Character.toString(isbn.charAt(numbers-1)))*1;
            }
            else{
                return false;
            }
            if(sum%11==0){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String ISBN = "3-598-21508-8";
        boolean isValid = runTimeValidation(ISBN);
        System.out.println(isValid);

        String emptyISBN = "";

        boolean isValidE = runTimeValidation(emptyISBN);
        System.out.println(isValidE);
    }
}
