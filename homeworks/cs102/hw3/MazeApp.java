import java.util.Scanner;

/**
 * Console-based application for maze generation.
 * Provides a menu to generate mazes using Maze Carving or Recursive Division.
 */
public class MazeApp {

    private Scanner scanner;
    private MazeCarver mazeCarver;
    private RecursiveDivider recursiveDivider;

    public MazeApp() {
        scanner = new Scanner(System.in);
        mazeCarver = new MazeCarver();
        recursiveDivider = new RecursiveDivider();
    }

    /**
     * Runs the main menu loop.
     */
    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    generateWithCarving();
                    break;
                case 2:
                    generateWithDivision();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Prints the main menu options.
     */
    private void printMenu() {
        System.out.println();
        System.out.println("=== Maze Generator ===");
        System.out.println("1. Generate maze (Maze Carving)");
        System.out.println("2. Generate maze (Recursive Division)");
        System.out.println("3. Exit");
    }

    private void generateWithCarving() {
        int[] dimensions = readDimensions();
        if (dimensions == null) {
            return;
        }
        Maze maze = new Maze(dimensions[0], dimensions[1]);
        mazeCarver.generate(maze);
        System.out.println();
        maze.print();
    }

    private void generateWithDivision() {
        int[] dimensions = readDimensions();
        if (dimensions == null) {
            return;
        }
        Maze maze = new Maze(dimensions[0], dimensions[1]);
        recursiveDivider.generate(maze);
        System.out.println();
        maze.print();
    }

    private int[] readDimensions() {
        int width = readInt("Enter maze width (odd, positive): ");
        if (width <= 0) {
            System.out.println("Error: Width must be a positive number.");
            return null;
        }
        if (width % 2 == 0) {
            System.out.println("Error: Width must be an odd number.");
            return null;
        }

        int height = readInt("Enter maze height (odd, positive): ");
        if (height <= 0) {
            System.out.println("Error: Height must be a positive number.");
            return null;
        }
        if (height % 2 == 0) {
            System.out.println("Error: Height must be an odd number.");
            return null;
        }

        return new int[]{width, height};
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Error: Please enter a valid integer.");
            scanner.next();  // consume invalid token
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }
    public static void main(String[] args) {
        MazeApp app = new MazeApp();
        app.run();
    }
}
