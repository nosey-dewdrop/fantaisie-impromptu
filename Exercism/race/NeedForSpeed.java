package Exercism.race;
class NeedForSpeed {

    int speed;
    int battery = 100;
    int batteryDrain;
    int distance = 0;

    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
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
    
    
    public boolean batteryDrained() {
        if(battery > batteryDrain){
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
}

