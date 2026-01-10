import java.util.List;
import java.util.Set;
import java.util.HashSet;

class GottaSnatchEmAll {

    static Set<String> newCollection(List<String> cards) {
        return new HashSet<>(cards); 
    }

    static boolean addCard(String card, Set<String> collection) {
        return collection.add(card);
    }

    static boolean canTrade(Set<String> myCollection, Set<String> theirCollection) {
        Set<String> onlyMine = new HashSet<>(myCollection);
        onlyMine.removeAll(theirCollection);
    
        Set<String> onlyTheirs = new HashSet<>(theirCollection);
        onlyTheirs.removeAll(myCollection);
    
        return !onlyMine.isEmpty() && !onlyTheirs.isEmpty();
    }

    static Set<String> commonCards(List<Set<String>> collections) {
    if (collections.isEmpty()) return Set.of(); 
        
    Set<String> common = new HashSet<>(collections.get(0)); 
    for (int i = 1; i < collections.size(); i++) {
        common.retainAll(collections.get(i)); 
    }
    return common;
}

    static Set<String> allCards(List<Set<String>> collections) {
        Set<String> complete = new HashSet<>(collections.get(0)); 
        for (int i = 1; i < collections.size(); i++) {
            complete.addAll(collections.get(i)); 
        }
        return complete;
        
    }
}
