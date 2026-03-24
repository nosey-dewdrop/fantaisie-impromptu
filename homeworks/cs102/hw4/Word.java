/**
 * Word class - her bir kelimeyi ve kac kere gectigini tutar.
 * Comparable implement eder ki quicksort ile siralanabilsin.
 */
public class Word implements Comparable<Word> {

    private String text;  // kelimenin kendisi
    private int count;    // kac kere gecti

    /**
     * Constructor - yeni bir Word olusturur, count 1 ile baslar.
     * @param text kelimenin string hali
     */
    public Word(String text) {
        this.text = text;
        this.count = 1;
    }

    /**
     * Kelimenin textini dondurur.
     * @return kelime metni
     */
    public String getText() {
        return text;
    }

    /**
     * Kelimenin kac kez gectigini dondurur.
     * @return frekans sayisi
     */
    public int getCount() {
        return count;
    }

    /**
     * Kelimenin frekansini 1 arttirir.
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Kelimenin frekansini belirli bir deger kadar arttirir.
     * @param amount arttirilacak miktar
     */
    public void incrementCount(int amount) {
        count += amount;
    }

    /**
     * Iki kelimeyi alfabetik olarak karsilastirir (lexicographic).
     * Quicksort icin gerekli.
     * @param other karsilastirilacak diger Word
     * @return negatif, sifir veya pozitif
     */
    @Override
    public int compareTo(Word other) {
        return this.text.compareTo(other.text);
    }

    /**
     * Kelimenin string temsilini dondurur.
     * @return "text: count" formatinda string
     */
    @Override
    public String toString() {
        return text + ": " + count;
    }
}
