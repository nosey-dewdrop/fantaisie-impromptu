import java.util.Arrays;

public class RemoveDuplicates {

      public static int removeDuplicates(int[] dataset) {
            if (dataset.length == 0){
                  return 0;
            }

            int i = 0; 

            for (int j = 1; j < dataset.length; j++) {
                  if (dataset[i] != dataset[j]) {
                        i++;
                        dataset[i] = dataset[j];
                  }
            }

            return i + 1;
      }
      
      public static void main(String[] args) {
            int[] nums = {1,1,2,2,3,3,4};

            int k = removeDuplicates(nums);

            System.out.println("k = " + k);

            System.out.print("unique = [");
            for (int i = 0; i < k; i++) {
                  System.out.print(nums[i]);
                  if (i < k - 1){
                        System.out.print(", ");
                  }
            }
            System.out.println("]");
      }
}



