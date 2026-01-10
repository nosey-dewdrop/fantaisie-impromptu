
class BirdWatcher {
    private int[] birdsPerDay = { 2, 5, 0, 7, 4, 1 };
    private int[] lastWeek = {0, 2, 5, 3, 7, 8 , 4 };

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
        return lastWeek;
    }

    public int getToday() {
        int today = birdsPerDay[birdsPerDay.length -1];
        return today;
    }

    public int incrementTodaysCount() {
        birdsPerDay[birdsPerDay.length -1] = birdsPerDay[birdsPerDay.length -1] + 1;
        int today = birdsPerDay[birdsPerDay.length -1];
        return today;
    }

    public boolean hasDayWithoutBirds() {
        boolean noVisitors = false;
        for(int visitor : birdsPerDay){
            if(visitor == 0){
                noVisitors = true;
                return noVisitors;
            }
        }
        return noVisitors;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int sum = 0;
        if(numberOfDays > birdsPerDay.length){
            for(int i = 0; i < birdsPerDay.length; i++){
            sum += birdsPerDay[i];
        }
        }
        else{
            for(int i = 0; i < numberOfDays; i++){
            sum += birdsPerDay[i];
        }
        }
        return sum;
    }

    public int getBusyDays() {
        int busy = 0;

        for(int birds : birdsPerDay){
            if(birds >= 5){
                busy++;
            }
        }
        return busy;
    }
}
