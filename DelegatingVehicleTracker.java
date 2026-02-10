import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class DelegatingVehicleTracker {
    private final ConcurrentHashMap<String, MutablePoint> locations;
    private final Map<String, MutablePoint> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, MutablePoint> points) {
        this.locations = new ConcurrentHashMap<>(points);
        this.unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, MutablePoint> getLocations() {
        return unmodifiableMap;
    }

    public MutablePoint getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new MutablePoint(x, y)) == null) {
            throw new IllegalArgumentException("No such vehicle: " + id);
        }
    }

    // Alternative version that returns an immutable snapshot
    public Map<String, MutablePoint> getLocationsAsImmutable() {
        Map<String, MutablePoint> result = new ConcurrentHashMap<>();
        for (Map.Entry<String, MutablePoint> entry : locations.entrySet()) {
            result.put(entry.getKey(), new MutablePoint(entry.getValue()));
        }
        return Collections.unmodifiableMap(result);
    }
}