class NeedForSpeed {
    int speed;
    int battery = 100;
    int batteryDrain;
    
    int distance = 0;

    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
    }

    public boolean batteryDrained() {
        if(battery >= batteryDrain){
            return false;
        }
        else{
            return true;
        }
    }

    public int distanceDriven() {
        return distance;
    }

    public void drive() {
        if(!batteryDrained()){
            distance += speed;
            battery -= batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50,4);
    }
    
    public int getSpeed(){
        return speed;
    }
    public int getBattery(){
        return battery;
    }
    public int getBatteryDrain(){
        return batteryDrain;
    }
    
}

class RaceTrack {
    int distance;
    RaceTrack(int distance) {
        this.distance = distance;
    }
    
    public boolean canFinishRace(NeedForSpeed car) {
        int maximumJoy = (car.getBattery()/car.getBatteryDrain())*car.getSpeed();

        if(maximumJoy>= distance){
            return true;
        }
        else{
            return false;
        }
    }
}
