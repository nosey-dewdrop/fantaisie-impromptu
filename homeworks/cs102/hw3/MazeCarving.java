import java.util.ArrayList;

public class MazeCarving {

    public static char[][] generate(int width, int height) {
        char[][] maze = MazeUtils.createAllWalls(width, height);
        maze[1][1] = ' ';
        carve(maze, 1, 1);
        return maze;
    }

    private static void carve(char[][] maze, int row, int col) {
        ArrayList<int[]> directions = MazeUtils.shuffledDirections();

        for (int[] dir : directions) {
            int newRow = row + dir[0] * 2;
            int newCol = col + dir[1] * 2;

            if (canCarve(maze, newRow, newCol)) {
                removeWall(maze, row, col, dir);
                maze[newRow][newCol] = ' ';
                carve(maze, newRow, newCol);
            }
        }
    }

    private static boolean canCarve(char[][] maze, int row, int col) {
        return MazeUtils.inBounds(maze, row, col) && maze[row][col] == '#';
    }

    private static void removeWall(char[][] maze, int row, int col, int[] dir) {
        maze[row + dir[0]][col + dir[1]] = ' ';
    }
}
