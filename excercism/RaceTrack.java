package Exercism.race;

class RaceTrack {
    int distance;
    RaceTrack(int distance) {
        this.distance = distance;
    }

    int getDistance(){
        return distance;
    }
    
    public boolean canFinishRace(NeedForSpeed car) {
        int maximumJoy = (car.getBattery()/car.getBatteryDrain())*car.getSpeed();
        if(maximumJoy>= getDistance()){
            return true;
        }
        else{
            return false;
        }
    }
}