import java.util.List;

public class TestTrack {

    public static void race(RemoteControlCar car) {
        car.drive();
    }

    public static List<ProductionRemoteControlCar> getRankedCars(List<ProductionRemoteControlCar> cars) {
        int n = cars.size(); 

        for (int i = 0; i < n - 1; i++) { 
            for (int j = 0; j < n - i - 1; j++) { 
                if (cars.get(j).getNumberOfVictories() < cars.get(j + 1).getNumberOfVictories()) {
                    ProductionRemoteControlCar temp = cars.get(j);
                    cars.set(j, cars.get(j + 1));
                    cars.set(j + 1, temp);
                }
            }
        }

        return cars; // Sıralı listeyi döndür
    }
}

