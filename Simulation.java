public class Simulation {

    private enum move{up, down, left, right};
    private int totalSteps;
    private Bookings bookings;

    public Simulation(Bookings bookings, int totalSteps){
        this.bookings = bookings;
        this.totalSteps = totalSteps;
    }

    public void start(){

    }

    private void makeMove(Vehicle vehicle){
        int intersectionX = vehicle.getIntersection().getIntersectionX();
        int intersectionY = vehicle.getIntersection().getIntersectionY();

        int nextIntersectionX = vehicle.getNextIntersection().getIntersectionX();
        int nextIntersectionY = vehicle.getNextIntersection().getIntersectionY();

        move direction = direction(intersectionX, intersectionY, nextIntersectionX, nextIntersectionY);

        switch (direction){
            case up -> vehicle.getIntersection().updateIntersection(intersectionX, intersectionY - 1);
            case down -> vehicle.getIntersection().updateIntersection(intersectionX, intersectionY + 1);
            case left -> vehicle.getIntersection().updateIntersection(intersectionX - 1, intersectionY);
            case right -> vehicle.getIntersection().updateIntersection(intersectionX + 1, intersectionY - 1);
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

    private move direction(int x, int y, int nextX, int nextY){
        return move.down;
    }
}
