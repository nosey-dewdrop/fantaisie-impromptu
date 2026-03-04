import java.util.Random;

// recursive division maze generator
// open grid + draw walls + carve openings = maze
public class MazeDivision {

    private static final Random RNG = new Random();

    // init the maze and apply recursion
    public static char[][] generate(int width, int height) {
        char[][] maze = MazeUtils.createOpenWithBorder(width, height);
        divide(maze, 1, 1, width - 2, height - 2);
        return maze;
    }

    // split region with walls, carve 3 of 4 openings, recurse into sub-regions
    private static void divide(char[][] maze, int startCol, int startRow, int w, int h) {
        if (w < 2 || h < 2) return; // too small, bail

        // extension: random even index instead of always center
        int wallRow = randomEvenIndex(startRow, startRow + h - 1);
        int wallCol = randomEvenIndex(startCol, startCol + w - 1);

        drawHorizontalWall(maze, wallRow, startCol, startCol + w - 1);
        drawVerticalWall(maze, wallCol, startRow, startRow + h - 1);

        // pick one solid segment, open up the other 3
        int solidSegment = RNG.nextInt(4);

        if (solidSegment != 0) carveHorizontalOpening(maze, wallRow, startCol,          wallCol - 1);
        if (solidSegment != 1) carveHorizontalOpening(maze, wallRow, wallCol + 1, startCol + w - 1);
        if (solidSegment != 2) carveVerticalOpening  (maze, wallCol, startRow,          wallRow - 1);
        if (solidSegment != 3) carveVerticalOpening  (maze, wallCol, wallRow + 1, startRow + h - 1);

        int leftW   = wallCol - startCol;
        int rightW  = (startCol + w - 1) - wallCol;
        int topH    = wallRow - startRow;
        int bottomH = (startRow + h - 1) - wallRow;

        // recurse into all 4 quadrants
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
        if (openCol != -1) maze[wallRow][openCol] = ' ';
    }

    private static void carveVerticalOpening(char[][] maze, int wallCol, int rowStart, int rowEnd) {
        int openRow = randomOddIndex(rowStart, rowEnd);
        if (openRow != -1) maze[openRow][wallCol] = ' ';
    }

    // random odd index in range, -1 if none
    private static int randomOddIndex(int lo, int hi) {
        int firstOdd;
        if (lo % 2 == 1) {
            firstOdd = lo;
        } else {
            firstOdd = lo + 1;
        }
        if (firstOdd > hi) return -1;
        int count = (hi - firstOdd) / 2 + 1;
        return firstOdd + RNG.nextInt(count) * 2;
    }

    // same as randomOddIndex but for even — added for extension
    private static int randomEvenIndex(int lo, int hi) {
        int firstEven;
        if (lo % 2 == 0) {
            firstEven = lo;
        } else {
            firstEven = lo + 1;
        }
        if (firstEven > hi) return lo; // fallback
        int count = (hi - firstEven) / 2 + 1;
        return firstEven + RNG.nextInt(count) * 2;
    }
}