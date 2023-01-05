import java.util.ArrayList;

public class Simulation {

    private int totalSteps;
    private Bookings bookings;

    private ArrayList<Vehicle> vehicles;
    private int totalRides;

    public Simulation(Bookings bookings, int totalSteps){
        this.bookings = bookings;
        this.totalSteps = totalSteps;
        this.vehicles = new ArrayList<>();

        totalRides = 0;
    }

    public void start(){
        for(int i = 0; i < totalSteps; i++){
            assignRideToVehicle(bookings.getCityA(), bookings.getFleetA());
            assignRideToVehicle(bookings.getCityB(), bookings.getFleetB());
            assignRideToVehicle(bookings.getCityC(), bookings.getFleetC());
            assignRideToVehicle(bookings.getCityD(), bookings.getFleetD());

            for (int j = 0; j < vehicles.size(); j++){
                makeMove(vehicles.get(j), i);
            }
        }

        System.out.println("Rides completed " + totalRides);
    }

    private Vehicle closetVehicle(Ride ride, ArrayList<Vehicle> vehicles){
        // TODO find closet vehicle to ride
        Vehicle closeVehicle = vehicles.get(0);
        int distance = closeVehicle.distance(ride.getStartIntersection());

        for(int i = 1; i < vehicles.size(); i++){
            Vehicle vehicle = vehicles.get(i);

            if(vehicle.distance(ride.getStartIntersection()) < distance){
                closeVehicle = vehicle;
            }
        }

        return closeVehicle;
    }

    private void assignRideToVehicle(ArrayList<Ride> rides, ArrayList<Vehicle> vehicles){
        if(rides.size() > 0 && vehicles.size() > 0) {
            Ride ride = rides.get(0);
            rides.remove(ride);

            Vehicle vehicle = closetVehicle(ride, vehicles);
            vehicle.assignRide(ride);
            vehicle.setPickup(true);

            this.vehicles.add(vehicle);
            vehicles.remove(vehicle);
            assignRideToVehicle(rides, vehicles);
        }

        // TODO Fix out of bound exception
        if(vehicles.size() > rides.size()){
            allocateVehicles( vehicles.size() - rides.size(), vehicles);
        }
    }

    private void allocateVehicles(int size, ArrayList<Vehicle> vehicles){
        City city = vehicles.get(0).getCity();

        switch (city){
            case CITY_A -> allocateVehicles(size, vehicles, bookings.getFleetB(), bookings.getFleetC(), bookings.getFleetD(), bookings.getCityB(), bookings.getCityC(), bookings.getCityD());
            case CITY_B -> allocateVehicles(size, vehicles, bookings.getFleetA(), bookings.getFleetC(), bookings.getFleetD(), bookings.getCityA(), bookings.getCityC(), bookings.getCityD());
            case CITY_C -> allocateVehicles(size, vehicles, bookings.getFleetA(), bookings.getFleetB(), bookings.getFleetD(), bookings.getCityA(), bookings.getCityB(), bookings.getCityD());
            case CITY_D -> allocateVehicles(size, vehicles, bookings.getFleetA(), bookings.getFleetB(), bookings.getFleetC(), bookings.getCityA(), bookings.getCityB(), bookings.getCityC());
        }
    }

    private void allocateVehicles(int size, ArrayList<Vehicle> vehicles, ArrayList<Vehicle> fleetA, ArrayList<Vehicle> fleetB, ArrayList<Vehicle> fleetC, ArrayList<Ride> rideA, ArrayList<Ride> rideB, ArrayList<Ride> rideC){
        int sizeA = rideA.size();
        int sizeB = rideB.size();
        int sizeC = rideC.size();

        int count = 0;
        for(int i = 0; i < size; i++){
            if(count == 0){
                count++;
                allocateVehicles(sizeA, fleetA, vehicles);
            }else if(count == 1){
                count++;
                allocateVehicles(sizeB, fleetB, vehicles);
            }else if(count == 2){
                count = 0;
                allocateVehicles(sizeC, fleetC, vehicles);
            }
        }
    }

    public void allocateVehicles(int size, ArrayList<Vehicle> fleet, ArrayList<Vehicle> vehicles){
        Vehicle vehicle = vehicles.get(0);
        //vehicle.changeCity(city);

        if (size != 0 && fleet.size() < size){
            fleet.add(vehicle);
            vehicles.remove(0);
        }
    }

    private void makeMove(Vehicle vehicle, int step){
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
                if(step >= vehicle.getCurrentRide().getEarliestStart()){
                    vehicle.setPickup(false);
                    vehicle.setNextIntersection(vehicle.getCurrentRide().getFinishIntersection());
                }
            }else{
                vehicle.tripCompleted();
                vehicles.remove(vehicle);

                // add back to available fleet
                bookings.addVehicle(vehicle, vehicle.getCity());

                // TODO write into a file
                totalRides++;
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
        }else if(y > nextY){
            return Direction.DOWN;
        }else{
            return Direction.STOP;
        }
    }
}
