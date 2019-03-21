public class Location {
    protected final String name;
    private Ship heldShip = null;

    public Location(String name) {
        this.name = name;
    }

    public synchronized void arrive(Ship ship) throws InterruptedException {
        while (heldShip != null) {
            wait();
        }
        heldShip = ship;
        notifyAll();
    }

    public synchronized Ship depart() throws InterruptedException {
        while (heldShip == null) {
            wait();
        }
        Ship departedShip = heldShip;
        heldShip = null;
        notifyAll();
        return departedShip;
    }
}
