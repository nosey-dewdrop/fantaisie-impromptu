package sortings;
import java.util.Arrays;

public class SelectionSort {

      public static int[] selectionSort(int[] dataset){

            for(int i = 0; i < dataset.length; i++){

                  int target = dataset[i];
                  int targetIndex = i;

                  for(int j= i; j < dataset.length; j++ ){
                        if(dataset[j] < target){
                              target = dataset[j];
                              targetIndex = j;
                        }
                  }

                  dataset[targetIndex] = dataset[i];
                  dataset[i] = target;

            }
      
            return dataset;
      }
      public static void main (String[] args){


            int [] example = {47, 15, 88, 23, 9, 62, 34, 51, 12, 76};
            System.out.println(Arrays.toString(example));
            String printArray = Arrays.toString(selectionSort(example));
            System.out.println(printArray);

      }
}
