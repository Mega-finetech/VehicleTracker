public class VehicleMovedEvent {
    private final String vehicleId;
    private final MutablePoint newLocation;

    public VehicleMovedEvent(String vehicleId, MutablePoint newLocation) {
        this.vehicleId = vehicleId;
        this.newLocation = newLocation;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public MutablePoint getNewLocation() {
        return newLocation;
    }
}