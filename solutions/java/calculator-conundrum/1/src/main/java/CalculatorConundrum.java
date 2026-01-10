public class CalculatorConundrum {
    public static String calculate(int a, int b, String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }

        if (operation.isEmpty()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        try {
            switch (operation) {
                case "+":
                    return a + " + " + b + " = " + (a + b);
                case "*":
                    return a + " * " + b + " = " + (a * b);
                case "/":
                    if (b == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    return a + " / " + b + " = " + (a / b);
                default:
                    throw new IllegalOperationException("Operation '" + operation + "' does not exist");
            }
        } catch (ArithmeticException e) {
            throw new IllegalOperationException("Division by zero is not allowed", e);
        }
    }
}
