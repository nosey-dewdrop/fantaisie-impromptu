public class ElonsToyCar {
    int battery = 100;
    int distance;
    
    public static ElonsToyCar buy() {
        ElonsToyCar car = new ElonsToyCar();
        return car;
    }

    public String distanceDisplay() {
        return "Driven " + distance + " meters";
     }

    public String batteryDisplay() {
        if(battery == 0){
            return "Battery empty";
        }
        return "Battery at " + battery +"%";
    }

    public void drive() {
        if(battery >= 1){
            distance += 20;
            battery -= 1;
        }
        else{
            System.out.println("Battery empty");
        }
    }
}
