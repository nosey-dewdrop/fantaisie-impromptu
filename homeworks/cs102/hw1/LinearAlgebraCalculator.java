import java.util.Scanner;

public class LinearAlgebraCalculator {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter a vector or matrix:");
        Algebraic current = readAlgebraic(scanner);
        
        if (current != null) {
            System.out.println(current);
        }
        
        boolean running = true;
        
        while (running) {
            printMenu(current);
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1: // Negate
                    current = handleNegate(current);
                    break;
                case 2: // Add
                    current = handleAdd(scanner, current);
                    break;
                case 3: // Subtract
                    current = handleSubtract(scanner, current);
                    break;
                case 4: // Multiply
                    current = handleMultiply(scanner, current);
                    break;
                case 5: // Cross Product or Determinant
                    current = handleCrossOrDet(scanner, current);
                    break;
                case 6: // Compare
                    handleCompare(scanner, current);
                    break;
                case 7: // Exit
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        
        scanner.close();
    }
    
    private static void printMenu(Algebraic current) {
        System.out.println("\nSelect an operation:");
        System.out.println("1: Negate");
        System.out.println("2: Add");
        System.out.println("3: Subtract");
        System.out.println("4: Multiply");
        
        if (current instanceof Vector) {
            System.out.println("5: Cross Product");
        } else {
            System.out.println("5: Determinant");
        }
        
        System.out.println("6: Compare");
        System.out.println("7: Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static Algebraic readAlgebraic(Scanner scanner) {
        System.out.print("Enter number of rows and columns (n x m): ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        
        if (n == 1) {
            // Vector
            System.out.print("Enter vector elements separated by spaces: ");
            float[] vec = new float[m];
            for (int i = 0; i < m; i++) {
                vec[i] = scanner.nextFloat();
            }
            return new Vector(vec);
        } else {
            // Matrix
            System.out.println("Enter matrix elements separated by spaces:");
            float[][] mat = new float[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    mat[i][j] = scanner.nextFloat();
                }
            }
            
            // Check if it's a lower triangular matrix
            if (n == m) {
                boolean isLT = true;
                final float TOLERANCE = 1e-6f;
                for (int i = 0; i < n && isLT; i++) {
                    for (int j = i + 1; j < m; j++) {
                        if (Math.abs(mat[i][j]) > TOLERANCE) {
                            isLT = false;
                            break;
                        }
                    }
                }
                
                if (isLT) {
                    return new LTMatrix(mat);
                }
            }
            
            return new Matrix(mat);
        }
    }
    
    private static Algebraic handleNegate(Algebraic current) {
        Algebraic result = current.negate();
        System.out.printf(" %s\n", current);
        System.out.printf("- %s = %s\n", current, result);
        return result;
    }
    
    private static Algebraic handleAdd(Scanner scanner, Algebraic current) {
        System.out.println("Enter the second vector or matrix:");
        Algebraic other = readAlgebraic(scanner);
        
        Algebraic result = current.add(other);
        
        if (result == null) {
            System.out.println("Invalid operation");
            return current;
        }
        
        System.out.printf("%s + %s = %s\n", current, other, result);
        return result;
    }
    
    private static Algebraic handleSubtract(Scanner scanner, Algebraic current) {
        System.out.println("Enter the second vector or matrix:");
        Algebraic other = readAlgebraic(scanner);
        
        Algebraic result = current.subtract(other);
        
        if (result == null) {
            System.out.println("Invalid operation");
            return current;
        }
        
        System.out.printf("%s - %s = %s\n", current, other, result);
        return result;
    }
    
    private static Algebraic handleMultiply(Scanner scanner, Algebraic current) {
        System.out.println("Enter the second vector or matrix:");
        Algebraic other = readAlgebraic(scanner);
        
        Algebraic result = current.multiply(other);
        
        if (result == null) {
            System.out.println("Invalid operation");
            return current;
        }
        
        System.out.printf("%s * %s = %s\n", current, other, result);
        return result;
    }
    
    private static Algebraic handleCrossOrDet(Scanner scanner, Algebraic current) {
        if (current instanceof Vector) {
            // Cross product
            System.out.println("Enter the second vector");
            Algebraic other = readAlgebraic(scanner);
            
            if (!(other instanceof Vector)) {
                System.out.println("Invalid operation");
                return current;
            }
            
            Vector result = ((Vector) current).crossProduct((Vector) other);
            
            if (result == null) {
                System.out.println("Invalid operation");
                return current;
            }
            
            System.out.printf("%s x %s = %s\n", current, other, result);
            return result;
        } else if (current instanceof Matrix) {
            // Determinant
            Vector det = ((Matrix) current).determinant();
            
            if (det == null) {
                System.out.println("Invalid operation");
                return current;
            }
            
            System.out.println(det);
            return det;
        }
        
        return current;
    }
    
    private static void handleCompare(Scanner scanner, Algebraic current) {
        System.out.println("Enter the second vector or matrix:");
        Algebraic other = readAlgebraic(scanner);
        
        boolean equal = current.equals(other);
        
        System.out.printf("%s == %s ==> %s\n", current, other, equal);
    }
}