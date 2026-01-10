import java.util.*;

public class Lasagna {

    int minutes = 40;

    public int expectedMinutesInOven(){
        int minutes = 40;
        return minutes;
    }

    public int remainingMinutesInOven(int passed){
        Lasagna lasagna = new Lasagna();
        int prep = lasagna.expectedMinutesInOven();
        int minutesLeft = prep-passed;
        return minutesLeft;
    }

    public int preparationTimeInMinutes(int layers){
        int minutes = layers*2;
        return minutes;
    }

    public int totalTimeInMinutes(int layers, int minutesPassed){
        int extra = preparationTimeInMinutes(layers);
        int total = minutesPassed + extra;
        return total;
    }

    public static void main (String args[]){
        Scanner scanner = new Scanner(System.in);
        Lasagna lasagna = new Lasagna();
        System.out.println("Minutes needed: " + lasagna.expectedMinutesInOven());
        System.out.println("Enter layers and how long has it been on the oven: ");
        
        int minutes = scanner.nextInt();
        int layers = scanner.nextInt();

        int total = lasagna.totalTimeInMinutes(layers, minutes);
        System.out.println("Total minutes: " + total);

    
    }
}

