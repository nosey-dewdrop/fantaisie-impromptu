import java.util.ArrayList;
import java.util.Collections;
/**
 * this is the first class and many classes uses static methods implemented in thhis class.
 * only fancy method here is shuffledDirections, which returns a new list of the 4 directions in random order.
 */

public class MazeUtils {

    public static final int[][] DIRECTIONS = {
        {-1,  0},
        { 1,  0},
        { 0, -1},
        { 0,  1}
    };

    public static char[][] createAllWalls(int width, int height) {
        char[][] maze = new char[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                maze[r][c] = '#';
            }
        }
        maze[0][1] = ' ';
        maze[height - 1][width - 2] = ' ';
        return maze;
    }

    public static char[][] createOpenWithBorder(int width, int height) {
        char[][] maze = new char[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                maze[r][c] = ' ';
            }
        }

        for (int c = 0; c < width; c++) {
            maze[0][c] = '#';
            maze[height - 1][c] = '#';
        }
        for (int r = 0; r < height; r++) {
            maze[r][0] = '#';
            maze[r][width - 1] = '#';
        }

        maze[0][1] = ' ';
        maze[height - 1][width - 2] = ' ';
        return maze;
    }

    public static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            System.out.println(new String(row));
        }
    }

    public static ArrayList<int[]> shuffledDirections() {
        ArrayList<int[]> dirs = new ArrayList<>();
        for (int[] d : DIRECTIONS) {
            dirs.add(d);
        }
        Collections.shuffle(dirs);
        return dirs;
    }

    public static int nearestEvenBelow(int index) {
        if (index % 2 == 0) {
            return index;
        } 
        else {
            return index - 1;
        }
    }

    public static int nearestOddBelow(int index) {
        if (index % 2 == 1) {
            return index;
        } 
        else {
            return index - 1;
        }
    }

    public static boolean inBounds(char[][] maze, int row, int col) {
        if (row < 0 || row >= maze.length) {
            return false;
        }
        if (col < 0 || col >= maze[0].length) {
            return false;
        }
        return true;
    }
}
