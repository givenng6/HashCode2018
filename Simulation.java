import java.util.ArrayList;

public class Simulation {

    private int totalSteps;
    private Bookings bookings;

    private ArrayList<Vehicle> commutingRides;

    public Simulation(Bookings bookings, int totalSteps){
        this.bookings = bookings;
        this.totalSteps = totalSteps;
        this.commutingRides = new ArrayList<>();
    }

    public void start(){
        for(int i = 0; i < totalSteps; i++){
            //System.out.println("Steps " + i);
            assignRidesToVehicles(bookings.getCityA(), bookings.getFleetA(), i);
            assignRidesToVehicles(bookings.getCityB(), bookings.getFleetB(), i);
            assignRidesToVehicles(bookings.getCityC(), bookings.getFleetC(), i);
            assignRidesToVehicles(bookings.getCityD(), bookings.getFleetD(), i);

            for(Vehicle vehicle : commutingRides){
                makeMove(vehicle, i);
            }
        }
    }

    public void assignRidesToVehicles(ArrayList<Ride> rides, ArrayList<Vehicle> vehicles, int val){
        if(val == 0){
            Vehicle vehicle = vehicles.get(0);
            Ride ride = rides.get(0);
            vehicle.setPickup(true);
            vehicle.assignRide(ride);
            commutingRides.add(vehicle);

            // TODO Remove vehicle from original list
        }
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
                //TODO add back to available fleet
            }
        }

    }

    private Direction direction(int x, int y, int nextX, int nextY){
        if(x > nextX){
            return Direction.LEFT;
        }else if(x < nextX){
            return Direction.RIGHT;
        }else if(y > nextY){
            return Direction.DOWN;
        }else{
            return Direction.UP;
        }
    }
}
