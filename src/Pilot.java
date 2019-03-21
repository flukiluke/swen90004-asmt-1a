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
                ship = arrivalZone.depart();
                System.out.format("pilot %d acquires ship [%d]\n", id, ship.id);
                tugs.acquire(Params.DOCKING_TUGS, id);
                berth.arrive(ship);
                Thread.sleep(Params.DOCKING_TIME);
                tugs.release(Params.DOCKING_TUGS, id);
                Thread.sleep(Params.UNLOADING_TIME);
                tugs.acquire(Params.UNDOCKING_TUGS, id);
                berth.depart();
                Thread.sleep(Params.UNDOCKING_TIME);
                departureZone.arrive(ship);
                tugs.release(Params.UNDOCKING_TUGS, id);
                System.out.format("pilot %d releases ship [%d]\n", id, ship.id);
            }
        }
        catch (InterruptedException e) {
            this.interrupt();
        }
    }
}
