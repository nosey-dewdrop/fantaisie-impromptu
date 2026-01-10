public class GameMaster {

    public String describe(Character character){
        String description = "You're a level " + character.getLevel() + " " +character.getCharacterClass() + " with " + character.getHitPoints() + " hit points.";
        return description;
    }

    public String describe(Destination destination){
        String description = "You've arrived at " + destination.getName()+", which has "+ destination.getInhabitants()+ " inhabitants.";
        return description;
    }

    public String describe(TravelMethod how){
        String travelMethod = "You're traveling to your destination";
        if(how == TravelMethod.HORSEBACK){
            return travelMethod + " on horseback.";
        }
        else if( how == TravelMethod.WALKING){
            return travelMethod + " by walking.";
        }
        else{
            return "Invalid";
        }
    }
    
    public String describe(Character character, Destination destination, TravelMethod how){
        String characterType = describe(character);
        String dest = describe(destination);
        String travelMethod = describe(how);

        String description = characterType + " " + travelMethod + " " + dest;
        return description;
    }

    public String describe (Character character, Destination destination){
         return describe(character, destination, TravelMethod.WALKING);
    }
}
