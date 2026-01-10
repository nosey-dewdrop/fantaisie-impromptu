import java.util.*;

public class LanguageList {
    private final List<String> languages = new ArrayList<>();

    public boolean isEmpty() {
        return languages.isEmpty();
    }

    public void addLanguage(String language) {
        if(!languages.contains(language)){
            languages.add(language);
        }
    }

    public void removeLanguage(String language) {
        if(languages.contains(language)){
            languages.remove(language);
        }
    }

    public String firstLanguage() {
        if(!languages.isEmpty()){
            return languages.get(0);
        }
        else{
            return null;
        }
    }

    public int count() {
        int count = languages.size();
        return count;
    }

    public boolean containsLanguage(String language) {
        return languages.contains(language);
    }

    public boolean isExciting() {
        return languages.contains("Java") || languages.contains("Kotlin");
    }
}
