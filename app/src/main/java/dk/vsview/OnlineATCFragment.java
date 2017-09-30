package dk.vsview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dk.vsview.model.ATC;
import dk.vsview.model.OnlineClient;
import dk.vsview.model.OnlineData;
import dk.vsview.model.Pilot;

/**
 * A placeholder fragment containing a simple view.
 */
public class OnlineATCFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private final List<Integer> friendCIDs = Arrays.asList(811390,812239,832365, 841181, 857075, 861112, 862571, 869132, 879396, 880543);
    public OnlineATCFragment() {
    }

    public void setData(OnlineData onlineData) {
        updateViews(onlineData);
    }
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OnlineATCFragment newInstance(int sectionNumber) {
        OnlineATCFragment fragment = new OnlineATCFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    private void updateViews(OnlineData onlineData) {

        int sectionNo = getArguments().getInt(ARG_SECTION_NUMBER);

        // Set up the table
        TableLayout t1;
        TableLayout tl = getView().getRootView().findViewById(R.id.atc_table);
        addATCLines(tl, onlineData.getOnlineATCForFavoriteFIRs());
        addFriendLines(tl, onlineData.getFriendsOnlinePilots(friendCIDs));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        int sectionNo = getArguments().getInt(ARG_SECTION_NUMBER);

        // Set up the table
        TableLayout t1;
        TableLayout tl = rootView.findViewById(R.id.atc_table);



        switch(sectionNo) {
            case 1:


                addATCLines(tl, null);
                break;
            case 2:

                addPilotLines(tl);
                break;
            case 3:

                addFriendLines(tl, null);
                break;
            default:
                break;
        }

        return rootView;
    }

    private void addFriendLines(TableLayout tl,  List<OnlineClient> clients) {

        tl.removeAllViews();

        if(clients != null) {
            for (OnlineClient client : clients) {
                TableRow row = new TableRow(getActivity());
                row.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView labelCallsign = new TextView(getActivity());
                labelCallsign.setText(client.getCallsign());
                labelCallsign.setPadding(5, 5, 5, 5);
                row.addView(labelCallsign);

                TextView labelRealname = new TextView(getActivity());
                labelRealname.setText(client.getRealname());
                labelRealname.setPadding(5, 5, 5, 5);
                row.addView(labelRealname);

                tl.addView(row, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,                    //part4
                        TableRow.LayoutParams.WRAP_CONTENT));
            }
    }
        else {
            setNoDataInTable(tl);
        }
    }

    private void addPilotLines(TableLayout tl) {
        List<Pilot> onlinepilots = new ArrayList<>();
        onlinepilots.add(new Pilot(1360825, "DAL4RX", "Simon Lund", "35000", 34976,  523, "EKCH", "KJFK", "+..EBONY N325A ELSIR/M082F370 NATS MALOT/N0476F390 EVRIN UL607 SPI UT180 PESOV T180 UNOKO", "+VFPS+/V/PBN/A1B1C1D1O1S2 DOF/170923 REG/N810NW EET/KZBW0002 CZQM0100 CZQX0140 50N050W0229 51N040W0314 EGGX0358 53N020W0442 EISN0504 EGTT0543 EBUR0622 EDUU0645 EDGG0648 OPR/DAL RALT/CYYT LPLA EINN RMK/TCAS SIMBRIEF"));
        onlinepilots.add(new Pilot(835801, "DAL8804", "Peter Lund", "35000", 34994, 559, "EKCH", "KJFK", "SKORR3.YNKEE BETTE DCT KANNI N139A PORTI/N0494F370 DCT 47N050W/M086F370 48N040W 49N030W 50N020W DCT SOMAX DCT ATSUR DCT GAPLI/N0492F370 UN20 SAM UM140 DVR UL9 KONAN UL607 SPI UT180 PESOV T180 UNOKO", "+VFPS+/V/PBN/A1B1C1D1L1O1S1 NAV/RNVD1E2A1 DOF/170923 REG/N674US EET/KZBW0006 CZQM0045 CZQX0141 PORTI0203 4750N0213 4840N0257 EGGX0343 5020N0427 SOMAX0449 EGTT0520 EBUR0605 EDVV0624 EDUU0625 EDGG0634 SEL/HMAK RVR/75 OPR/DELTA AIR LINES PER/E RALT/CYQX LPPD EINN"));

        for(Pilot pilot: onlinepilots) {
            TableRow row = new TableRow(getActivity());
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

            TextView labelCallsign = new TextView(getActivity());
            labelCallsign.setText(pilot.getCallsign());
            labelCallsign.setPadding(5, 5, 5, 5);
            row.addView(labelCallsign);

            TextView labelRealname = new TextView(getActivity());
            labelRealname.setText(pilot.getRealname());
            labelRealname.setPadding(5, 5, 5, 5);
            row.addView(labelRealname);

            tl.addView(row, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,                    //part4
                    TableRow.LayoutParams.WRAP_CONTENT));
        }
    }

    private void addATCLines(TableLayout tl, List<ATC> atcList) {

        tl.removeAllViews();

        if(atcList != null) {
            for (ATC atc : atcList) {
                TableRow row = new TableRow(getActivity());
                row.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView labelCallsign = new TextView(getActivity());
                labelCallsign.setText(atc.getCallsign());
                labelCallsign.setPadding(5, 5, 5, 5);
                row.addView(labelCallsign);

                TextView labelRealname = new TextView(getActivity());
                labelRealname.setText(atc.getRealname());
                labelRealname.setPadding(5, 5, 5, 5);
                row.addView(labelRealname);

                tl.addView(row, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,                    //part4
                        TableRow.LayoutParams.WRAP_CONTENT));
            }
        }
        else {
            setNoDataInTable(tl);
        }
    }

    private void setNoDataInTable(TableLayout tl) {
        TableRow row = new TableRow(getActivity());
        row.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        TextView label = new TextView(getActivity());
        label.setText("No data available");
        label.setPadding(5, 5, 5, 5);
        row.addView(label);
        tl.addView(row, new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,                    //part4
                TableRow.LayoutParams.WRAP_CONTENT));
    }
}
