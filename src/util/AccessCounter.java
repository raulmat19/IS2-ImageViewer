package util;

import java.util.HashMap;

public class AccessCounter {
    
    private static final AccessCounter INSTANCE = new AccessCounter();
    private final HashMap<String, Integer> accesses = new HashMap<>();

    private AccessCounter() {
    }
    
    public static AccessCounter getInstance() {
        return INSTANCE;
    }
    
    public Integer increment(String key){
        final Integer count;
        if (this.accesses.containsKey(key)) {
            count = this.accesses.get(key) + 1;
        } else {
            count = 1;
        }
        
        this.accesses.put(key, count);
        return count;
    }
    

}
