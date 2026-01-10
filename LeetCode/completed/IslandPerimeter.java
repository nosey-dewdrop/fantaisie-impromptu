package LeetCode.completed;
public class IslandPerimeter {
    public static int islandPerimeter(int[][] grid){
        int count = 0;
        int lines = 0;
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                int current = grid[i][j];
                
                if(current == 1){
                    count++;
                    // ilk satır için.
                    if(i == 0){
                        // ilk satırın ilk kutusu için.
                        if(j ==0){
                            if(j+1 < grid[i].length){
                                if(grid[i][j+1] == 1){
                                    lines++;
                                }
                            }
                            if(i+1 < grid.length){
                                if(grid[i+1][j] == 1){
                                    lines++;
                                }
                            }
                        }
                        
                        else if(j == grid[i].length - 1){
                            if(i+1 < grid.length){
                                if(grid[i+1][j] == 1){
                                    lines++;
                                }
                            }
                            if(grid[i][j-1] == 1){
                                lines++;
                            }
                        }
                        else{
                            if(grid[i][j-1] == 1){
                                lines++;
                            }
                            if(j+1 < grid[i].length){
                                if(grid[i][j+1] == 1){
                                    lines++;
                                }
                            }
                            if(i+1 < grid.length){
                                if(grid[i+1][j] == 1){
                                    lines++;
                                }
                            }
                        }
                    }
                    
                    // varsa son satır için. yani eğer yoksa sorun da yoktur diye düşünüyorum!
                    else if(i == grid.length - 1){
                        if(j ==0){
                            if(j+1 < grid[i].length){
                                if(grid[i][j+1] == 1){
                                    lines++;
                                }
                            }
                            if(grid[i-1][j] == 1){
                                lines++;
                            }
                        }
                        else if(j == grid[i].length - 1){
                            if(grid[i][j-1] == 1){
                                lines++;
                            }
                            if(grid[i-1][j] == 1){
                                lines++;
                            }
                        }
                        else{
                            if(grid[i][j-1] == 1){
                                lines++;
                            }
                            if(j+1 < grid[i].length){
                                if(grid[i][j+1] == 1){
                                    lines++;
                                }
                            }
                            if(grid[i-1][j] == 1){
                                lines++;
                            }
                        }
                    }
                    
                    // orta satırlar için.
                    else{
                        if(j ==0){
                            if(j+1 < grid[i].length){
                                if(grid[i][j+1]==1){
                                    lines++;
                                }
                            }
                            if(grid[i-1][j]==1){
                                lines++;
                            }
                            if(i+1 < grid.length){
                                if(grid[i+1][j]==1){
                                    lines++;
                                }
                            }
                        }
                        else if(j == grid[i].length - 1){
                            if(grid[i][j-1]==1){
                                lines++;
                            }
                            if(grid[i-1][j]==1){
                                lines++;
                            }
                            if(i+1 < grid.length){
                                if(grid[i+1][j]==1){
                                    lines++;
                                }
                            }
                        }
                        else{
                            if(grid[i-1][j]==1){
                                lines++;
                            }
                            if(i+1 < grid.length){
                                if(grid[i+1][j]==1){
                                    lines++;
                                }
                            }
                            if(grid[i][j-1]==1){
                                lines++;
                            }
                            if(j+1 < grid[i].length){
                                if(grid[i][j+1]==1){
                                    lines++;
                                }
                            }
                        }
                    }
                }
            }
        }  
        
        
        System.out.println("The line count is: " + lines);
        System.out.println("There are " + count + " cubes.");
        int perimeter = count*4 - (lines);

        return perimeter;

    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 0, 0}};
        int perimeter = islandPerimeter(grid);
        System.out.println(perimeter);

    
        int[][] singleGrid = {{1}};
        int singleGridPerimeter = islandPerimeter(singleGrid);
        System.out.println(singleGridPerimeter);

        int[][] doubleGrid = {{1,1}};
        int doubleGridPerimeter = islandPerimeter(doubleGrid);
        System.out.println(doubleGridPerimeter);

        int[][] strangeGrid = {{0,1},{0,1}};
        int strangePerimeter = islandPerimeter(strangeGrid);
        System.out.println(strangePerimeter);


    }
}




