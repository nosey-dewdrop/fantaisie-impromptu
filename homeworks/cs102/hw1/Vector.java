public class Vector implements Algebraic {

      private float[] elements;

      /** cloning the array as declared in assignment. we make a deep copy so the modifications won't affect the originak.
       * if we made shallow copy, meaning the adresses would be the same.
       * @param vec
       */
      public Vector(float[] vec) {
            this.elements = new float[vec.length];
            for (int i = 0; i < vec.length; i++) {
                  this.elements[i] = vec[i];
            }
      }

      /**
       * getters for vector class.
       * @return
       */
      public int getLength() {
            return elements.length;
      }

      public float getElement(int index) {
            return elements[index];
      }


      @Override
      public Algebraic negate() {
            float[] result = new float[elements.length];

            for (int i = 0; i < elements.length; i++) {
                  result[i] = -elements[i];
            }
            // vector constructor which makes a deep copy of "result" array.
            return new Vector(result);
      }

      @Override
      public Algebraic add(Algebraic other) {

            if (!(other instanceof Vector)) {
                  return null;
            }
            
            Vector otherVec = (Vector) other;
            /* other is an algebraic. vector class implements algebraic interface. 
            so, we can cast other to vector and use the vector specific methods
            */

            if (this.elements.length != otherVec.elements.length) {
                  return null;
            }
            
            // result is in float type. i am getting elements and making operations on them.
            float[] result = new float[elements.length];

            for (int i = 0; i < elements.length; i++) {
                  result[i] = this.elements[i] + otherVec.elements[i];
            }

            return new Vector(result);
      }

      @Override
      public Algebraic subtract(Algebraic other) {

            if (!(other instanceof Vector)) {
                  return null;
            }
            
            Vector otherVec = (Vector) other;
            
            if (this.elements.length != otherVec.elements.length) {
                  return null;
            }
            
            float[] result = new float[elements.length];
            for (int i = 0; i < elements.length; i++) {
                  result[i] = this.elements[i] - otherVec.elements[i];
            }
            return new Vector(result);
      }


      @Override
      public Algebraic multiply(Algebraic other) {
            if (!(other instanceof Vector)) {
                  return null;
            }
            
            Vector otherVec = (Vector) other;
            
            if (this.elements.length != otherVec.elements.length) {
                  return null;
            }
            
            float dotProduct = 0;
            for (int i = 0; i < elements.length; i++) {
                  dotProduct += this.elements[i] * otherVec.elements[i];
            }
            return new Vector(new float[]{dotProduct});
      }

      /**
       * cross product method for 3D vectors.
       * formula: (a2*b3 - a3*b2, a3*b1 - a1*b3, a1*b2 - a2*b1)
       * @param other
       * @return
       */
      public Vector crossProduct(Vector other) {

            if (this.elements.length != 3 || other.elements.length != 3) {
                  return null;
            } 
            
            float[] result = new float[3];

            result[0] = this.elements[1] * other.elements[2] - this.elements[2] * other.elements[1];
            result[1] = this.elements[2] * other.elements[0] - this.elements[0] * other.elements[2];
            result[2] = this.elements[0] * other.elements[1] - this.elements[1] * other.elements[0];
            
            return new Vector(result);
      }

      @Override
      public boolean equals(Object other) {
            if (other == null) {
                  return false;
            }
            
            if (!(other instanceof Vector)) {
                  return false;
            }
            
            Vector otherVec = (Vector) other;
            
            if (this.elements.length != otherVec.elements.length) {
                  return false;
            }
            
            final float TOLERANCE = 1e-6f;
            for (int i = 0; i < elements.length; i++) {
                  if (Math.abs(this.elements[i] - otherVec.elements[i]) > TOLERANCE) {
                        return false;
                  }
            }
            
            return true;
      }

      @Override
      public String toString() {
            StringBuilder sb = new StringBuilder(); 
            for (int i = 0; i < elements.length; i++) {
                  sb.append(String.format("|%.2f|", elements[i]));
                  if (i < elements.length - 1) {
                        sb.append("\n");
                  }
            }
            return sb.toString();
      }
}

//!NOTES
/*
!!!!learn how to use string builder.
! learn override vs overload vs overwrite
*/

/*
- stringBuilder:
- instanceOf
- deep copy vs shallow copy 
- override: subclass changes the implementation of a method declared in superclass.
- overload: same method name with different parameters in the same class.
- overwrite: replacing the existing code with new code.

*/