import java.util.LinkedList;
import java.util.Queue;

public class Router {
    private Semaphore semaphore;
    private Queue<Device> connections;
    private Queue<Device> waitingDevices;

    public Router(int maxConnections) {
        semaphore = new Semaphore(maxConnections);
        connections = new LinkedList<>();
        waitingDevices = new LinkedList<>();
    }

    public void connect(Device device) throws InterruptedException {
    }
    public void disconnect(Device device) {

    }
    public Queue<Device> getConnections() {
        return connections;
    }
}