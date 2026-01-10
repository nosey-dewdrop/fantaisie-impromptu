import java.util.*;

class ResistorColorTrio {

    String getColorNumber(String color){
        String bandColor = "";
        switch(color){
            case "black":
                bandColor += "0";
                break;
            case "brown":
                bandColor += "1";
                break;
            case "red":
                bandColor += "2";
                break;
            case "orange":
                bandColor += "3";
                break;
            case "yellow":
                bandColor += "4";
                break;
            case "green":
                bandColor += "5";
                break;
            case "blue":
                bandColor += "6";
                break;
            case "violet":
                bandColor += "7";
                break;
            case "grey":
                bandColor += "8";
                break;
            case "white":
                bandColor += "9";
                break;
            default: 
                throw new IllegalArgumentException("Invalid color: " + color);
        }
        return bandColor;
    }
    String label(String[] colors) {
        final int THOUSAND = 1000;
        String str = getColorNumber(colors[0]) + getColorNumber(colors[1]);

        int firstOne = Integer.parseInt(str);
        int lastOne = Integer.parseInt(getColorNumber(colors[2]));

        long label = firstOne*(long)Math.pow(10, lastOne);

        if(label/THOUSAND != 0){
            label = label/THOUSAND;
            if(label/THOUSAND != 0){
                label = label/THOUSAND;
                if(label/THOUSAND != 0){
                    label = label/ THOUSAND;
                    return label + " gigaohms";
                }
                return label + " megaohms";
            }
            return label + " kiloohms";
        }
        return label + " ohms";
    }
}
