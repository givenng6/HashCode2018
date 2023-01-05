import java.util.ArrayList;

public class Bookings {

    private ArrayList<Ride> cityA, cityB, cityC, cityD;
    private ArrayList<Vehicle> fleetA, fleetB, fleetC, fleetD;
    public Bookings(){
        cityA = new ArrayList<>();
        cityB = new ArrayList<>();
        cityC = new ArrayList<>();
        cityD = new ArrayList<>();

        fleetA = new ArrayList<>();
        fleetB = new ArrayList<>();
        fleetC = new ArrayList<>();
        fleetD = new ArrayList<>();
    }

    public ArrayList<Ride> getCityA() {
        return cityA;
    }

    public ArrayList<Ride> getCityB() {
        return cityB;
    }

    public ArrayList<Ride> getCityC() {
        return cityC;
    }

    public ArrayList<Ride> getCityD() {
        return cityD;
    }

    public ArrayList<Vehicle> getFleetA() {
        return fleetA;
    }

    public ArrayList<Vehicle> getFleetB() {
        return fleetB;
    }

    public ArrayList<Vehicle> getFleetC() {
        return fleetC;
    }

    public ArrayList<Vehicle> getFleetD() {
        return fleetD;
    }

    public void addRide(Ride ride, City city){
        // TODO sort the rides, earliest first
        switch (city){
            case CITY_A -> cityA.add(ride);
            case CITY_B -> cityB.add(ride);
            case CITY_C -> cityC.add(ride);
            case CITY_D -> cityD.add(ride);
        }
    }

    public void addVehicle(Vehicle vehicle, City city){
        switch (city){
            case CITY_A -> fleetA.add(vehicle);
            case CITY_B -> fleetB.add(vehicle);
            case CITY_C -> fleetC.add(vehicle);
            case CITY_D -> fleetD.add(vehicle);
        }
    }

    public int[] getDistribution(){

        return new int[] {cityA.size(), cityB.size(), cityC.size(), cityD.size()};
    }
}