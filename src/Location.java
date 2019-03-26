/**
 * Base class for all the places a ship could be.
 *
 * Provides logic for adding and removing ships in a thread-safe manner.
 *
 * @author Luke Ceddia [834076]
 */
abstract public class Location {
  // The ship currently at this location, if any.
  private volatile Ship heldShip = null;

  /**
   * Insert a ship into this location. Will only return when a place is
   * available for it.
   *
   * @param ship Ship to be inserted
   */
  public synchronized void arrive(Ship ship) throws InterruptedException {
    // Block until space in the location is available
    while (heldShip != null) {
      wait();
    }
    heldShip = ship;
    notifyAll();
  }

  /**
   * Cause a ship to leave this location. If no ship is at this location
   * this function will wait until there is a ship present.
   *
   * @return Ship that was at this location
   */
  public synchronized Ship depart() throws InterruptedException {
    // Block until a ship is present
    while (heldShip == null) {
      wait();
    }
    Ship departedShip = heldShip;
    heldShip = null;
    notifyAll();
    return departedShip;
  }
}
