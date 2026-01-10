class Fighter {
    
    boolean isVulnerable() {
        return true;
    }

    int getDamagePoints(Fighter fighter) {
        return 1;
    }
}

class Warrior extends Fighter{
    @Override
    boolean isVulnerable(){
        return false;
    }
    @Override int getDamagePoints(Fighter fighter){
        if(!fighter.isVulnerable()){
            return 6;
        }
        return 10;
    }
    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }
}

class Wizard extends Fighter{
    boolean spellPrepared;
    
    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }
    public boolean prepareSpell(){
        spellPrepared = true;
        return spellPrepared;
    }
    
    @Override
    boolean isVulnerable(){
        return !spellPrepared;
    }
    
    @Override int getDamagePoints(Fighter fighter){
        if(spellPrepared){
            return 12;
        }
        return 3;
    }
}
