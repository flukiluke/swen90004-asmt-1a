public class WaitZone extends Location {
    @Override
    public synchronized void arrive(Ship ship) throws InterruptedException {
        super.arrive(ship);
        System.out.format("ship [%d] arrives at %s zone\n", ship.id, this.name);
    }

    @Override
    public synchronized Ship depart() throws InterruptedException {
        Ship ship = super.depart();
        System.out.format("ship [%d] departs %s zone\n", ship.id, this.name);
        return ship;
    }

    public WaitZone(String name) {
        super(name);
    }
}
