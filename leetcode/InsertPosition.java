package leetcode;
class InsertPosition {

    public int searchInsert(int[] nums, int target) {
        
        int length = nums.length;
        int place = 0;

        for(int i = 0; i < length; i++){
            if(nums[i] == target){
                place = i;
                return i;
            }
        }
        
        for(int j = 0; j < length; j++){
            if(nums[j] >  target){ // mesela 3 girdim ve index 1'de de 5 var. demek ki artık index 1 yerine (J) 3 olacak ve diğerleri kayacak.
                place = j;
                return j;
            }
            if(j == length-1){
                place = j+1;
                return j+1;
            }
        }

        return place;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 7;

        InsertPosition insertPosition = new InsertPosition();
        int place = insertPosition.searchInsert(nums, target);
        System.out.println(place);

    }   

}