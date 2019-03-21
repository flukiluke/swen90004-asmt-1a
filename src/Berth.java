public class Berth extends Location {
    private volatile boolean shieldActivated = false;

    public synchronized void triggerShield() throws InterruptedException {
        shieldActivated = true;
        System.out.println("Shield activated");
        notifyAll();
        Thread.sleep(Params.DEBRIS_TIME);
        shieldActivated = false;
        System.out.println("Shield deactivated");
        notifyAll();
    }

    @Override
    public synchronized void arrive(Ship ship) throws InterruptedException {
        while (shieldActivated) {
            wait();
        }
        super.arrive(ship);
        System.out.format("ship [%d] docks at berth\n", ship.id);
    }

    @Override
    public synchronized Ship depart() throws InterruptedException {
        while (shieldActivated) {
            wait();
        }
        Ship ship = super.depart();
        System.out.format("ship [%d] undocks from berth\n", ship.id);
        return ship;
    }
}