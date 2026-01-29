public class LTMatrix extends Matrix {
    
    // Constructor - validates lower triangular property
    public LTMatrix(float[][] mat) {
        super(new float[0][0]); // temporary
        
        // Validation
        if (mat == null || mat.length == 0) {
            this.rows = 0;
            this.cols = 0;
            this.elements = new float[0][0];
            return;
        }
        
        int n = mat.length;
        
        // Check if square
        if (n != mat[0].length) {
            System.out.println("Error: Matrix must be square for LTMatrix");
            this.rows = 0;
            this.cols = 0;
            this.elements = new float[0][0];
            return;
        }
        
        // Check if lower triangular (all elements above diagonal are zero)
        final float TOLERANCE = 1e-6f;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(mat[i][j]) > TOLERANCE) {
                    System.out.println("Error: Matrix is not lower triangular");
                    this.rows = 0;
                    this.cols = 0;
                    this.elements = new float[0][0];
                    return;
                }
            }
        }
        
        // Valid LTMatrix
        this.rows = n;
        this.cols = n;
        this.elements = new float[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.elements[i][j] = mat[i][j];
            }
        }
        
        System.out.println("Constructed the LTMatrix");
    }
    
    @Override
    public Algebraic negate() {
        float[][] result = new float[rows][cols];
        
        // Efficient - only process lower triangular part
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                result[i][j] = -elements[i][j];
            }
        }
        
        return new LTMatrix(result);
    }
    
    @Override
    public Algebraic add(Algebraic other) {
        if (!(other instanceof Matrix)) {
            return null;
        }
        
        Matrix otherMat = (Matrix) other;
        
        if (this.rows != otherMat.rows || this.cols != otherMat.cols) {
            return null;
        }
        
        float[][] result = new float[rows][cols];
        
        if (other instanceof LTMatrix) {
            // Efficient addition - only lower triangular
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j <= i; j++) {
                    result[i][j] = this.elements[i][j] + otherMat.elements[i][j];
                }
            }
            return new LTMatrix(result);
        } else {
            // Regular matrix addition - return Matrix
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result[i][j] = this.elements[i][j] + otherMat.elements[i][j];
                }
            }
            return new Matrix(result);
        }
    }
    
    @Override
    public Algebraic subtract(Algebraic other) {
        if (!(other instanceof Matrix)) {
            return null;
        }
        
        Matrix otherMat = (Matrix) other;
        
        if (this.rows != otherMat.rows || this.cols != otherMat.cols) {
            return null;
        }
        
        float[][] result = new float[rows][cols];
        
        if (other instanceof LTMatrix) {
            // Efficient subtraction - only lower triangular
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j <= i; j++) {
                    result[i][j] = this.elements[i][j] - otherMat.elements[i][j];
                }
            }
            return new LTMatrix(result);
        } else {
            // Regular matrix subtraction - return Matrix
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    result[i][j] = this.elements[i][j] - otherMat.elements[i][j];
                }
            }
            return new Matrix(result);
        }
    }
    
    @Override
    public Algebraic multiply(Algebraic other) {
        if (other instanceof LTMatrix) {
            return multiplyLTMatrix((LTMatrix) other);
        } else if (other instanceof Matrix) {
            return super.multiply(other); // Use parent's multiply for Matrix
        } else if (other instanceof Vector) {
            return multiplyVector((Vector) other);
        }
        return null;
    }
    
    // Efficient LTMatrix multiplication
    private LTMatrix multiplyLTMatrix(LTMatrix other) {
        if (this.cols != other.rows) {
            return null;
        }
        
        float[][] result = new float[this.rows][other.cols];
        
        // Efficient - skip zeros
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j <= i; j++) {
                result[i][j] = 0;
                // Only multiply where both matrices have non-zero elements
                int limit = Math.min(i, j);
                for (int k = 0; k <= limit; k++) {
                    result[i][j] += this.elements[i][k] * other.elements[k][j];
                }
            }
        }
        
        return new LTMatrix(result);
    }
    
    // Efficient Matrix-Vector multiplication
    private Vector multiplyVector(Vector vec) {
        if (this.cols != vec.getLength()) {
            return null;
        }
        
        float[] result = new float[this.rows];
        
        // Efficient - only process lower triangular part
        for (int i = 0; i < this.rows; i++) {
            result[i] = 0;
            for (int j = 0; j <= i; j++) {
                result[i] += this.elements[i][j] * vec.getElement(j);
            }
        }
        
        return new Vector(result);
    }
    
    @Override
    public Vector determinant() {
        // For lower triangular matrix, determinant is product of diagonal elements
        float det = 1;
        for (int i = 0; i < rows; i++) {
            det *= elements[i][i];
        }
        return new Vector(new float[]{det});
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        
        if (!(other instanceof Matrix)) {
            return false;
        }
        
        Matrix otherMat = (Matrix) other;
        
        if (this.rows != otherMat.rows || this.cols != otherMat.cols) {
            return false;
        }
        
        final float TOLERANCE = 1e-6f;
        
        if (other instanceof LTMatrix) {
            // Efficient - only compare lower triangular part
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j <= i; j++) {
                    if (Math.abs(this.elements[i][j] - otherMat.elements[i][j]) > TOLERANCE) {
                        return false;
                    }
                }
            }
        } else {
            // Compare all elements
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (Math.abs(this.elements[i][j] - otherMat.elements[i][j]) > TOLERANCE) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}