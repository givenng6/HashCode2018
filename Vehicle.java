public class Vehicle {

    private Intersection intersection;
    private Intersection nextIntersection;
    private boolean busy, pickup;
    private int vehicleID;
    private Ride currentRide;
    private City city;

    public Vehicle(int vehicleID, City city){
        intersection = new Intersection(0, 0);
        nextIntersection = null;
        this.vehicleID = vehicleID;
        currentRide = null;
        busy = false;
        pickup = false;
        this.city = city;
    }

    public int getVehicleID(){

        return vehicleID;
    }

    public void changeCity(City city){
        this.city = city;
    }

    public City getCity(){
        return city;
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
        this.currentRide = currentRide;
        this.nextIntersection = currentRide.getStartIntersection();
    }

    public Ride getCurrentRide(){
        return currentRide;
    }

    public void tripCompleted(){
        currentRide = null;
        busy = false;
    }

    public void setNextIntersection(Intersection nextIntersection) {
        this.nextIntersection = nextIntersection;
    }

    public Intersection getIntersection(){
        return intersection;
    }

    public Intersection getNextIntersection() {
        return nextIntersection;
    }
}
