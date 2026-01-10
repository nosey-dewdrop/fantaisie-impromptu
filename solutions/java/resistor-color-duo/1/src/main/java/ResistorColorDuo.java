class ResistorColorDuo {
    int value(String[] colors) {
        String bandColor = "";
        for(int i = 0; i < 2; i++){
            String color = colors[i];
            
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
        }
        int band = Integer.parseInt(bandColor);
        return band;
    }
}
