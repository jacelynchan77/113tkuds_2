
import java.util.*;

public class MultiLevelCacheSystem {

    static class CacheItem {

        int key;
        String value;
        int freq;
        long timestamp; // last accessed time
        int level; // current cache level

        CacheItem(int key, String value, int level) {
            this.key = key;
            this.value = value;
            this.freq = 1;
            this.timestamp = System.nanoTime();
            this.level = level;
        }

        void accessed() {
            freq++;
            timestamp = System.nanoTime();
        }
    }

    static class CacheLevel {

        int capacity;
        int cost;
        PriorityQueue<CacheItem> heap;
        Map<Integer, CacheItem> map;

        CacheLevel(int capacity, int cost) {
            this.capacity = capacity;
            this.cost = cost;
            this.map = new HashMap<>();
            // Min-heap based on score (lower score evicted first)
            this.heap = new PriorityQueue<>(Comparator.comparingDouble(MultiLevelCacheSystem::score));
        }

        boolean containsKey(int key) {
            return map.containsKey(key);
        }

        CacheItem get(int key) {
            return map.get(key);
        }

        void put(CacheItem item) {
            map.put(item.key, item);
            heap.offer(item);
        }

        void remove(CacheItem item) {
            map.remove(item.key);
            heap.remove(item);
        }

        boolean isFull() {
            return map.size() >= capacity;
        }

        CacheItem evict() {
            CacheItem evicted = heap.poll();
            if (evicted != null) {
                map.remove(evicted.key);
            }
            return evicted;
        }

        void update(CacheItem item) {
            // Need to remove and re-insert to update heap order
            heap.remove(item);
            heap.offer(item);
        }
    }

    int L1_CAPACITY = 2, L2_CAPACITY = 5, L3_CAPACITY = 10;
    int L1_COST = 1, L2_COST = 3, L3_COST = 10;

    CacheLevel L1 = new CacheLevel(L1_CAPACITY, L1_COST);
    CacheLevel L2 = new CacheLevel(L2_CAPACITY, L2_COST);
    CacheLevel L3 = new CacheLevel(L3_CAPACITY, L3_COST);

    Map<Integer, CacheItem> allItems = new HashMap<>();

    // Score combines frequency, timestamp, and level cost.
    // Higher freq and recent timestamp → higher score; lower cost → higher score.
    static double score(CacheItem item) {
        // Example: weight freq=2, timestamp=1e-9, cost = inverse
        // Normalize cost to [0,1]: costScore = 1 / cost
        double costScore = 1.0 / (item.level == 1 ? 1 : (item.level == 2 ? 3 : 10));
        double timeScore = 1e-9 * item.timestamp;
        double freqScore = item.freq * 2.0;
        return freqScore + timeScore + costScore;
    }

    public String get(int key) {
        CacheItem item = allItems.get(key);
        if (item == null) {
            return null;
        }

        item.accessed();

        // Update heap position in its current level
        getLevel(item.level).update(item);

        // Try promote if score better than lower levels
        promote(item);

        return item.value;
    }

    public void put(int key, String value) {
        CacheItem item = allItems.get(key);
        if (item != null) {
            // Update existing item
            item.value = value;
            item.accessed();
            getLevel(item.level).update(item);
            promote(item);
        } else {
            // New item inserted at L3 by default
            item = new CacheItem(key, value, 3);
            allItems.put(key, item);
            addToLevel(item, 3);
            promote(item);
        }
    }

    private CacheLevel getLevel(int level) {
        if (level == 1) {
            return L1;
        }
        if (level == 2) {
            return L2;
        }
        return L3;
    }

    private void addToLevel(CacheItem item, int level) {
        CacheLevel levelCache = getLevel(level);
        if (levelCache.isFull()) {
            CacheItem evicted = levelCache.evict();
            if (evicted != null) {
                allItems.remove(evicted.key);
            }
        }
        item.level = level;
        levelCache.put(item);
    }

    private void removeFromLevel(CacheItem item) {
        CacheLevel levelCache = getLevel(item.level);
        levelCache.remove(item);
    }

    private void promote(CacheItem item) {
        // Try promote upward if possible and beneficial
        while (item.level > 1) {
            int higherLevel = item.level - 1;
            CacheLevel higherCache = getLevel(higherLevel);

            // If higher level full, check if can evict lower score item
            if (higherCache.isFull()) {
                CacheItem lowest = higherCache.heap.peek();
                if (lowest != null && score(item) > score(lowest)) {
                    // Evict from higher level
                    higherCache.evict();
                    allItems.remove(lowest.key);

                    // Move current item up
                    removeFromLevel(item);
                    addToLevel(item, higherLevel);
                } else {
                    // Can't promote
                    break;
                }
            } else {
                // Space available, promote
                removeFromLevel(item);
                addToLevel(item, higherLevel);
            }
        }
    }

    public void debugPrint() {
        System.out.println("L1: " + getKeys(L1));
        System.out.println("L2: " + getKeys(L2));
        System.out.println("L3: " + getKeys(L3));
    }

    private List<Integer> getKeys(CacheLevel level) {
        List<Integer> keys = new ArrayList<>(level.map.keySet());
        keys.sort(Comparator.naturalOrder());
        return keys;
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        // Expected roughly: L1: [2,3], L2: [1], L3: []
        cache.debugPrint();

        cache.get(1);
        cache.get(1);
        cache.get(2);
        // 1 frequently accessed → move up
        // Expected roughly: L1: [1,2], L2: [3], L3: []
        cache.debugPrint();

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
        // Items re-distributed based on freq and cost
        cache.debugPrint();
    }
}
