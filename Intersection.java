public class Intersection {

    private int intersectionX, intersectionY;

    public Intersection(int intersectionX, int intersectionY){
        this.intersectionX = intersectionX;
        this.intersectionY = intersectionY;
    }

    public void updateIntersection(int intersectionX, int intersectionY){
        this.intersectionX = intersectionX;
        this.intersectionY = intersectionY;
    }

    public int getIntersectionX(){
        return intersectionX;
    }

    public int getIntersectionY(){
        return intersectionY;
    }

    public int distance(Intersection intersection){
        int differenceY = Math.abs(this.intersectionY - intersection.intersectionY);
        int differenceX = Math.abs(this.intersectionX - intersection.intersectionX);

        return differenceX + differenceY;
    }
}
