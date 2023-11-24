import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Device extends Thread {
    private String deviceName, deviceType;
    private int connectionID;
    private Router deviceRouter;

    public Device(String name, String type, Router router) {
        deviceName = name;
        deviceType = type;
        deviceRouter = router;
        connectionID = -1;
    }

    @Override
    public void run() {
        try {
            deviceRouter.semaphore.acquire(this);
            connectionID = deviceRouter.connect(this);
            logToFile("Connection " + connectionID + ": " + deviceName + " Occupied");
            performOnlineActivity();
            deviceRouter.disconnect(this);
            deviceRouter.semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void performOnlineActivity() throws InterruptedException, IOException {
        logToFile("Connection " + connectionID + ": " + deviceName + " Login");
        logToFile("Connection " + connectionID + ": " + deviceName + " performs online activity");
        sleep(2000);
    }

    private void logToFile(String message) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("output.txt", false);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(message);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getConnectionID() {
        return connectionID;
    }

    public void setConnectionID(int connectionID) {
        this.connectionID = connectionID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }
}

