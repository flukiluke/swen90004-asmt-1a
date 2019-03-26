/**
 * Contains the main logic and lifecycle of a ship.
 *
 * The Pilot is responsible for acquiring ships & tugs and moving them between
 * the various locations.
 */
public class Pilot extends Thread {
  // Simulation-unique identifier
  private final int id;
  // Various locations a ship can be at
  private final ArrivalZone arrivalZone;
  private final DepartureZone departureZone;
  private final Berth berth;

  // Manager for all tugships
  private final Tugs tugs;

  public Pilot(int id, ArrivalZone arrivalZone, DepartureZone departureZone,
               Tugs tugs, Berth berth) {
    this.id = id;
    this.arrivalZone = arrivalZone;
    this.departureZone = departureZone;
    this.tugs = tugs;
    this.berth = berth;
  }

  /**
   * Runs continuously. Acquires a ship from the arrival zone, then moves it
   * to the berth and departure zone, acquiring and releasing rugs as needed.
   *
   * Each step of the simulation is justified in the method body.
   */
  public void run() {
    Ship ship;
    try {
      while (true) {
        // Main logic of simulation. Heavily documented for my own sanity.
        // Send pilot aboard a ship in the arrival zone
        ship = arrivalZone.depart(id);

        // Get tugs needed for docking
        tugs.acquire(Params.DOCKING_TUGS, id);

        // Travel to vicinity of berth
        Thread.sleep(Params.TRAVEL_TIME);

        // Dock at berth (will only complete when shield is down)
        berth.arrive(ship);
        Thread.sleep(Params.DOCKING_TIME);
        tugs.release(Params.DOCKING_TUGS, id);

        // Unload cargo
        Thread.sleep(Params.UNLOADING_TIME);

        // Undocking procedure
        tugs.acquire(Params.UNDOCKING_TUGS, id);
        berth.depart();
        Thread.sleep(Params.UNDOCKING_TIME);

        // Travel to departure zone
        Thread.sleep(Params.TRAVEL_TIME);
        departureZone.arrive(ship);

        // Spec logs show pilot releasing ship before releasing tugs
        System.out.format("pilot %d releases ship [%d].\n", id, ship.id);

        // Tugs are required until the ship arrives at the departure zone
        tugs.release(Params.UNDOCKING_TUGS, id);
      }
    } catch (InterruptedException e) {
      this.interrupt();
    }
  }
}
