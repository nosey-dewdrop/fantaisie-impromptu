class ArmstrongNumbers {

    boolean isArmstrongNumber(int number) {
        int count = String.valueOf(number).length();
        
        int sum = 0;
        int num = number;
        for(int i = 0; i < count; i++){
            sum += Math.pow(num%10, count);
            num = num/10;
        }
        
        if(sum == number){
            return true;
        }
        else{
            return false;
        }
    }
}
