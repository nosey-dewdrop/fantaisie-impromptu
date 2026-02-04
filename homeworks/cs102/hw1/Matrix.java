/*
so, there is an algebraic INTERFACE with four methods.
Matrix and Vector class IMPLEMENTS Algebraic interface. 

and LTMatrix EXTENDS from Matrix class. 
LTMatrix is a CHILD class of Matrix class. 
so it inherits all methods and attributes of Matrix class.
 */

import java.util.Locale;

public class Matrix implements Algebraic {

      protected float[][] elements;
      protected int rows;
      protected int cols;

      /**
       * 
       * @param mat the matrix user enters read by readAlgebraic on calculator.
       */
      public Matrix(float[][] mat) {
            if (mat == null || mat.length == 0) {
                  this.rows = 0;
                  this.cols = 0;
                  this.elements = new float[0][0];
                  return;
            }
            
            this.rows = mat.length;
            this.cols = mat[0].length;
            this.elements = new float[rows][cols];
            
            for (int i = 0; i < rows; i++) {
                  for (int j = 0; j < cols; j++) {
                        this.elements[i][j] = mat[i][j];
                  }
            }
      }


      /**
       * getters for matrix class.
       * @return
       */
      public int getRows() {
            return rows;
      }

      public int getCols() {
            return cols;
      }

      public float getElement(int row, int col) {
            return elements[row][col];
      }

      @Override
      public Algebraic negate() {
            float[][] result = new float[rows][cols];

            for (int i = 0; i < rows; i++) {
                  for (int j = 0; j < cols; j++) {
                        result[i][j] = -elements[i][j];
                  }
            }

            return new Matrix(result);
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
            // create a result float 2d array with the same size.
            float[][] result = new float[rows][cols];
            for (int i = 0; i < rows; i++) {
                  for (int j = 0; j < cols; j++) {
                        result[i][j] = this.elements[i][j] + otherMat.elements[i][j];
                  }
            }
            return new Matrix(result);
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
            for (int i = 0; i < rows; i++) {
                  for (int j = 0; j < cols; j++) {
                        result[i][j] = this.elements[i][j] - otherMat.elements[i][j];
                  }
            }
            return new Matrix(result);
      }

      /**
       * it is directive method.
       */
      @Override
      public Algebraic multiply(Algebraic other) {
            if (other instanceof Matrix) {
                  return multiplyMatrix((Matrix) other);
            } 
            else if (other instanceof Vector) {
                  return multiplyVector((Vector) other);
            }
            return null;
      }

      private Matrix multiplyMatrix(Matrix other) {

            if (this.cols != other.rows) {
                  return null;
            }
            
            float[][] result = new float[this.rows][other.cols];
            
            for (int i = 0; i < this.rows; i++) {
                  for (int j = 0; j < other.cols; j++) {
                        result[i][j] = 0; // to avoid garbage value
                        for (int k = 0; k < this.cols; k++) {
                              result[i][j] += this.elements[i][k] * other.elements[k][j];
                        }
                  }
            }
            
            return new Matrix(result);
      }

      private Vector multiplyVector(Vector vec) {
            if (this.cols != vec.getLength()) {
                  return null;
            }
            
            float[] result = new float[this.rows];
            
            for (int i = 0; i < this.rows; i++) {
                  result[i] = 0; // to avoid garbage value
                  for (int j = 0; j < this.cols; j++) {
                        result[i] += this.elements[i][j] * vec.getElement(j);
                  }
            }
            
            return new Vector(result);
      }

      /**
       * only works for 2x2 and 3x3
       * @return
       */
      public Vector determinant() {
            if (rows != cols) {
                  return null;
            }
            
            if (rows == 2) {
                  float det = elements[0][0] * elements[1][1] - elements[0][1] * elements[1][0];
                  return new Vector(new float[]{det});
            } 

            else if (rows == 3) {
                  float det = elements[0][0] * elements[1][1] * elements[2][2]
                              + elements[0][1] * elements[1][2] * elements[2][0]
                              + elements[0][2] * elements[1][0] * elements[2][1]
                              - elements[0][2] * elements[1][1] * elements[2][0]
                              - elements[0][1] * elements[1][0] * elements[2][2]
                              - elements[0][0] * elements[1][2] * elements[2][1];
                  return new Vector(new float[]{det});
            }
            return null;
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
            for (int i = 0; i < rows; i++) {
                  for (int j = 0; j < cols; j++) {
                        if (Math.abs(this.elements[i][j] - otherMat.elements[i][j]) > TOLERANCE) {
                              return false;
                        }
                  }
            }
            
            return true;
      }

      @Override
      public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rows; i++) {
                  sb.append("|");
                  for (int j = 0; j < cols; j++) {
                        sb.append(String.format(Locale.US, "%.2f", elements[i][j]));
                        if (j < cols - 1) {
                              sb.append(" ");
                        }
                  }

                  sb.append("|");
                  if (i < rows - 1) {
                        sb.append("\n");
                  }
            }
            return sb.toString();
      }
}



/*

private - only it's own class.
protected - child class and the package
public - all 
casting:
implements:
extends:
abstract classes:
abstract methods:

*/
      