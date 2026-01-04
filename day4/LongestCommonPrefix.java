package day4;
public class LongestCommonPrefix {

      public static String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                  return "";
            }

            String prefix = "";

            for(int i = 0; i < strs[0].length(); i++){
                  char currentChar = strs[0].charAt(i); 

                  for(int j = 1; j < strs.length; j++){
                        if(i >= strs[j].length() || currentChar != strs[j].charAt(i)){
                              return prefix;
                        }        
                  }
                  prefix += currentChar;
            }
            return prefix;
      }
      public static void main(String[] args) {
            String[] strs = {"flower", "flow", "flight"};
            String result = longestCommonPrefix(strs);
            System.out.println("Longest Common Prefix: " + result);

      }
}

// BAD RUNTIME