import java.util.ArrayList;

public class Bookings {

    private ArrayList<Ride> cityA, cityB, cityC, cityD;
    public Bookings(){
        cityA = new ArrayList<>();
        cityB = new ArrayList<>();
        cityC = new ArrayList<>();
        cityD = new ArrayList<>();
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

    public void sizes(){
        System.out.println(cityA.size());
        System.out.println(cityB.size());
        System.out.println(cityC.size());
        System.out.println(cityD.size());
    }
}