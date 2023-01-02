public class Ride {

    private Intersection startIntersection, finishIntersection;
    private int earliestStart, latestFinish, totalDistance, rideID;

    public Ride(int startX, int startY, int finishX, int finishY, int earliestStart, int latestFinish, int rideID){
        this.rideID = rideID;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
        startIntersection = new Intersection(startX, startX);
        finishIntersection = new Intersection(finishX, finishY);

        totalDistance = startIntersection.distance(finishIntersection);
    }

    public Intersection getStart(){
        return startIntersection;
    }

    public Intersection geFinish(){
        return startIntersection;
    }




}
