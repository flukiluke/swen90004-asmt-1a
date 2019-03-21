public class Tugs {
  private final int totalTugs;
  private volatile int freeTugs;

  public Tugs(int totalTugs) {
    this.totalTugs = totalTugs;
    this.freeTugs = totalTugs;
  }

  public synchronized void acquire(int numRequested, int pilotId)
          throws InterruptedException {
    while (freeTugs < numRequested) {
      wait();
    }
    freeTugs -= numRequested;
    notifyAll();
    System.out.format("pilot %d acquires %d tugs (%d available).\n",
            pilotId, numRequested, freeTugs);
  }

  public synchronized void release(int numReleased, int pilotId) {
    freeTugs += numReleased;
    // Sanity check - cannot release more tugs than were acquired.
    if (freeTugs > totalTugs) {
      throw new Error("Attempt to release more tugs than acquired");
    }
    notifyAll();
    System.out.format("pilot %d releases %d tugs (%d available).\n",
            pilotId, numReleased, freeTugs);
  }
}
