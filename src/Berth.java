/**
 * The Berth is where ships are docked so they can be unloaded.
 *
 * Behaves as per the base Location class, but adds extra behaviour to trigger
 * a shield that prevents docking & undocking.
 *
 * @author Luke Ceddia [834076]
 */
public class Berth extends Location {
  /**
   * Activate the shield, and prevent and docking or undocking manoeuvres from
   * starting. The shield lasts for Params.DEBRIS_TIME. This function only
   * returns once the shield has expired.
   */
  public synchronized void triggerShield() throws InterruptedException {
    System.out.println("Shield is activated.");
    // Thread.sleep does not release intrinsic locks, so arrive & depart can't
    // execute while this method is active. This is the mechanism to prevent
    // docking & undocking while the shield is active.
    Thread.sleep(Params.DEBRIS_TIME);
    System.out.println("Shield is deactivated.");
  }

  /**
   * Dock a ship at the berth. If the dock is occupied or the shield is active,
   * waits until the operation can succeed.
   *
   * @param ship Ship to be inserted
   */
  @Override
  public synchronized void arrive(Ship ship) throws InterruptedException {
    super.arrive(ship);
    System.out.format("ship [%d] docks at berth.\n", ship.id);
  }

  /**
   * Undocks a ship from the berth. If the operation cannot happen because the
   * shield is active, waits until the operation can succeed.
   *
   * @return The ship undocked from the berth
   */
  @Override
  public synchronized Ship depart() throws InterruptedException {
    Ship ship = super.depart();
    System.out.format("ship [%d] undocks from berth.\n", ship.id);
    return ship;
  }
}