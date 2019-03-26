/**
 * Controls activation of the shield.
 *
 * The Operator gets its own thread so the shield can be triggered at any
 * time.
 *
 * @author Luke Ceddia [834076]
 */
public class Operator extends Thread {
  // The shield is a feature of the Berth object
  private final Berth berth;

  public Operator(Berth berth) {
    this.berth = berth;
  }

  /**
   * Runs continuously, triggering the shield every Params.debrisLapse
   * milliseconds.
   */
  public void run() {
    while (!isInterrupted()) {
      try {
        // wait some time before the shield is activated
        sleep(Params.debrisLapse());
        berth.triggerShield();
        // triggerShield only returns once the shield has deactivated
      } catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}
