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
            case CITY_A -> insertionSortRides(cityA, ride);
            case CITY_B -> insertionSortRides(cityB, ride);
            case CITY_C -> insertionSortRides(cityC, ride);
            case CITY_D -> insertionSortRides(cityD, ride);
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

    public void insertionSortRides(ArrayList<Ride> list, Ride ride){
        boolean added = false;
        for (int i = 0; i <  list.size(); i++){
            Ride currentRide = list.get(i);
            if(currentRide.getEarliestStart() > ride.getEarliestStart()){
                list.add(i, ride);
                added = true;
                break;
            }
        }

        if(!added){
            list.add(ride);
        }
    }
}