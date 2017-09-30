package dk.vsview.model;

public abstract class OnlineClient {

    private final int CID;
    private final String callsign;
    private final String realname;

    public enum ClientType {
        UNKNOWN, PILOT, ATC;
    }

    protected OnlineClient(int cid, String callsign, String realname) {
        CID = cid;
        this.callsign = callsign;
        this.realname = realname;
     }

    public String getCallsign() {
        return callsign;
    }
    public String getRealname() {
        return realname;
    }
     public int getCID() {
        return CID;
    }


}
