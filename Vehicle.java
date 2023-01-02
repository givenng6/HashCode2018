public class Vehicle {

    private Intersection intersection;
    private boolean busy;
    private int vehicleID;
    private Ride currentRide;

    public Vehicle(int vehicleID){
        intersection = new Intersection(0, 0);
        this.vehicleID = vehicleID;
        currentRide = null;
        busy = false;
    }

    public int getVehicleID(){
        return vehicleID;
    }

    public void assignRide(Ride currentRide){
        busy = true;
        this.currentRide = currentRide;
    }

    public void finishRide(){
        currentRide = null;
        busy = false;
    }
}
