import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextAnalyzer {

    // reads the file and groups words by year
    public static void readFile(String filename, ArrayList<Integer> years,
                                ArrayList<WordList> wordLists) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }

            int space = line.indexOf(' ');
            if (space == -1) {
                continue;
            }

            String date = line.substring(0, space);
            String content = line.substring(space + 1);

            String[] parts = date.split("\\.");
            int year = Integer.parseInt(parts[2]);

            String[] tokens = content.split("\\s+");

            int pos = years.indexOf(year);
            if (pos == -1) {
                years.add(year);
                WordList wl = new WordList();
                wl.addAllWords(tokens);
                wordLists.add(wl);
            } else {
                wordLists.get(pos).addAllWords(tokens);
            }
        }
        sc.close();
    }

    // sorts years in ascending order using selection sort
    public static void sortYears(ArrayList<Integer> years, ArrayList<WordList> wordLists) {
        for (int i = 0; i < years.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < years.size(); j++) {
                if (years.get(j) < years.get(minIdx)) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int tmpYear = years.get(i);
                years.set(i, years.get(minIdx));
                years.set(minIdx, tmpYear);

                WordList tmpWl = wordLists.get(i);
                wordLists.set(i, wordLists.get(minIdx));
                wordLists.set(minIdx, tmpWl);
            }
        }
    }

    // calculates term frequency
    public static double calcTF(int freq, int total) {
        return (double) freq / total;
    }

    // calculates inverse document frequency
    public static double calcIDF(int totalYears, int numYears) {
        return Math.log((double) (totalYears + 1) / (numYears + 1));
    }

    // counts how many years contain a given word
    public static int countYears(String text, ArrayList<WordList> wordLists) {
        int cnt = 0;
        for (int i = 0; i < wordLists.size(); i++) {
            if (wordLists.get(i).findWord(text) > 0) {
                cnt++;
            }
        }
        return cnt;
    }

    // calculates keyness score for a word in a specific year
    public static double calcKeyness(int freq, String text, int yearIdx,
                                     ArrayList<WordList> wordLists) {
        int otherFreq = 0;
        for (int i = 0; i < wordLists.size(); i++) {
            if (i != yearIdx) {
                int f = wordLists.get(i).findWord(text);
                if (f > 0) {
                    otherFreq = otherFreq + f;
                }
            }
        }
        return (double) freq / (otherFreq + 30);
    }

    // calculates tf-idf for all words in a given year
    public static double[] getAllTFIDF(int yearIdx, ArrayList<WordList> wordLists) {
        WordList wl = wordLists.get(yearIdx);
        int numYears = wordLists.size();
        double[] vals = new double[wl.getUniqueWordCount()];

        for (int i = 0; i < wl.getUniqueWordCount(); i++) {
            Word w = wl.getWord(i);
            double tf = calcTF(w.getCount(), wl.getTotalWordCount());
            int yrs = countYears(w.getText(), wordLists);
            double idf = calcIDF(numYears, yrs);
            vals[i] = tf * idf;
        }
        return vals;
    }

    // calculates keyness for all words in a given year
    public static double[] getAllKeyness(int yearIdx, ArrayList<WordList> wordLists) {
        WordList wl = wordLists.get(yearIdx);
        double[] vals = new double[wl.getUniqueWordCount()];

        for (int i = 0; i < wl.getUniqueWordCount(); i++) {
            Word w = wl.getWord(i);
            vals[i] = calcKeyness(w.getCount(), w.getText(), yearIdx, wordLists);
        }
        return vals;
    }

    // finds the indices of the top n highest values
    public static int[] getTopN(double[] vals, int n) {
        int len = vals.length;
        if (n > len) {
            n = len;
        }

        int[] top = new int[n];
        boolean[] used = new boolean[len];

        for (int i = 0; i < n; i++) {
            int maxIdx = -1;
            double maxVal = -1;

            for (int j = 0; j < len; j++) {
                if (!used[j] && vals[j] > maxVal) {
                    maxVal = vals[j];
                    maxIdx = j;
                }
            }

            top[i] = maxIdx;
            if (maxIdx >= 0) {
                used[maxIdx] = true;
            }
        }
        return top;
    }

    // prints the results as a table with years as columns
    public static void printTable(String name, ArrayList<Integer> years,
                                  ArrayList<WordList> wordLists,
                                  double[][] allVals, int n) {
        System.out.println("\n" + name + ":");

        for (int y = 0; y < years.size(); y++) {
            System.out.printf("%-24s", years.get(y));
        }
        System.out.println();

        int[][] topPerYear = new int[years.size()][];
        for (int y = 0; y < years.size(); y++) {
            topPerYear[y] = getTopN(allVals[y], n);
        }

        for (int row = 0; row < n; row++) {
            for (int y = 0; y < years.size(); y++) {
                int idx = topPerYear[y][row];
                if (idx >= 0) {
                    Word w = wordLists.get(y).getWord(idx);
                    String s = String.format("%s: %.4f", w.getText(), allVals[y][idx]);
                    System.out.printf("%-24s", s);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the name of the input file: ");
        String filename = input.nextLine();

        ArrayList<Integer> years = new ArrayList<Integer>();
        ArrayList<WordList> wordLists = new ArrayList<WordList>();

        System.out.println("Reading file...");
        readFile(filename, years, wordLists);

        sortYears(years, wordLists);

        System.out.println("Sorting word lists...");
        for (int i = 0; i < wordLists.size(); i++) {
            wordLists.get(i).sort();
        }

        System.out.println("\nYears found: " + years.size());
        for (int i = 0; i < years.size(); i++) {
            System.out.println(years.get(i) + ": " + wordLists.get(i).getUniqueWordCount()
                    + " unique words, " + wordLists.get(i).getTotalWordCount() + " total words");
        }

        System.out.print("\nEnter the number of top keywords (N): ");
        int n = input.nextInt();

        System.out.println("Calculating TF-IDF...");
        double[][] tfidf = new double[years.size()][];
        for (int i = 0; i < years.size(); i++) {
            tfidf[i] = getAllTFIDF(i, wordLists);
        }

        System.out.println("Calculating Keyness...");
        double[][] keyness = new double[years.size()][];
        for (int i = 0; i < years.size(); i++) {
            keyness[i] = getAllKeyness(i, wordLists);
        }

        printTable("TF-IDF", years, wordLists, tfidf, n);
        printTable("Keyness", years, wordLists, keyness, n);

        input.close();
    }
}
