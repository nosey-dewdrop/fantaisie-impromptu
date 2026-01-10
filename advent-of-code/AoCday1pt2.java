import java.io.*;
import java.util.Scanner;

public class AoCday1pt2 {

    public static int[] move(String direction, int placement) {
    char dir = direction.charAt(0);
    int steps = Integer.parseInt(direction.substring(1));

    int count = 0;

    // Tam turlar (her 100 adım = 1 kez 0)
    count += steps / 100;
    int rem = steps % 100;

    int start = placement;
    int end;

    if (dir == 'R') {
        // yol üstünde 0'dan geçiş
        if (rem > 0 && start + rem >= 100) count++;

        end = (start + rem) % 100;
    } else { // L
        if (rem > 0 && start - rem < 0) count++;

        end = (start - rem + 100) % 100;
    }

    // 🔥 KRİTİK: dönüş sonunda 0'daysak SAY
    if (end == 0) count++;

    return new int[]{end, count};
}
    public static void main(String[] args) {

        try {
            Scanner scanner = new Scanner(new File("input.txt"));

            int placement = 50;
            long count = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty()) continue;

                int[] result = move(line, placement);
                placement = result[0];
                count += result[1];
            }

            scanner.close();

            System.out.println("password: " + count);

        } catch (FileNotFoundException e) {
            System.out.println("input.txt not found");
        }
    }
}