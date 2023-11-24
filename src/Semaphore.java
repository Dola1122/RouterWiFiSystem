import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Semaphore {
    private int permits = 0;

    public Semaphore() {
        permits = 0;
    }

    public Semaphore(int initialPermits) {
        permits = initialPermits;
    }

    public int getPermits() {
        return permits;
    }

    public synchronized void acquire(Device device) throws IOException {
        permits--;
        if (permits < 0) {
            try {
                FileWriter fileWriter = new FileWriter("output.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println(device.getDeviceName() + " (" + device.getDeviceType() + ") arrived and waiting");
                printWriter.close();

                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            try {
                FileWriter fileWriter = new FileWriter("output.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter);

                printWriter.println(device.getDeviceName() + " (" + device.getDeviceType() + ") arrived");
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void release() {
        permits++;
        if (permits <= 0) {
            notify();
        }
    }
}
