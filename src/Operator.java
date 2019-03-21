public class Operator extends Thread {
  private final Berth berth;

  public Operator(Berth berth) {
    this.berth = berth;
  }

  public void run() {
    while (!isInterrupted()) {
      try {
        // wait some time before the shield is activated
        sleep(Params.debrisLapse());
        berth.triggerShield();
      } catch (InterruptedException e) {
        this.interrupt();
      }
    }
  }
}
