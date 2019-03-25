/**
 * The Berth is where ships are docked so they can be unloaded.
 *
 * Behaves as per the base Location class, but adds extra behaviour to trigger
 * a shield that prevents docking & undocking.
 *
 * @author Luke Ceddia [834076]
 */
public class Berth extends Location {
  // State of the shield; docking & undocking must wait for deactivated shield.
  private volatile boolean shieldActivated = false;

  /**
   * Activate the shield, and prevent and docking or undocking manoeuvres from
   * starting. The shield lasts for Params.DEBRIS_TIME. This function only
   * returns once the shield has expired.
   */
  public synchronized void triggerShield() throws InterruptedException {
    shieldActivated = true;
    System.out.println("Shield is activated.");
    notifyAll();
    Thread.sleep(Params.DEBRIS_TIME);
    shieldActivated = false;
    System.out.println("Shield is deactivated.");
    notifyAll();
  }

  @Override
  public synchronized void arrive(Ship ship) throws InterruptedException {
    while (shieldActivated) {
      wait();
    }
    super.arrive(ship);
    System.out.format("ship [%d] docks at berth.\n", ship.id);
  }

  @Override
  public synchronized Ship depart() throws InterruptedException {
    while (shieldActivated) {
      wait();
    }
    Ship ship = super.depart();
    System.out.format("ship [%d] undocks from berth.\n", ship.id);
    return ship;
  }
}