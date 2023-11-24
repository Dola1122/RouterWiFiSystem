import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import static java.lang.Thread.sleep;

public class Network {


    public static void main(String[] args) throws InterruptedException {
        File file = new File("output.txt");

        if (file.exists()){
            file.delete();
        } else {
            System.out.println("no file");
        }
        int numberOfConnections, numberOfDevices;

        ArrayList<Device> devices = new ArrayList<>();

        try (Scanner input = new Scanner(System.in)) {
            System.out.println(" ");
            // Get the number of Wi-Fi connections from the user
            System.out.print("Enter number of Wi-Fi Connections: ");
            numberOfConnections = input.nextInt();

            // Create a router with the specified number of connections
            Router router = new Router(numberOfConnections);

            // Get the number of devices the user wants to connect
            System.out.print("Enter number of devices Clients want to connect: ");
            numberOfDevices = input.nextInt();

            // Get information about each device from the user and add it to the ArrayList
            System.out.println("Enter device name and type");
            for (int i = 0; i < numberOfDevices; i++) {
                System.out.print("Enter device(" + (i + 1) + ") : ");
                Device newDevice = new Device(input.next(), input.next(), router);
                devices.add(newDevice);
            }
        }

        for (int i = 0; i < numberOfDevices; i++) {
            sleep(200);
            devices.get(i).start();
        }
    }
}
