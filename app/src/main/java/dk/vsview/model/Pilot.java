package dk.vsview.model;

/**
 * Created by Claus on 21-09-2017.
 */

public class Pilot extends OnlineClient  {
    private final String plannedAltitude;
    private final int altitude;
    private final int groundspeed;
    private final String plannedDepAirport;
    private final String plannedDestAirport;
    private final String plannedRoute;
    private final String plannedRemarks;

    public Pilot(int cid, String callsign, String realname, String plannedAltitude, int altitude, int groundspeed, String plannedDepAirport, String plannedDestAirport, String plannedRoute, String plannedRemarks) {
        super(cid, callsign, realname);
        this.plannedAltitude = plannedAltitude;
        this.altitude = altitude;
        this.groundspeed = groundspeed;
        this.plannedDepAirport = plannedDepAirport;
        this.plannedDestAirport = plannedDestAirport;
        this.plannedRoute = plannedRoute;
        this.plannedRemarks = plannedRemarks;
    }

    public String getPlannedAltitude() {
        return plannedAltitude;
    }
    public int getAltitude() {
        return altitude;
    }
    public int getGroundspeed() {
        return groundspeed;
    }
    public String getPlannedDepAirport() {
        return plannedDepAirport;
    }
    public String getPlannedDestAirport() {
        return plannedDestAirport;
    }
    public String getPlannedRoute() {
        return plannedRoute;
    }
    public String getPlannedRemarks() {
        return plannedRemarks;
    }
}
