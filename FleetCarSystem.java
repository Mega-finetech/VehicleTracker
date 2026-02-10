import java.util.HashMap;
import java.util.Map;

/**
 * Thread Safety annotation to indicate thread-safe classes
 */
@interface ThreadSafe {}

/**
 * Fleet Vehicle Tracking System Implementation
 * 
 * This implementation demonstrates the Java monitor pattern for tracking
 * fleet vehicles (taxicabs, police cars, delivery trucks) in a thread-safe manner.
 * 
 * Key Components:
 * - MutablePoint: Represents vehicle coordinates (x, y)
 * - MonitorVehicleTracker: Thread-safe implementation using synchronized methods
 * - DelegatingVehicleTracker: Alternative using ConcurrentHashMap
 * - FleetTrackingDemo: Example with view and updater threads
 */
public class FleetCarSystem {
    
    public static void main(String[] args) {
        System.out.println("Fleet Car Tracking System");
        System.out.println("==========================");
        System.out.println("Choose implementation:");
        System.out.println("1. Monitor Pattern (synchronized)");
        System.out.println("2. Delegating (ConcurrentHashMap)");
        
        // Default to demonstration
        FleetTrackingDemo.main(args);
    }
}