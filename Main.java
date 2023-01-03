import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class Main{

    public static void main(String[] args){
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

                Ride ride = new Ride(startX, startY, finishX, finishY, earliestStart, latestFinish, i);
                classifier(bookings, ride, cityCols, cityRows);
            }

        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }

        int cityA = (bookings.getDistribution()[0] * numVehicles) / numRides;
        int cityB = (bookings.getDistribution()[1] * numVehicles) / numRides;
        int cityC = (bookings.getDistribution()[2] * numVehicles) / numRides;
        int cityD = (bookings.getDistribution()[3] * numVehicles) / numRides;

        int cities = cityA + cityB + cityC + cityD;

        int clock = 0;
        for(int i = cities; i < numVehicles; i++){
            if(clock == 0){
                cityA++;
            }else if(clock == 1){
                cityB++;
            }else if(clock == 2){
                cityC++;
            }else if(clock == 3){
                cityD++;
                clock = 0;
            }
        }

        assignVehicles(bookings, "A", cityA);
        assignVehicles(bookings, "B", cityB);
        assignVehicles(bookings, "C", cityC);
        assignVehicles(bookings, "D", cityD);
    }

    public static String getCity(int startX, int startY, int finishX, int finishY, int sizeX, int sizeY){
        int halfY = sizeY / 2;
        int halfX = sizeX / 2;

        if(startX <= halfX && startY >= halfY){
            return "A";
        }else if(startX >= halfX && startY >= halfY){
            return  "B";
        }else if(startX <= halfX) {
            return "C";
        }else{
            return "D";
        }

    }

    public static void classifier(Bookings bookings, Ride ride, int sizeX, int sizeY){
        int startX = ride.getStart().getIntersectionX();
        int startY = ride.getStart().getIntersectionY();

        int finishX = ride.geFinish().getIntersectionX();
        int finishY = ride.geFinish().getIntersectionY();

        String city = getCity(startX, startY, finishX, finishY, sizeX, sizeY);
        bookings.addRide(ride, city);
    }

    public static void assignVehicles(Bookings bookings, String city, int size){
        for(int i = 0; i < size; i++){
            bookings.addVehicle(new Vehicle(i, city), city);
        }
    }
}



