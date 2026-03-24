import java.util.ArrayList;

public class WordList {

    private ArrayList<Word> words;
    private int totalWords;
    private boolean isSorted;

    public WordList() {
        words = new ArrayList<Word>();
        totalWords = 0;
        isSorted = false;
    }

    // adds a word, if it already exists increments its count
    public void addWord(String text) {
        totalWords++;

        if (isSorted) {
            int idx = binarySearchIndex(text);
            if (idx >= 0) {
                words.get(idx).incrementCount();
                return;
            }
        } else {
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getText().equals(text)) {
                    words.get(i).incrementCount();
                    return;
                }
            }
        }

        words.add(new Word(text));
        isSorted = false;
    }

    // adds all words from an article
    public void addAllWords(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() > 0) {
                addWord(arr[i]);
            }
        }
    }

    // sorts the words alphabetically using quicksort
    public void sort() {
        if (words.size() <= 1) {
            isSorted = true;
            return;
        }

        Word[] arr = words.toArray(new Word[0]);
        quickSort(arr, 0, arr.length - 1);

        words.clear();
        for (int i = 0; i < arr.length; i++) {
            words.add(arr[i]);
        }
        isSorted = true;
    }

    // generic quicksort that works with any Comparable array
    public static <T extends Comparable<T>> void quickSort(T[] arr, int low, int high) {
        if (low < high) {
            int pIdx = partition(arr, low, high);
            quickSort(arr, low, pIdx - 1);
            quickSort(arr, pIdx + 1, high);
        }
    }

    // partitions the array around the last element as pivot
    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                T tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }

        T tmp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = tmp;

        return i + 1;
    }

    // finds a word using binary search, returns its count or -1
    public int findWord(String text) {
        int idx = binarySearchIndex(text);
        if (idx >= 0) {
            return words.get(idx).getCount();
        }
        return -1;
    }

    // binary search helper, returns the index or -1
    private int binarySearchIndex(String text) {
        int low = 0;
        int high = words.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = words.get(mid).getText().compareTo(text);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int getUniqueWordCount() {
        return words.size();
    }

    public int getTotalWordCount() {
        return totalWords;
    }

    public Word getWord(int i) {
        return words.get(i);
    }
}
