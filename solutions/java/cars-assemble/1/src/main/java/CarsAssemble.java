import java.util.*;

public class CarsAssemble {

    int speed;
    final int MAX_SPEED = 10;
    final int MIN_SPEED = 0;
    final int ASSEMBLE = 221;

    public double productionRatePerHour(int speed) {
        boolean isValid = isValidSpeed(speed);
        double successRate = successRate(speed);
        if (isValid) {
            return speed * ASSEMBLE*successRate;
        }
        return -1;
    }

    public int workingItemsPerMinute(int speed) {
        boolean isValid = isValidSpeed(speed);
        double totalItems = productionRatePerHour(speed);
        if(isValid){
            int itemsPerMinute = (int)totalItems/60;
            return itemsPerMinute;
        }
        return -1;
        
    }

    public double successRate(int speed){
        double rate;
        boolean isValid = isValidSpeed(speed);
        if(isValid){
            if(speed ==10){
                rate = 0.77;
            }
            else if(speed == 9){
                rate = 0.8;
            }
            else if(speed >= 5){
                rate = 0.9;
            }
            else{
                rate = 1;
            }
            return rate;
        }
        return -1;
    }
    public boolean isValidSpeed(int speed){
        return speed >= MIN_SPEED && speed <= MAX_SPEED;
    }

    public int getSpeed() {
        return speed;
    }

    public int getCarAssemble() {
        return ASSEMBLE;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
}
