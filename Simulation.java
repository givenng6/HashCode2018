import java.util.ArrayList;

public class Simulation {

    private int totalSteps;
    private Bookings bookings;

    private ArrayList<Vehicle> vehicles;

    public Simulation(Bookings bookings, int totalSteps){
        this.bookings = bookings;
        this.totalSteps = totalSteps;
        this.vehicles = new ArrayList<>();
    }

    public void start(){
        for(int i = 0; i < totalSteps; i++){
            assignRideToVehicle(bookings.getCityA(), bookings.getFleetA(), i);
            assignRideToVehicle(bookings.getCityB(), bookings.getFleetB(), i);
            assignRideToVehicle(bookings.getCityC(), bookings.getFleetC(), i);
            assignRideToVehicle(bookings.getCityD(), bookings.getFleetD(), i);

            for (int j = 0; j < vehicles.size(); j++){
                makeMove(vehicles.get(j), i);
            }

        }
    }

    private Vehicle closetVehicle(Ride ride, ArrayList<Vehicle> vehicles){
        // TODO find closet vehicle to ride

        return vehicles.get(0);
    }

    private void assignRideToVehicle(ArrayList<Ride> rides, ArrayList<Vehicle> vehicles, int val){
        if(rides.size() > 0 && vehicles.size() > 0) {
            Ride ride = rides.get(0);
            rides.remove(ride);

            Vehicle vehicle = closetVehicle(ride, vehicles);
            vehicle.assignRide(ride);
            vehicle.setPickup(true);

            this.vehicles.add(vehicle);
            vehicles.remove(vehicle);
        }

        // TODO If still vehicles but no rides, move all vehicles to different city
    }

    private void makeMove(Vehicle vehicle, int val){
        int intersectionX = vehicle.getIntersection().getIntersectionX();
        int intersectionY = vehicle.getIntersection().getIntersectionY();

        int nextIntersectionX = vehicle.getNextIntersection().getIntersectionX();
        int nextIntersectionY = vehicle.getNextIntersection().getIntersectionY();

        Direction direction = direction(intersectionX, intersectionY, nextIntersectionX, nextIntersectionY);

        switch (direction){
            case UP -> vehicle.getIntersection().updateIntersection(intersectionX, intersectionY + 1);
            case DOWN -> vehicle.getIntersection().updateIntersection(intersectionX, intersectionY - 1);
            case LEFT -> vehicle.getIntersection().updateIntersection(intersectionX - 1, intersectionY);
            case RIGHT -> vehicle.getIntersection().updateIntersection(intersectionX + 1, intersectionY);
        }

        boolean hasArrived = vehicle.getIntersection().arrived(vehicle.getNextIntersection());

        if(hasArrived){
            if(vehicle.isPickup()){
                vehicle.setPickup(false);
                vehicle.setNextIntersection(vehicle.getCurrentRide().getFinishIntersection());
            }else{
                vehicle.tripCompleted();
                System.out.println("DONE : " + vehicle.getVehicleID());
                System.out.println(val);
                vehicles.remove(vehicle);

                // add back to available fleet
                bookings.addVehicle(vehicle, vehicle.getCity());
            }
        }

    }

    private Direction direction(int x, int y, int nextX, int nextY){
        if(x > nextX){
            return Direction.LEFT;
        }else if(x < nextX){
            return Direction.RIGHT;
        }else if(y < nextY){
            return Direction.UP;
        }else{
            return Direction.DOWN;
        }
    }
}
