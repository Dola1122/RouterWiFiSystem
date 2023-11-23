
public class Device extends Thread {
    private String name;
    private String type;
    private Router router;

    public Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
    }

    public String getType() {
        return type;
    }

    public String getDeviceName() {
        return name;
    }

    public void performActivity(){

    }
    @Override
    public void run() {

    }
}
