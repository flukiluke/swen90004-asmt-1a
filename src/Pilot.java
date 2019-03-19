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
}
