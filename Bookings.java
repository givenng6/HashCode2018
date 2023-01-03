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

    public void addRide(Ride ride, String city){
        switch (city){
            case "A" -> cityA.add(ride);
            case "B" -> cityB.add(ride);
            case "C" -> cityC.add(ride);
            case "D" -> cityD.add(ride);
        }
    }

    public void addVehicle(Vehicle vehicle, String city){
        switch (city){
            case "A" -> fleetA.add(vehicle);
            case "B" -> fleetB.add(vehicle);
            case "C" -> fleetC.add(vehicle);
            case "D" -> fleetD.add(vehicle);
        }
    }

    public int[] getDistribution(){
        return new int[] {cityA.size(), cityB.size(), cityC.size(), cityD.size()};
    }
}