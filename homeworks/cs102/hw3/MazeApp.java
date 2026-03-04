import java.util.Scanner;

public class MazeApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Maze Generator ===");

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    runMazeGeneration(scanner, "carving");
                    break;
                case 2:
                    runMazeGeneration(scanner, "division");
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Generate maze using Maze Carving");
        System.out.println("2. Generate maze using Recursive Division");
        System.out.println("3. Exit");
    }

    // this method handles the common logic for both maze generation methods.
    private static void runMazeGeneration(Scanner scanner, String method) {
        int width  = readOddPositive(scanner, "Enter maze width  (odd, positive): ");
        int height = readOddPositive(scanner, "Enter maze height (odd, positive): ");

        char[][] maze;

        if (method.equals("carving")) {
            maze = MazeCarving.generate(width, height);
        } else {
            maze = MazeDivision.generate(width, height);
        }

        System.out.println();
        MazeUtils.printMaze(maze);
    }

    static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine();
                return val;
            } else {
                System.out.println("Please enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    static int readOddPositive(Scanner scanner, String prompt) {
        while (true) {
            int val = readInt(scanner, prompt);
            if (val <= 0) {
                System.out.println("Error: Size must be positive.");
            } else if (val % 2 == 0) {
                System.out.println("Error: Size must be odd.");
            } else {
                return val;
            }
        }
    }
}
