public class DepartureZone extends Location {
  @Override
  public synchronized Ship depart() throws InterruptedException {
    Ship ship = super.depart();
    System.out.format("ship [%d] departs departure zone\n", ship.id);
    return ship;
  }
}
