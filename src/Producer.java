/**
 * Produces new cargo ships wanting to unload cargo at the space station.
 *
 * @author ngeard@unimelb.edu.au
 * @author Luke Ceddia [834076]
 */

public class Producer extends Thread {

  // the wait zone at which ships will arrive
  private ArrivalZone arrivalZone;

  // create a new producer
  Producer(ArrivalZone newArrivalZone) {
    this.arrivalZone = newArrivalZone;
  }

  // cargo ships arrive at the arrival zone at random intervals.
  public void run() {
    while (!isInterrupted()) {
      try {
        // create a new cargo ship and send it to the arrival zone.
        Ship ship = Ship.getNewShip();
        arrivalZone.arrive(ship);

        // let some time pass before the next ship arrives
        sleep(Params.arrivalLapse());
      } catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}
