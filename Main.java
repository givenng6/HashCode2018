import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main{

    public static void main(String[] args){

        ArrayList<Vehicle> fleet = new ArrayList<>();
        Bookings bookings = new Bookings();

        int cityRows = 0;
        int cityCols = 0;

        int numVehicles = 0;
        int totalSteps = 0;
        int numRides = 0;
        int bonus = 0;

        File file = new File("input/b_should_be_easy.in");

        try {
            Scanner input = new Scanner(file);
            String[] line = input.nextLine().split(" ");
            cityRows = Integer.parseInt(line[0]);
            cityCols = Integer.parseInt(line[1]);

            numVehicles = Integer.parseInt(line[2]);
            totalSteps = Integer.parseInt(line[5]);
            numRides = Integer.parseInt(line[3]);
            bonus = Integer.parseInt(line[4]);

            for(int i = 0; i < numRides; i++){
                String[] rideInfo = input.nextLine().split(" ");

                int startX = Integer.parseInt(rideInfo[1]);
                int startY = Integer.parseInt(rideInfo[0]);

                int finishX = Integer.parseInt(rideInfo[3]);
                int finishY = Integer.parseInt(rideInfo[2]);

                int earliestStart = Integer.parseInt(rideInfo[4]);
                int latestFinish = Integer.parseInt(rideInfo[5]);

                Ride newRide = new Ride(startX, startY, finishX, finishY, earliestStart, latestFinish, i);
                classifier(bookings, newRide, cityCols, cityRows);
            }

            for(int i = 0; i < numVehicles; i++){
                fleet.add(new Vehicle(i));
            }

        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }

    }

    public static String getCity(int startX, int startY, int finishX, int finishY, int sizeX, int sizeY){
        int halfY = sizeY / 2;
        int halfX = sizeX / 2;

        if(startX <= halfX && startY <= halfY && finishX <= halfX && finishY <= halfY){
            return "A";
        }else if(startX >= halfX && startY <= halfY && finishX >= halfX && finishY <= halfY){
            return "B";
        }else if(startX <= halfX && startY >= halfY && finishX <= halfX && finishY >= halfY){
            return "C";
        }else if(startX >= halfX && startY >= halfY && finishX >= halfX && finishY >= halfY){
            return "D";
        }else if((startX <= halfX && startY <= halfY && finishX >= halfX && finishY <= halfY) || (startX >= halfX && startY <= halfY && finishX <= halfX && finishY <= halfY)){
            return "AB";
        }else if((startX <= halfX && startY >= halfY && finishX >= halfX && finishY >= halfY) || (startX >= halfX && startY >= halfY && finishX <= halfX && finishY >= halfY)){
            return "CD";
        }else if((startX <= halfX && startY <= halfY && finishX >= halfX && finishY >= halfY) || (startX >= halfX && startY >= halfY && finishX <= halfX && finishY <= halfY)){
            return "AD";
        }else{
            return "BC";
        }
    }

    public static void classifier(Bookings bookings, Ride newRide, int sizeX, int sizeY){
        int startX = newRide.getStart().getIntersectionX();
        int startY = newRide.getStart().getIntersectionY();

        int finishX = newRide.geFinish().getIntersectionX();
        int finishY = newRide.geFinish().getIntersectionY();

        String city = getCity(startX, startY, finishX, finishY, sizeX, sizeY);

        switch (city) {
            case "A" -> bookings.addBookingA(newRide);
            case "B" -> bookings.addBookingB(newRide);
            case "C" -> bookings.addBookingC(newRide);
            case "D" -> bookings.addBookingD(newRide);
            case "AB" -> bookings.addBookingAB(newRide);
            case "CD" -> bookings.addBookingCD(newRide);
            case "AD" -> bookings.addBookingAD(newRide);
            case "BC" -> bookings.addBookingBC(newRide);
        }

    }
}



