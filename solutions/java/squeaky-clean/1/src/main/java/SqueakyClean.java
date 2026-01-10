class SqueakyClean {
    static String clean(String identifier) {

        String result = identifier.replaceAll(" ", "_");

        for(int i = 0; i < result.length(); i++){

            char current = result.charAt(i);
            if(current == '-'){
                result  = result.substring(0, i) + result.substring(i,i+2).toUpperCase() + result.substring(i+2);
            }
            if(current == '0'){
                result = result.replaceAll("0", "o");
            }
            if(current == '1'){
                result = result.replaceAll("1", "l");
            }
            if(current == '3'){
                result = result.replaceAll("3", "e");
            }
            if(current == '4'){
                result = result.replaceAll("4", "a");
            }
            if(current == '7'){
                result = result.replaceAll("7", "t");
            }
        }
        
        result = result.replaceAll("[^a-zA-Z_]", "");
        return result;
    }
}
