package LeetCode.completed;
import java.util.Arrays;

class deleteFromArray {
    public static int removeElement(int[] nums, int val) {
        int [] newNums = new int[nums.length];

        int i = 0;
        for(int number: nums){
            if(number != val){
                newNums[i] = number;
                i++;
            }
        }
        System.out.println(Arrays.toString(newNums));
        return i;
    }

    

    public static void main (String args[]){
        int[] nums = {3,2,2,3};
        int delete = 3;

        int size = removeElement(nums, delete);
        System.out.print(size);

    }
}