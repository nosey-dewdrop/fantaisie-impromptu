import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * TextAnalyzer - ana sinif.
 * Dosyadan haberleri okur, yillara gore gruplar,
 * TF-IDF ve Keyness hesaplar, en onemli kelimeleri gosterir.
 */
public class TextAnalyzer {

    /**
     * Dosyayi okur, her satiri parse eder, yillara gore WordList'ler olusturur.
     * @param filename okunacak dosyanin adi
     * @param years yillarin listesi (doldurulacak)
     * @param wordLists her yila karsilik gelen WordList'ler (doldurulacak)
     */
    public static void readFile(String filename, ArrayList<Integer> years,
                                ArrayList<WordList> wordLists) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filename));

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            // ilk bosluga gore ayir: tarih ve icerik
            int firstSpace = line.indexOf(' ');
            if (firstSpace == -1) continue;

            String dateStr = line.substring(0, firstSpace);  // "dd.mm.yyyy"
            String content = line.substring(firstSpace + 1);  // geri kalan icerik

            // yili cikar (son 4 karakter veya noktadan sonra)
            String[] dateParts = dateStr.split("\\.");
            int year = Integer.parseInt(dateParts[2]);

            // kelimeleri ayir
            String[] articleWords = content.split("\\s+");

            // bu yil icin WordList bul veya yeni olustur
            int yearIndex = years.indexOf(year);
            if (yearIndex == -1) {
                // yeni yil, listeye ekle
                years.add(year);
                WordList wl = new WordList();
                wl.addAllWords(articleWords);
                wordLists.add(wl);
            } else {
                // mevcut yil, kelimeleri ekle
                wordLists.get(yearIndex).addAllWords(articleWords);
            }
        }
        sc.close();
    }

    /**
     * Yillari kucukten buyuge siralar (wordList'leri de ayni sirada tutar).
     * Basit selection sort - yil sayisi az oldugu icin yeterli.
     * @param years yil listesi
     * @param wordLists WordList listesi
     */
    public static void sortYears(ArrayList<Integer> years, ArrayList<WordList> wordLists) {
        for (int i = 0; i < years.size() - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < years.size(); j++) {
                if (years.get(j) < years.get(minIdx)) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                // swap years
                int tempYear = years.get(i);
                years.set(i, years.get(minIdx));
                years.set(minIdx, tempYear);

                // swap wordlists (ayni sirada tutmak icin)
                WordList tempWl = wordLists.get(i);
                wordLists.set(i, wordLists.get(minIdx));
                wordLists.set(minIdx, tempWl);
            }
        }
    }

    /**
     * TF (Term Frequency) hesaplar.
     * TF = kelimenin bu yildaki frekansi / bu yildaki toplam kelime sayisi
     * @param wordCount kelimenin frekansi
     * @param totalWords yildaki toplam kelime sayisi
     * @return TF degeri
     */
    public static double calculateTF(int wordCount, int totalWords) {
        return (double) wordCount / totalWords;
    }

    /**
     * IDF (Inverse Document Frequency) hesaplar.
     * IDF = ln((toplam yil sayisi + 1) / (kelimeyi iceren yil sayisi + 1))
     * Her yil bir "document" olarak kabul edilir.
     * @param totalYears toplam yil sayisi
     * @param yearsContaining kelimenin gectigi yil sayisi
     * @return IDF degeri
     */
    public static double calculateIDF(int totalYears, int yearsContaining) {
        return Math.log((double) (totalYears + 1) / (yearsContaining + 1));
    }

    /**
     * Bir kelimenin kac farkli yilda gectigini sayar.
     * IDF hesaplamak icin gerekli.
     * @param wordText aranan kelime
     * @param wordLists tum yillarin WordList'leri
     * @return kelimenin gectigi yil sayisi
     */
    public static int countYearsContaining(String wordText, ArrayList<WordList> wordLists) {
        int count = 0;
        for (WordList wl : wordLists) {
            if (wl.findWord(wordText) > 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Keyness hesaplar.
     * keyness = freq(word, year) / (diger yillardaki toplam freq + 30)
     * Bir kelimenin belirli bir yilda ne kadar "ozel" oldugunu gosterir.
     * @param wordFreqInYear kelimenin bu yildaki frekansi
     * @param wordText kelimenin kendisi
     * @param currentYearIndex mevcut yilin indexi
     * @param wordLists tum WordList'ler
     * @return keyness degeri
     */
    public static double calculateKeyness(int wordFreqInYear, String wordText,
                                          int currentYearIndex, ArrayList<WordList> wordLists) {
        int otherYearsFreq = 0;
        for (int i = 0; i < wordLists.size(); i++) {
            if (i != currentYearIndex) {
                int freq = wordLists.get(i).findWord(wordText);
                if (freq > 0) {
                    otherYearsFreq += freq;
                }
            }
        }
        return (double) wordFreqInYear / (otherYearsFreq + 30);
    }

    /**
     * Bir yil icin tum kelimelerin TF-IDF degerlerini hesaplar.
     * Her kelime icin [tfidf, index] ciftini dondurur.
     * @param yearIndex yilin indexi
     * @param wordLists tum WordList'ler
     * @return her kelimenin TF-IDF degeri (index sirali)
     */
    public static double[] calculateAllTFIDF(int yearIndex, ArrayList<WordList> wordLists) {
        WordList wl = wordLists.get(yearIndex);
        int totalYears = wordLists.size();
        double[] tfidfValues = new double[wl.getUniqueWordCount()];

        for (int i = 0; i < wl.getUniqueWordCount(); i++) {
            Word w = wl.getWord(i);
            double tf = calculateTF(w.getCount(), wl.getTotalWordCount());
            int yearsContaining = countYearsContaining(w.getText(), wordLists);
            double idf = calculateIDF(totalYears, yearsContaining);
            tfidfValues[i] = tf * idf;
        }
        return tfidfValues;
    }

    /**
     * Bir yil icin tum kelimelerin Keyness degerlerini hesaplar.
     * @param yearIndex yilin indexi
     * @param wordLists tum WordList'ler
     * @return her kelimenin keyness degeri (index sirali)
     */
    public static double[] calculateAllKeyness(int yearIndex, ArrayList<WordList> wordLists) {
        WordList wl = wordLists.get(yearIndex);
        double[] keynessValues = new double[wl.getUniqueWordCount()];

        for (int i = 0; i < wl.getUniqueWordCount(); i++) {
            Word w = wl.getWord(i);
            keynessValues[i] = calculateKeyness(w.getCount(), w.getText(), yearIndex, wordLists);
        }
        return keynessValues;
    }

    /**
     * Verilen degerler dizisinden en buyuk N tanesinin indexlerini bulur.
     * Top N keyword bulmak icin kullanilir.
     * Her seferinde maksimumu bulup isaretleyerek ilerler.
     * @param values degerler dizisi
     * @param n kac tane isteniyor
     * @return en buyuk N degerin indexleri
     */
    public static int[] getTopNIndices(double[] values, int n) {
        int len = values.length;
        if (n > len) n = len;

        int[] topIndices = new int[n];
        boolean[] used = new boolean[len]; // secilmis indexleri isaretle

        for (int i = 0; i < n; i++) {
            int maxIdx = -1;
            double maxVal = -1;

            for (int j = 0; j < len; j++) {
                if (!used[j] && values[j] > maxVal) {
                    maxVal = values[j];
                    maxIdx = j;
                }
            }

            topIndices[i] = maxIdx;
            if (maxIdx >= 0) {
                used[maxIdx] = true;
            }
        }
        return topIndices;
    }

    /**
     * Sonuclari tablo formatinda ekrana yazdirir.
     * Her yil icin top N kelimeyi ve degerini gosterir.
     * @param metricName "TF-IDF" veya "Keyness"
     * @param years yil listesi
     * @param wordLists WordList listesi
     * @param allValues her yil icin metrik degerleri
     * @param n kac kelime gosterilecek
     */
    public static void printResults(String metricName, ArrayList<Integer> years,
                                    ArrayList<WordList> wordLists,
                                    double[][] allValues, int n) {
        System.out.println("\n" + metricName + ":");

        // baslik satiri - yillari yazdir
        for (int y = 0; y < years.size(); y++) {
            System.out.printf("%-24s", years.get(y));
        }
        System.out.println();

        // her satir icin top N'den birini yazdir
        int[][] topIndicesPerYear = new int[years.size()][];
        for (int y = 0; y < years.size(); y++) {
            topIndicesPerYear[y] = getTopNIndices(allValues[y], n);
        }

        for (int row = 0; row < n; row++) {
            for (int y = 0; y < years.size(); y++) {
                int idx = topIndicesPerYear[y][row];
                if (idx >= 0) {
                    Word w = wordLists.get(y).getWord(idx);
                    String entry = String.format("%s: %.4f", w.getText(), allValues[y][idx]);
                    System.out.printf("%-24s", entry);
                }
            }
            System.out.println();
        }
    }

    /**
     * Main metod - programin giris noktasi.
     * Kullanicidan dosya adini alir, dosyayi okur, analiz yapar, sonuclari gosterir.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        // kullanicidan dosya adini al
        System.out.print("Enter the name of the input file: ");
        String filename = input.nextLine();

        // yillar ve wordlist'ler
        ArrayList<Integer> years = new ArrayList<>();
        ArrayList<WordList> wordLists = new ArrayList<>();

        // dosyayi oku ve parse et
        System.out.println("Reading file...");
        readFile(filename, years, wordLists);

        // yillari sirala
        sortYears(years, wordLists);

        // her yilin WordList'ini quicksort ile sirala
        System.out.println("Sorting word lists...");
        for (WordList wl : wordLists) {
            wl.sort();
        }

        // kac yil ve kac kelime oldugunu goster
        System.out.println("\nYears found: " + years.size());
        for (int i = 0; i < years.size(); i++) {
            System.out.println(years.get(i) + ": " + wordLists.get(i).getUniqueWordCount()
                    + " unique words, " + wordLists.get(i).getTotalWordCount() + " total words");
        }

        // kullanicidan kac keyword istedigini sor
        System.out.print("\nEnter the number of top keywords (N): ");
        int n = input.nextInt();

        // TF-IDF hesapla
        System.out.println("Calculating TF-IDF...");
        double[][] tfidfValues = new double[years.size()][];
        for (int i = 0; i < years.size(); i++) {
            tfidfValues[i] = calculateAllTFIDF(i, wordLists);
        }

        // Keyness hesapla
        System.out.println("Calculating Keyness...");
        double[][] keynessValues = new double[years.size()][];
        for (int i = 0; i < years.size(); i++) {
            keynessValues[i] = calculateAllKeyness(i, wordLists);
        }

        // Sonuclari yazdir
        printResults("TF-IDF", years, wordLists, tfidfValues, n);
        printResults("Keyness", years, wordLists, keynessValues, n);

        input.close();
    }
}
