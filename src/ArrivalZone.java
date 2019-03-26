/**
 * An Arrival Zone is where new ships are placed.
 *
 * Behaves as per the base Location class, with specialised log messages. See
 * the Location class for details on thread-safety and locking.
 *
 * The Arrival Zone can only hold one ship at a time.
 *
 * @author Luke Ceddia [834076]
 */
public class ArrivalZone extends Location {
  /**
   * Insert a ship into the arrival zone and log such occurrence. Only returns
   * when space is available.
   *
   * @param ship Ship to insert to arrival zone.
   */
  @Override
  public synchronized void arrive(Ship ship) throws InterruptedException {
    super.arrive(ship);
    System.out.format("%s arrives at arrival zone\n", ship);
  }

  /**
   * Remove a ship from the arrival zone, and log which pilot is responsible.
   * Only returns when a ship is available.
   *
   * @param pilot Pilot bringing the ship out of the zone.
   * @return The ship that was in the zone.
   */
  public synchronized Ship depart(Pilot pilot) throws InterruptedException {
    Ship ship = super.depart();
    System.out.format("%s acquires %s.\n", pilot, ship);
    return ship;
  }
}
