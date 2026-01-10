package Exercism.race;

public class CarRace {
    public static void main(String[] args) {
        NeedForSpeed car = new NeedForSpeed(5, 10);
        RaceTrack raceTrack = new RaceTrack(40);

        System.out.println(car.getBattery());
        //System.out.println(car.getBatteryDrain());
        System.out.println(car.getBattery());

        System.out.println("Second turn!!!");
        car.drive();
        System.out.println(car.getBattery());
        //System.out.println(car.getBatteryDrain());
        System.out.println(car.getBattery());

        boolean canFinish = raceTrack.canFinishRace(car);
        System.out.println(canFinish);

    }
    
}
