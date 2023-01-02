import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Main{

    public static void main(String[] args){
        ArrayList<Ride> bookings = new ArrayList<>();
        ArrayList<Vehicle> fleet = new ArrayList<>();

        int cityRows = 0;
        int cityCols = 0;

        int numVehicles = 0;
        int totalSteps = 0;
        int numRides = 0;
        int bonus = 0;

        File file = new File("input/a_example.in");

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

                bookings.add(new Ride(startX, startY, finishX, finishY, earliestStart, latestFinish, i));
            }

            for(int i = 0; i < numVehicles; i++){
                fleet.add(new Vehicle(i));
            }

        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }


    }
}



