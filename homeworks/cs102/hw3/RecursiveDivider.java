/**
 * Generates a maze using Recursive Division with Quad Partitioning.
 * Starts with an open grid and recursively divides regions by adding walls.
 * At each step, horizontal and vertical walls are placed near the center.
 * Three of the four walls get openings; one wall remains solid.
 */
public class RecursiveDivider {

    /**
     * Generates a maze using recursive division.
     * @param maze the Maze object to divide
     */
    public void generate(Maze maze) {
        maze.fillWithPassages();
        divide(maze, 1, 1, maze.getWidth() - 2, maze.getHeight() - 2);
        maze.openEntryAndExit();
    }

    /**
     * Recursively divides the region defined by (startCol, startRow) to (endCol, endRow).
     * Places horizontal and vertical walls near the center, then opens three of the four walls.
     *
     * @param maze     the Maze object
     * @param startCol left boundary (inclusive)
     * @param startRow top boundary (inclusive)
     * @param endCol   right boundary (inclusive)
     * @param endRow   bottom boundary (inclusive)
     */
    private void divide(Maze maze, int startCol, int startRow, int endCol, int endRow) {
        int regionWidth = endCol - startCol + 1;
        int regionHeight = endRow - startRow + 1;

        // Stop if the region is too small to divide
        if (regionWidth < 2 || regionHeight < 2) {
            return;
        }

        // Find the wall positions near the center (must be even indices)
        int wallCol = findEvenCenter(startCol, endCol);
        int wallRow = findEvenCenter(startRow, endRow);

        // Draw horizontal wall
        for (int col = startCol - 1; col <= endCol + 1; col++) {
            if (maze.isInBounds(wallRow, col)) {
                maze.setCell(wallRow, col, '#');
            }
        }

        // Draw vertical wall
        for (int row = startRow - 1; row <= endRow + 1; row++) {
            if (maze.isInBounds(row, wallCol)) {
                maze.setCell(row, wallCol, '#');
            }
        }

        // Determine openings for three of the four walls.
        // The four wall segments are: top, bottom, left, right
        // Randomly choose one wall to remain solid (no opening).
        int solidWall = maze.getRandom().nextInt(4);

        // Opening positions: near the center of each wall segment, at odd indices.
        // Top wall segment: vertical wall above horizontal wall -> opening at odd row
        int openTop = findOddCenter(startRow, wallRow - 1);
        // Bottom wall segment: vertical wall below horizontal wall -> opening at odd row
        int openBottom = findOddCenter(wallRow + 1, endRow);
        // Left wall segment: horizontal wall left of vertical wall -> opening at odd col
        int openLeft = findOddCenter(startCol, wallCol - 1);
        // Right wall segment: horizontal wall right of vertical wall -> opening at odd col
        int openRight = findOddCenter(wallCol + 1, endCol);

        // Open three of the four walls
        if (solidWall != 0 && maze.isInBounds(openTop, wallCol)) {
            maze.setCell(openTop, wallCol, ' ');  // opening in top segment of vertical wall
        }
        if (solidWall != 1 && maze.isInBounds(openBottom, wallCol)) {
            maze.setCell(openBottom, wallCol, ' ');  // opening in bottom segment of vertical wall
        }
        if (solidWall != 2 && maze.isInBounds(wallRow, openLeft)) {
            maze.setCell(wallRow, openLeft, ' ');  // opening in left segment of horizontal wall
        }
        if (solidWall != 3 && maze.isInBounds(wallRow, openRight)) {
            maze.setCell(wallRow, openRight, ' ');  // opening in right segment of horizontal wall
        }

        // Recurse into the four sub-regions (quadrants)
        // Top-left
        divide(maze, startCol, startRow, wallCol - 1, wallRow - 1);
        // Top-right
        divide(maze, wallCol + 1, startRow, endCol, wallRow - 1);
        // Bottom-left
        divide(maze, startCol, wallRow + 1, wallCol - 1, endRow);
        // Bottom-right
        divide(maze, wallCol + 1, wallRow + 1, endCol, endRow);
    }

    /**
     * Finds the even index nearest to the center of the range [start, end].
     * Walls must be placed at even indices.
     */
    private int findEvenCenter(int start, int end) {
        int center = (start + end) / 2;
        if (center % 2 != 0) {
            center--;
        }
        return center;
    }

    /**
     * Finds the odd index nearest to the center of the range [start, end].
     * Openings must be placed at odd indices.
     */
    private int findOddCenter(int start, int end) {
        int center = (start + end) / 2;
        if (center % 2 == 0) {
            center--;
        }
        if (center < start) {
            center += 2;
        }
        return center;
    }
}
