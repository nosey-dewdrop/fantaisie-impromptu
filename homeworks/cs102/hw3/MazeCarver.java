/**
 * Generates a maze using the Maze Carving (recursive backtracking) method.
 * Starts with a grid full of walls and carves passages recursively.
 */


public class MazeCarver {

    private static final int[][] DIRECTIONS = {
        {-2, 0}, 
        {2, 0},  
        {0, -2},  
        {0, 2}    
    };

    public void generate(Maze maze) {
        maze.fillWithWalls();
        maze.setCell(1, 1, ' ');  // mark starting cell as passage
        carve(maze, 1, 1);
        maze.openEntryAndExit();
    }

    /**
     * recursively carves passages from the given cell.
     */
    private void carve(Maze maze, int row, int col) {
        int[] order = getShuffledOrder(maze);

        for (int i = 0; i < 4; i++) {
            int dirIndex = order[i];
            int newRow = row + DIRECTIONS[dirIndex][0];
            int newCol = col + DIRECTIONS[dirIndex][1];

            if (maze.isInBounds(newRow, newCol) && maze.getCell(newRow, newCol) == '#') {
                // remove the wall
                int wallRow = row + DIRECTIONS[dirIndex][0] / 2;
                int wallCol = col + DIRECTIONS[dirIndex][1] / 2;
                maze.setCell(wallRow, wallCol, ' ');

                // mark the neighbor as a passage
                maze.setCell(newRow, newCol, ' ');

                // recurse from the neighbor
                carve(maze, newRow, newCol);
            }
        }
    }

    /**
     * returns an array {0,1,2,3} shuffled randomly ps: fisher-yates shuffle. 
     */
    private int[] getShuffledOrder(Maze maze) {
        int[] order = {0, 1, 2, 3};
        for (int i = 3; i > 0; i--) {
            int j = maze.getRandom().nextInt(i + 1);
            int temp = order[i];
            order[i] = order[j];
            order[j] = temp;
        }
        return order;
    }
}
