public class Word implements Comparable<Word> {

    private String text;
    private int count;

    // creates a new word with count starting at 1
    public Word(String text) {
        this.text = text;
        this.count = 1;
    }

    public String getText() {
        return text;
    }

    public int getCount() {
        return count;
    }

    // increases the count when the same word is seen again
    public void incrementCount() {
        count++;
    }

    // compares two words alphabetically for sorting
    @Override
    public int compareTo(Word other) {
        return this.text.compareTo(other.text);
    }

    @Override
    public String toString() {
        return text + ": " + count;
    }
}
