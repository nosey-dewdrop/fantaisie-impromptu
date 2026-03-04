import java.util.Random;

public class MazeDivision {

    private static final Random RNG = new Random();

    public static char[][] generate(int width, int height) {
        char[][] maze = MazeUtils.createOpenWithBorder(width, height);
        divide(maze, 1, 1, width - 2, height - 2);
        return maze;
    }

    private static void divide(char[][] maze, int startCol, int startRow, int w, int h) {
        if (w < 2 || h < 2) return;

        int wallRow = MazeUtils.nearestEvenBelow(startRow + h / 2);
        int wallCol = MazeUtils.nearestEvenBelow(startCol + w / 2);

        drawHorizontalWall(maze, wallRow, startCol, startCol + w - 1);
        drawVerticalWall(maze, wallCol, startRow, startRow + h - 1);

        int solidSegment = RNG.nextInt(4);

        if (solidSegment != 0) carveHorizontalOpening(maze, wallRow, startCol,          wallCol - 1);
        if (solidSegment != 1) carveHorizontalOpening(maze, wallRow, wallCol + 1, startCol + w - 1);
        if (solidSegment != 2) carveVerticalOpening  (maze, wallCol, startRow,          wallRow - 1);
        if (solidSegment != 3) carveVerticalOpening  (maze, wallCol, wallRow + 1, startRow + h - 1);

        int leftW   = wallCol - startCol;
        int rightW  = (startCol + w - 1) - wallCol;
        int topH    = wallRow - startRow;
        int bottomH = (startRow + h - 1) - wallRow;

        divide(maze, startCol,    startRow,    leftW,  topH);
        divide(maze, wallCol + 1, startRow,    rightW, topH);
        divide(maze, startCol,    wallRow + 1, leftW,  bottomH);
        divide(maze, wallCol + 1, wallRow + 1, rightW, bottomH);
    }

    private static void drawHorizontalWall(char[][] maze, int wallRow, int colStart, int colEnd) {
        for (int c = colStart; c <= colEnd; c++) {
            maze[wallRow][c] = '#';
        }
    }

    private static void drawVerticalWall(char[][] maze, int wallCol, int rowStart, int rowEnd) {
        for (int r = rowStart; r <= rowEnd; r++) {
            maze[r][wallCol] = '#';
        }
    }

    private static void carveHorizontalOpening(char[][] maze, int wallRow, int colStart, int colEnd) {
        int openCol = randomOddIndex(colStart, colEnd);
        if (openCol != -1) {
            maze[wallRow][openCol] = ' ';
        }
    }

    private static void carveVerticalOpening(char[][] maze, int wallCol, int rowStart, int rowEnd) {
        int openRow = randomOddIndex(rowStart, rowEnd);
        if (openRow != -1) {
            maze[openRow][wallCol] = ' ';
        }
    }

    private static int randomOddIndex(int lo, int hi) {
        int firstOdd = (lo % 2 == 1) ? lo : lo + 1;
        if (firstOdd > hi) return -1;
        int count = (hi - firstOdd) / 2 + 1;
        return firstOdd + RNG.nextInt(count) * 2;
    }
}
