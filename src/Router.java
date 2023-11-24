import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Router {
    private boolean[] connectedDevices;
    private int maxDeviceCount, currentConnectedDevices;
    public Semaphore semaphore;

    public Router(int maxDeviceCount) {
        this.maxDeviceCount = maxDeviceCount;
        semaphore = new Semaphore(maxDeviceCount);
        connectedDevices = new boolean[maxDeviceCount];
    }

    public synchronized int connect(Device device) throws InterruptedException {
        for (int i = 0; i < maxDeviceCount; i++) {
            if (!connectedDevices[i]) {
                currentConnectedDevices++;
                device.setConnectionID(i + 1);
                connectedDevices[i] = true;
                Thread.sleep(100);
                break;
            }
        }
        return device.getConnectionID();
    }

    public synchronized void disconnect(Device device) {
        currentConnectedDevices--;
        connectedDevices[device.getConnectionID() - 1] = false;
        logToFile("Connection " + device.getConnectionID() + ": " + device.getDeviceName() + " Logged out");
        notify();
    }

    private void logToFile(String message) {
        try {
            FileWriter fileWriter = new FileWriter("output.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(message);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
