class ReverseString {

    String reverse(String inputString) {
        int length = inputString.length();
        String newString = "";
        for(int i= length-1; i >= 0; i--){
            newString += Character.toString(inputString.charAt(i));
        }
        return newString;
    }
}
