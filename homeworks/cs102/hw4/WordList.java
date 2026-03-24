import java.util.ArrayList;

/**
 * WordList class - bir yila ait tum kelimeleri tutar.
 * Quicksort ile siralar, binary search ile arama yapar.
 * HashMap/TreeMap/Collections.sort kullanmak YASAK,
 * bu yuzden her seyi elle yaziyoruz.
 */
public class WordList {

    private ArrayList<Word> words;       // unique kelimelerin listesi
    private int totalWordCount;          // toplam kelime sayisi (tekrarlar dahil)
    private boolean sorted;              // sirali mi degil mi

    /**
     * Constructor - bos bir WordList olusturur.
     */
    public WordList() {
        words = new ArrayList<>();
        totalWordCount = 0;
        sorted = false;
    }

    /**
     * Listeye bir kelime ekler. Eger kelime zaten varsa count'unu arttirir,
     * yoksa yeni Word olusturup listeye ekler.
     * NOT: Liste henuz siralanmamissa linear search yapar.
     * Siralanmissa binary search ile bakar.
     * @param wordText eklenecek kelime
     */
    public void addWord(String wordText) {
        totalWordCount++;

        if (sorted) {
            // sirali ise binary search ile bul
            int index = binarySearchIndex(wordText);
            if (index >= 0) {
                words.get(index).incrementCount();
                return;
            }
        } else {
            // sirali degilse linear search
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getText().equals(wordText)) {
                    words.get(i).incrementCount();
                    return;
                }
            }
        }

        // kelime bulunamadi, yeni ekle
        words.add(new Word(wordText));
        sorted = false; // yeni eleman eklendi, artik sirali degil
    }

    /**
     * Tum kelimeleri toplu olarak ekler (bir article'in kelimelerini).
     * Her kelimeyi addWord ile ekler.
     * @param articleWords kelimelerin string dizisi
     */
    public void addAllWords(String[] articleWords) {
        for (String w : articleWords) {
            if (w.length() > 0) {
                addWord(w);
            }
        }
    }

    /**
     * Quicksort ile kelimeleri alfabetik olarak siralar.
     * Generic quicksort kullanir (Comparable arrayleri icin).
     */
    public void sort() {
        if (words.size() <= 1) {
            sorted = true;
            return;
        }

        // ArrayList'i array'e cevir ki quicksort uygulayabilelim
        Word[] arr = words.toArray(new Word[0]);
        quickSort(arr, 0, arr.length - 1);

        // sirali arrayi tekrar ArrayList'e koy
        words.clear();
        for (Word w : arr) {
            words.add(w);
        }
        sorted = true;
    }

    /**
     * Generic Quicksort - Comparable objeler icin calisan recursive siralama.
     * Pivot olarak ortadaki elemani secer.
     * @param arr siralanacak dizi
     * @param low baslangic indexi
     * @param high bitis indexi
     */
    public static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Quicksort icin partition metodu.
     * Son elemani pivot olarak secer, kucukleri sola buyukleri saga atar.
     * @param arr dizi
     * @param low baslangic
     * @param high bitis (pivot)
     * @return pivot'un son pozisyonu
     */
    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high]; // son elemani pivot olarak sec
        int i = low - 1;    // kucuk elemanlarin son indexi

        for (int j = low; j < high; j++) {
            // eger mevcut eleman pivottan kucuk veya esitse
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                // swap arr[i] ve arr[j]
                T temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // pivotu dogru yerine koy
        T temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    /**
     * Binary Search - sirali listede bir kelimeyi arar.
     * @param wordText aranan kelime
     * @return kelimenin frekansi, bulunamazsa -1
     */
    public int findWord(String wordText) {
        int index = binarySearchIndex(wordText);
        if (index >= 0) {
            return words.get(index).getCount();
        }
        return -1;
    }

    /**
     * Binary search'un index donduren versiyonu.
     * Iceride hem findWord hem addWord tarafindan kullanilir.
     * @param wordText aranan kelime
     * @return index, bulunamazsa -1
     */
    private int binarySearchIndex(String wordText) {
        int low = 0;
        int high = words.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = words.get(mid).getText().compareTo(wordText);

            if (cmp == 0) {
                return mid;       // bulduk!
            } else if (cmp < 0) {
                low = mid + 1;    // ortadaki kucuk, saga bak
            } else {
                high = mid - 1;   // ortadaki buyuk, sola bak
            }
        }
        return -1; // bulunamadi
    }

    /**
     * Unique kelime sayisini dondurur.
     * @return unique kelime adedi
     */
    public int getUniqueWordCount() {
        return words.size();
    }

    /**
     * Toplam kelime sayisini dondurur (tekrarlar dahil).
     * TF hesaplamak icin lazim.
     * @return toplam kelime sayisi
     */
    public int getTotalWordCount() {
        return totalWordCount;
    }

    /**
     * Belirli bir indexteki Word objesini dondurur.
     * @param index istenilen index
     * @return Word objesi
     */
    public Word getWord(int index) {
        return words.get(index);
    }

    /**
     * Kelimenin bu yilda olup olmadigini kontrol eder.
     * IDF hesaplamak icin kullanilir.
     * @param wordText aranan kelime
     * @return true eger kelime bu yilda varsa
     */
    public boolean contains(String wordText) {
        return findWord(wordText) > 0;
    }
}
