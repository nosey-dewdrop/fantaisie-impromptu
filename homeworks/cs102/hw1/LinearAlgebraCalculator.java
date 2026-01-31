import java.util.Scanner;

public class LinearAlgebraCalculator {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter a vector or matrix!!! ");
        Algebraic current = readAlgebraicObject();

        if(current != null){
            System.out.println(current);
        }

        boolean proceed = true;
        while (proceed && current != null) {
            displayMenu(current);
            int choice = getChoice();

            if (choice == 1) {
                Algebraic result = current.negate();
                System.out.println(result);
                current = result;
            } 

            else if (choice == 2) {

                System.out.println("Enter the second vector or matrix:");
                Algebraic other = readAlgebraicObject();

                if (other != null) {
                    Algebraic result = current.add(other);

                    if (result != null) {
                        System.out.println(result);
                        current = result;
                    } 

                    else {
                        System.out.println("Invalid operation");
                    }
                }
            } 

            else if (choice == 3) {

                System.out.println("Enter the second vector or matrix:");
                Algebraic other = readAlgebraicObject();
                
                if (other != null) {
                    Algebraic result = current.subtract(other);

                    if (result != null) {
                        System.out.println(result);
                        current = result;
                    } 

                    else {
                        System.out.println("Invalid operation");
                    }
                }

            } 
            
            else if (choice == 4) {

                System.out.println("Enter the second vector or matrix:");
                Algebraic other = readAlgebraicObject();
                
                if (other != null) {
                    Algebraic result = current.multiply(other);
                    
                    if (result != null) {
                        System.out.println(result);
                        current = result;
                    } 
                    
                    else {
                        System.out.println("Invalid operation");
                    }
                }
            } 
            
            else if (choice == 5) {

                if (current instanceof Vector) {
                    System.out.println("Enter the second vector");
                    Algebraic other = readAlgebraicObject();

                    if (other instanceof Vector) {

                        Vector result = ((Vector) current).crossProduct((Vector) other);
                        
                        if (result != null) {
                            System.out.println(result);
                            current = result;
                        } 

                        else {
                            System.out.println("Invalid operation");
                        }
                    }
                } 

                else if (current instanceof Matrix) {
                    Vector result = ((Matrix) current).determinant();

                    if (result != null) {
                    System.out.println(result);
                    current = result;
                    } 
                    
                    else {
                    System.out.println("Invalid operation");
                    }
                }
            } 

            else if (choice == 6) {

                System.out.println("Enter the second vector or matrix:");
                Algebraic other = readAlgebraicObject();
               
                if (other != null) {
                    boolean equal = current.equals(other);
                    System.out.println(current + " == " + other + " ==> " + equal);
                }

            } 
            
            else if (choice == 7) {
                System.out.println("Exiting...");
                proceed = false;
            } 

            else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close(); // why here, not in each method? or the class?
    }

    private static Algebraic readAlgebraicObject() {
        System.out.print("Enter number of rows and columns (n x m): ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        if (rows == 1) {
            System.out.print("Enter vector elements separated by spaces: ");
            float[] elements = new float[cols];
            for(int i = 0; i < cols; i++){
                elements[i] = scanner.nextFloat();
            }
            return new Vector(elements);
        } 

        else {
            System.out.println("Enter matrix elements separated by spaces:");
            float[][] elements = new float[rows][cols];

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    elements[i][j] = scanner.nextFloat();
                }
            }

            if(rows == cols){
                boolean isLT = true;
                for (int i = 0; i < rows; i++) {
                    for (int j = i + 1; j < cols; j++) {
                        if (Math.abs(elements[i][j]) > 1e-6f) {
                            isLT = false;
                        }
                    }
                }

                if (isLT) {
                    return new LTMatrix(elements);
                }
            }
            return new Matrix(elements);
        }

    }

    private static void displayMenu(Algebraic current) {
        System.out.println("\nSelect an operation:");
        System.out.println("1: Negate");
        System.out.println("2: Add");
        System.out.println("3: Subtract");
        System.out.println("4: Multiply");

        if (current instanceof Vector) {
            System.out.println("5: Cross Product");
        } 
        else {
            System.out.println("5: Determinant");
        }

        System.out.println("6: Compare");
        System.out.println("7: Exit");
    }

    private static int getChoice() {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Invalid input. Enter your choice: ");
        }
        return scanner.nextInt();
    }
}

    // - readAlgebraicObject() OK
    // - displayMenu() OK   
    // - getChoice() OK
    // - performNegate()        
    // - performAdd()           
    // - performSubtract()    
    // - performMultiply()      
    // - performCrossOrDet()    
    // - performCompare()       
