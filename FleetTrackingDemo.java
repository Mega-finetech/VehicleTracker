import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FleetTrackingDemo {
    
    public static void main(String[] args) {
        // Initialize vehicles with starting positions
        Map<String, MutablePoint> initialLocations = new HashMap<>();
        initialLocations.put("Taxi-001", new MutablePoint(10, 20));
        initialLocations.put("Police-101", new MutablePoint(50, 60));
        initialLocations.put("Delivery-201", new MutablePoint(30, 40));
        
        MonitorVehicleTracker tracker = new MonitorVehicleTracker(initialLocations);
        
        // Start view thread (simulates GUI display)
        Thread viewThread = new Thread(new ViewTask(tracker));
        viewThread.setDaemon(true);
        viewThread.start();
        
        // Start updater threads (simulating GPS updates)
        for (String vehicleId : initialLocations.keySet()) {
            Thread updaterThread = new Thread(new UpdaterTask(tracker, vehicleId));
            updaterThread.setDaemon(true);
            updaterThread.start();
        }
        
        // Run for demonstration
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Demonstration completed.");
    }
    
    private static void renderVehicle(String key, MutablePoint location) {
        System.out.printf("Rendering %s at location %s%n", key, location);
    }
    
    private static class ViewTask implements Runnable {
        private final MonitorVehicleTracker tracker;
        
        public ViewTask(MonitorVehicleTracker tracker) {
            this.tracker = tracker;
        }
        
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Map<String, MutablePoint> locations = tracker.getLocations();
                System.out.println("\n=== Vehicle Status Update ===");
                for (String key : locations.keySet()) {
                    renderVehicle(key, locations.get(key));
                }
                
                try {
                    Thread.sleep(2000); // Update display every 2 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    
    private static class UpdaterTask implements Runnable {
        private final MonitorVehicleTracker tracker;
        private final String vehicleId;
        private final Random random = new Random();
        
        public UpdaterTask(MonitorVehicleTracker tracker, String vehicleId) {
            this.tracker = tracker;
            this.vehicleId = vehicleId;
        }
        
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // Simulate vehicle movement
                    int newX = random.nextInt(100);
                    int newY = random.nextInt(100);
                    
                    tracker.setLocation(vehicleId, newX, newY);
                    System.out.printf("%s moved to (%d, %d)%n", vehicleId, newX, newY);
                    
                    Thread.sleep(3000 + random.nextInt(2000)); // Random update intervals
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
}