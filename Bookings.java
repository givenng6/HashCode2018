import java.util.ArrayList;

public class Bookings {

    private ArrayList<Ride> bookingsA;
    private ArrayList<Ride> bookingsB;
    private ArrayList<Ride> bookingsC;
    private ArrayList<Ride> bookingsD;

    private ArrayList<Ride> bookingsAB;
    private ArrayList<Ride> bookingsCD;
    private ArrayList<Ride> bookingsAD;
    private ArrayList<Ride> bookingsBC;

    public Bookings(){
        bookingsA = new ArrayList<>();
        bookingsB = new ArrayList<>();
        bookingsC = new ArrayList<>();
        bookingsD = new ArrayList<>();

        bookingsAB = new ArrayList<>();
        bookingsCD = new ArrayList<>();
        bookingsAD = new ArrayList<>();
        bookingsBC = new ArrayList<>();
    }

    public ArrayList<Ride> getBookingsA() {
        return bookingsA;
    }

    public ArrayList<Ride> getBookingsB() {
        return bookingsB;
    }

    public ArrayList<Ride> getBookingsC() {
        return bookingsC;
    }

    public ArrayList<Ride> getBookingsD() {
        return bookingsD;
    }

    public ArrayList<Ride> getBookingsCD() {
        return bookingsCD;
    }

    public ArrayList<Ride> getBookingsAD() {
        return bookingsAD;
    }

    public ArrayList<Ride> getBookingsBC() {
        return bookingsBC;
    }

    public ArrayList<Ride> getBookingsAB() {
        return bookingsAB;
    }

    public void addBookingA(Ride ride){
        bookingsA.add(ride);
    }

    public void addBookingB(Ride ride){
        bookingsB.add(ride);
    }

    public void addBookingC(Ride ride){
        bookingsC.add(ride);
    }

    public void addBookingD(Ride ride){
        bookingsD.add(ride);
    }

    public void addBookingAB(Ride ride){
        bookingsAB.add(ride);
    }

    public void addBookingCD(Ride ride){
        bookingsCD.add(ride);
    }

    public void addBookingAD(Ride ride){
        bookingsAD.add(ride);
    }

    public void addBookingBC(Ride ride){
        bookingsBC.add(ride);
    }
}
