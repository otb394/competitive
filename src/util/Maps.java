package util;

import java.util.Map;

public class Maps {
    /**
     * Adds the key to a counter map, which maintains the count of keys
     *
     * @param map Counter map
     * @param key key to be added
     * @param <K> Type of key
     */
    public <K> void addCounter(Map<K, Integer> map, K key) {
        int val = map.getOrDefault(key, 0);
        map.put(key, val + 1);
    }

    /**
     * Removes the key from a counter map, which maintains the count of keys
     *
     * @param map Counter map
     * @param key Key to be removed
     * @param <K> Type of the key
     * @return The new count of the key in the map. If the key was not initially present, returns -1
     */
    public <K> int removeCounter(Map<K, Integer> map, K key) {
        int val = map.getOrDefault(key, 0);
        if (val == 1) {
            map.remove(key);
        } else if (val > 1) {
            map.put(key, val - 1);
        }
        return val - 1;
    }
}
