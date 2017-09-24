package dk.vsview.model;

/**
 * Created by Claus on 21-09-2017.
 */

public class ATC extends OnlineClient {



    public enum FacilityType {
        OBSERVER, FSS, DEL, GND, TWR, APP, CTR;

        public static FacilityType parseString(String facilityStr) {
            int facilityNo = Integer.parseInt(facilityStr);
            switch (facilityNo) {
                case 0:
                    return OBSERVER;
                case 1:
                    return FSS;
                case 2:
                    return DEL;
                case 3:
                    return GND;
                case 4:
                    return TWR;
                case 5:
                    return APP;
                case 6:
                    return CTR;
                default:
                    return null;
            }
        }
    }

    private final FacilityType facilityType;
    private final String frequency;

    public ATC(int cid, String callsign, String realname, String frequency, FacilityType facilityType) {
        super(cid, callsign, realname);
        this.facilityType = facilityType;
        this.frequency = frequency;
    }
    public FacilityType getFacilityType() {
        return facilityType;
    }
    public String getFrequency() {
        return frequency;
    }
}
