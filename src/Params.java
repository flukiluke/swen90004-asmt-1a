/**
 * Tweakables and random values for simulation.
 *
 * Non-obvious values are documented. All times are in milliseconds.
 *
 * @author ngeard@unimelb.edu.au
 * @author Luke Ceddia [834076]
 */

import java.util.Random;

class Params {
  static final int NUM_PILOTS = 2;

  static final int NUM_TUGS = 5;

  // Number of tugs required for a docking operation
  static final int DOCKING_TUGS = 3;

  // Number of tugs required for an undocking operation
  static final int UNDOCKING_TUGS = 2;

  static final int DOCKING_TIME = 800;

  static final int UNDOCKING_TIME = 400;

  static final int UNLOADING_TIME = 1200;

  // Time to travel between locations
  static final int TRAVEL_TIME = 800;

  // Time the shield is active when triggered.
  static final int DEBRIS_TIME = 1800;

  private static final int MAX_ARRIVAL_INTERVAL = 400;

  private static final int MAX_DEPARTURE_INTERVAL = 1000;

  private static final int MAX_DEBRIS_INTERVAL = 2400;

  static int debrisLapse() {
    Random rnd = new Random();
    return rnd.nextInt(MAX_DEBRIS_INTERVAL);
  }

  static int arrivalLapse() {
    Random rnd = new Random();
    return rnd.nextInt(MAX_ARRIVAL_INTERVAL);
  }

  static int departureLapse() {
    Random rnd = new Random();
    return rnd.nextInt(MAX_DEPARTURE_INTERVAL);
  }
}
