public class Simulation {

    private int totalSteps;
    private Bookings bookings;

    public Simulation(Bookings bookings, int totalSteps){
        this.bookings = bookings;
        this.totalSteps = totalSteps;
    }

    public void start(){
        for(int i = 0; i < totalSteps; i++){

        }
    }

    public void assignRidesToVehicles(){

    }

    private void makeMove(Vehicle vehicle){
        int intersectionX = vehicle.getIntersection().getIntersectionX();
        int intersectionY = vehicle.getIntersection().getIntersectionY();

        int nextIntersectionX = vehicle.getNextIntersection().getIntersectionX();
        int nextIntersectionY = vehicle.getNextIntersection().getIntersectionY();

        Direction direction = direction(intersectionX, intersectionY, nextIntersectionX, nextIntersectionY);

        switch (direction){
            case UP -> vehicle.getIntersection().updateIntersection(intersectionX, intersectionY - 1);
            case DOWN -> vehicle.getIntersection().updateIntersection(intersectionX, intersectionY + 1);
            case LEFT -> vehicle.getIntersection().updateIntersection(intersectionX - 1, intersectionY);
            case RIGHT -> vehicle.getIntersection().updateIntersection(intersectionX + 1, intersectionY - 1);
        }

        boolean hasArrived = vehicle.getIntersection().arrived(vehicle.getNextIntersection());

        if(hasArrived){
            if(vehicle.isPickup()){
                vehicle.setPickup(false);
                vehicle.setNextIntersection(vehicle.getCurrentRide().getFinishIntersection());
            }else{
                vehicle.tripCompleted();
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
