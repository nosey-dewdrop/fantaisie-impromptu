public class LTMatrix extends Matrix {
      
      public LTMatrix(float[][] mat){
            super(new float[0][0]);
            if(mat == null || mat.length == 0 ){
                  LTBuilder();
                  return;
            }

            if(mat.length != mat[0].length){
                  System.out.println("Error: Matrix must be square to be lower triangular");
                  LTBuilder();
                  return;
            }

            if(isLowerTriangular(mat)){
                  this.rows = mat.length;
                  this.cols = mat.length;
                  this.elements = new float[rows][cols];

                  for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                              this.elements[i][j] = mat[i][j];
                        }
                  }
                  System.out.println("Constructed the LTMatrix"); 
            }

            else {
                  System.out.println("Error: Matrix is not lower triangular (non-zero element above diagonal)");
                  LTBuilder();
                  return;
            }


      }

      /**
       * helper method to set lt array rows, cols and it to 0x0
       */
      public void LTBuilder(){
            this.rows = 0;
            this.cols = 0;
            this.elements = new float[0][0];
      }

      /**
       * 
       * @param mat checking methods. it runs when the mat is not null and not 0x0 so we can put it as an input and work on it.
       * @return true if it is lower triangular, else false
       */
      public boolean isLowerTriangular(float[][] mat){
            for(int i = 0; i < mat.length; i++){
                  for(int j = i+1; j < mat.length; j++){
                        if(Math.abs(mat[i][j]) > 1e-6f ){
                              return false;
                        }
                  }
            }

            return true;
      }

      public boolean isUpperTriangular(float[][] mat){
            for(int i = 1; i < mat.length; i++){
                  for(int j = 0; j < i; j++){
                        if(Math.abs(mat[i][j]) > 1e-6f ){
                              return false;
                        }
                  }
            }
            return true;
      }


      /*
      it extends the upperclass matrix which implements algebraic interface.
      so that it should implement algebraic methods
      + negate
      + add
      + substract
      + multiply

      if needed, you can override matrix methods. 
      if operation happens on other LT, return is LT.
      else returns a new matrix. */

      @Override
      public Algebraic negate(){
            float[][] result = new float[rows][cols];
            for(int i = 0; i < rows; i++){
                  for(int j = 0; j <= i; j++){
                        result[i][j] = -elements[i][j];
                  }
            }
            return new LTMatrix(result);
      }

      @Override
      public Algebraic add(Algebraic other){
            if (!(other instanceof Matrix)) {
                  return null;
            }

            Matrix otherMat = (Matrix) other;
            if(this.rows != otherMat.rows || this.cols != otherMat.cols){
                  return null;
            }

            if(other instanceof LTMatrix){
                  float[][] result = new float[rows][cols];

                  for (int i = 0; i < rows; i++) {
                        for (int j = 0; j <= i; j++) {  
                              result[i][j] = this.elements[i][j] + otherMat.elements[i][j];
                        }
                  }
                  return new LTMatrix(result); 
            }

            else{
                  return super.add(other);
            }
      }

      @Override
      public Algebraic subtract(Algebraic other){
            if(!(other instanceof Matrix)){
                  return null;
            }

            Matrix otherMat = (Matrix) other;
            if(this.rows != otherMat.rows || this.cols != otherMat.cols){
                  return null;
            }

            if(other instanceof LTMatrix){
                  float[][] result = new float[rows][cols];

                  for (int i = 0; i < rows; i++) {
                        for (int j = 0; j <= i; j++) {  
                              result[i][j] = this.elements[i][j]- otherMat.elements[i][j];
                        }
                  }
                  return new LTMatrix(result); 
            }

            else{
                  return super.subtract(other);
            }
      }

      @Override
      public Algebraic multiply(Algebraic other){

            if (other instanceof LTMatrix) {
                  return multiplyLTMatrix((LTMatrix) other);
            } 

            else if (other instanceof Matrix) {
                  return super.multiply(other);
            } 

            else if (other instanceof Vector) {
                  return multiplyVector((Vector) other);
            }
            return null;
      }

      private LTMatrix multiplyLTMatrix(LTMatrix other){
            if(this.cols != other.rows){
                  return null;
            }

            float[][] result = new float[rows][cols];
            for (int i = 0; i < this.rows; i++) {
                  for (int j = 0; j <= i; j++) {
                  result[i][j] = 0;
                        for (int k = j; k <= i; k++) {  // ← BU EKSİK!
                        result[i][j] += this.elements[i][k] * other.elements[k][j];
                        }
                  }
            }

            return new LTMatrix(result);
      }

      private Vector multiplyVector(Vector vec){

            if(this.cols != vec.getLength()){
                  return null;
            }

            float[] result = new float[rows];

            for(int i = 0; i < rows; i++){
                  result[i] = 0;
                  for(int j = 0; j <= i; j++){
                        result[i] += elements[i][j] * vec.getElement(j);
                  }
            }

            return new Vector(result);
      }

      @Override
      public Vector determinant(){

            if (rows != cols || (rows == 0 || cols == 0)) {  
                  return null;
            }

            float det = 1.0f;
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

            if (other instanceof LTMatrix) {
                  LTMatrix otherLT = (LTMatrix) other;

                  if (this.rows != otherLT.rows || this.cols != otherLT.cols) {
                        return false;
                  }

                  final float TOLERANCE = 1e-6f;
                  for (int i = 0; i < rows; i++) {
                        for (int j = 0; j <= i; j++) {
                              if (Math.abs(this.elements[i][j] - otherLT.elements[i][j]) > TOLERANCE) {
                                    return false;
                              }
                        }
                  }
                  return true;
            } 

            else if (other instanceof Matrix) {
                  return super.equals(other);
            }

            return false;
      }

}
