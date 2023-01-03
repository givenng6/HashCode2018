public class Vehicle {

    private Intersection intersection;
    private Intersection nextIntersection;
    private boolean busy, pickup;
    private int vehicleID;
    private Ride currentRide;

    public Vehicle(int vehicleID, String city){
        intersection = new Intersection(0, 0);
        nextIntersection = null;
        this.vehicleID = vehicleID;
        currentRide = null;
        busy = false;
        pickup = false;
    }

    public int getVehicleID(){
        return vehicleID;
    }

    public boolean isBusy() {
        return busy;
    }

    public boolean isPickup() {
        return pickup;
    }

    public void setPickup(boolean pickup) {
        this.pickup = pickup;
    }

    public void assignRide(Ride currentRide){
        busy = true;
        pickup = true;
        this.currentRide = currentRide;
    }

    public Ride getCurrentRide(){
        return currentRide;
    }

    public void tripCompleted(){
        currentRide = null;
        busy = false;
    }

    public Intersection getIntersection(){
        return intersection;
    }

    public void setNextIntersection(Intersection nextIntersection) {
        this.nextIntersection = nextIntersection;
    }

    public Intersection getNextIntersection() {
        return nextIntersection;
    }
}
