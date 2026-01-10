class Acronym {

    String acronym;
    
    Acronym(String phrase) {
        this.acronym = getAcronym(phrase);
    }

    String get() {
        return this.acronym;
    }

    String getAcronym(String phrase){
        String[] words = wordCalculator(phrase);
        StringBuilder result = new StringBuilder();
        
        for(String word: words){
            result.append(word.charAt(0));
        }
        return result.toString().toUpperCase();
    }

    String[] wordCalculator(String str){
        String[] words = str.split("[^a-zA-Z0-9\\']+");
        return words;
    }

}

// stringi al, stringi böl?? bunun ilk harflerini al.