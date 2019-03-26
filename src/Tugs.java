/**
 * Manager for all tugs in the simulation.
 *
 * Tugs are fungible so we just keep track of counts of them, not the actual
 * tugships. This class provides thread-safe acquisition and release of tugs.
 *
 * Methods take a pilot parameter purely for logging purposes; we do not track
 * assignments of tugs to pilots.
 *
 * @author Luke Ceddia [834076]
 */
public class Tugs {
  // Total tugs available in the simulation
  private final int totalTugs;
  // Number of tugs not locked by a pilot
  private volatile int freeTugs;

  public Tugs(int totalTugs) {
    this.totalTugs = totalTugs;
    this.freeTugs = totalTugs;
  }

  /**
   * Allocates tugs to a pilot for its exclusive use. If insufficient tugs
   * are available, blocks until there are.
   *
   * @param numRequested Number of tugs to acquire
   * @param pilotId The pilot wishing to acquire the tugs
   */
  public synchronized void acquire(int numRequested, int pilotId)
          throws InterruptedException {
    // Block until sufficient tugs available
    while (freeTugs < numRequested) {
      wait();
    }
    freeTugs -= numRequested;
    notifyAll();
    System.out.format("pilot %d acquires %d tugs (%d available).\n",
            pilotId, numRequested, freeTugs);
  }

  /**
   * Releases tugs back into the free pool. It is the caller's responsibility
   * to only release at most the number of tugs it has acquired.
   *
   * @param numReleased Number of tugs to release back to the free pool
   * @param pilotId The pilot releasing the tugs
   */
  public synchronized void release(int numReleased, int pilotId) {
    freeTugs += numReleased;
    notifyAll();
    System.out.format("pilot %d releases %d tugs (%d available).\n",
            pilotId, numReleased, freeTugs);
  }
}
