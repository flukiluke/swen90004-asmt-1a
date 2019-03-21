public class ArrivalZone extends Location {
    @Override
    public synchronized void arrive(Ship ship) throws InterruptedException {
        super.arrive(ship);
        System.out.format("ship [%d] arrives at arrival zone\n", ship.id);
    }
}
