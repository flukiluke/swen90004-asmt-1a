public class Pilot extends Thread {
    private final int id;
    private final WaitZone arrivalZone;
    private final WaitZone departureZone;
    private final Tugs tugs;
    private final Berth berth;

    public Pilot(int id, WaitZone arrivalZone, WaitZone departureZone, Tugs tugs, Berth berth) {
        this.id = id;
        this.arrivalZone = arrivalZone;
        this.departureZone = departureZone;
        this.tugs = tugs;
        this.berth = berth;
    }

    public void run() {
        Ship ship;
        try {
            while (true) {
                // Main logic of simulation. Heavily documented for my own sanity.
                // Send pilot aboard a ship in the arrival zone
                ship = arrivalZone.depart();
                System.out.format("pilot %d acquires ship [%d]\n", id, ship.id);

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

                // Tugs are required until the ship arrives at the departure zone
                tugs.release(Params.UNDOCKING_TUGS, id);
                System.out.format("pilot %d releases ship [%d]\n", id, ship.id);
            }
        }
        catch (InterruptedException e) {
            this.interrupt();
        }
    }
}
