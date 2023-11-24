import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

import static java.lang.Thread.sleep;

public class Network {
//    File file = new File("output.txt");
//    public Network(){
//        if (file.exists()){
//            file.delete();
//        } else {
//            System.out.println("no file");
//        }
//    }

    public static void main(String[] args) throws InterruptedException {
        File file = new File("output.txt");

        if (file.exists()){
            file.delete();
        }
        int  numberOfConnections, numberOfDecives;
        ArrayList<Device> devices = new ArrayList<>();

        try (Scanner input = new Scanner(System.in)) {
             System.out.println(" ");
            System.out.print("Enter number of WI-FI Connections: ");
            numberOfConnections = input.nextInt();
            Router router = new Router(numberOfConnections);
            System.out.print("Enter number of devices Clients want to connect: ");
            numberOfDecives = input.nextInt();
            System.out.println("Enter device name and type");
            for (int i = 0; i < numberOfDecives; i++) {
                System.out.print("Enter device("+(i+1)+") : ");
                Device newDevice = new Device(input.next(),input.next(), router);
                devices.add(newDevice);
            }
        }

        for (int i = 0; i < numberOfDecives; i++) {
            sleep(100);
            devices.get(i).start();
        }
    }
}
