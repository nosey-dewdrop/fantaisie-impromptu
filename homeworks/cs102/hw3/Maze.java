import java.util.Random;

/**
 * creating a 2d maze with character array. # for walls and " " for passages. 
 * walls can only be at even indices, so width and height must be odd.
 */
public class Maze {
    private char[][] grid;
    private int width;
    private int height;
    private Random random;

    public Maze(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];
        this.random = new Random();
    }

    /**
     * fill the entire grid with #
     */
    public void fillWithWalls() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = '#';
            }
        }
    }

    /**
     * Fills the interior of the grid with passages (' ') and edges with walls.
     * Used as the starting state for recursive division.
     */
    public void fillWithPassages() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row == 0 || row == height - 1 || col == 0 || col == width - 1) {
                    grid[row][col] = '#';
                } else {
                    grid[row][col] = ' ';
                }
            }
        }
    }

    /**
     * opens the entry point at the top-left and exit at the bottom-right.
     */
    public void openEntryAndExit() {
        grid[0][1] = ' ';                      
        grid[height - 1][width - 2] = ' ';     
    }

    public char getCell(int row, int col) {
        return grid[row][col];
    }

    public void setCell(int row, int col, char value) {
        grid[row][col] = value;
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Random getRandom() {
        return random;
    }

    /**
     * prints the maze to the console
     */
    public void print() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }
}
