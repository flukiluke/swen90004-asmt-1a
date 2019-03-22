public class ArrivalZone extends Location {
  @Override
  public synchronized void arrive(Ship ship) throws InterruptedException {
    super.arrive(ship);
    System.out.format("ship [%d] arrives at arrival zone\n", ship.id);
  }

  public synchronized Ship depart(int pilotId) throws InterruptedException {
    Ship ship = super.depart();
    System.out.format("pilot %d acquires ship [%d].\n", pilotId, ship.id);
    return ship;
  }
}
