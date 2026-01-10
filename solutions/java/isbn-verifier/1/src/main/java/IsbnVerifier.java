class IsbnVerifier {

    public static boolean isValid(String isbn){
        isbn = isbn.replaceAll("-", "").replaceAll(" ", "");
        int numbers = isbn.length();

        if(isbn.isEmpty() || isbn == null){
            return false;
        }
        
        if( Character.isDigit(isbn.charAt(0)) && isbn.length()==10){
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
}
