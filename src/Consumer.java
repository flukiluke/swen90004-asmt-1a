/**
 * Removes unloaded cargo ships from the departure zone.
 *
 * @author ngeard@unimelb.edu.au
 * @author Luke Ceddia [834076]
 */

public class Consumer extends Thread {

  // the wait zone from which cargo ships depart
  private DepartureZone departureZone;

  // creates a new consumer for the given wait zone
  Consumer(DepartureZone newDepartureZone) {
    this.departureZone = newDepartureZone;
  }

  // repeatedly collect waiting ships from the departure zone
  public void run() {
    while (!isInterrupted()) {
      try {
        // remove a vessel that is in the departure wait zone
        departureZone.depart();

        // let some time pass before the next departure
        sleep(Params.departureLapse());
      } catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}
