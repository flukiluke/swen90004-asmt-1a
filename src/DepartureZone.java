/**
 * The Departure Zone is where ships leaving the simulation are placed.
 *
 * Behaves as per the base Location class, with a specialised log message for
 * departure. See the Location class for details on thread-safety and locking.
 *
 * The Departure Zone can only hold one ship at a time.
 *
 * @author Luke Ceddia [834076]
 */
public class DepartureZone extends Location {
  /**
   * Remove a ship from the departure zone. If no ship is present, waits until
   * a ship is.
   *
   * @return The ship removed from the departure zone.
   */
  @Override
  public synchronized Ship depart() throws InterruptedException {
    Ship ship = super.depart();
    System.out.format("%s departs departure zone\n", ship);
    return ship;
  }
}
