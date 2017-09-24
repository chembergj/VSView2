package dk.vsview;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.vsview.model.ATC;
import dk.vsview.model.OnlineClient;
import dk.vsview.model.Pilot;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setTitle(getString(R.string.app_name) + " - " + getString(R.string.title_atc));
                        break;
                    case 1:
                        setTitle(getString(R.string.app_name) + " - " + getString(R.string.title_pilots));
                        break;
                    case 2:
                        setTitle(getString(R.string.app_name) + " - " + getString(R.string.title_friends));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
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

                    addATCLines(tl);
                    break;
                case 2:

                    addPilotLines(tl);
                    break;
                case 3:

                    addFriendLines(tl);
                    break;
                default:
                    break;
            }

            return rootView;
        }

        private void addFriendLines(TableLayout tl) {
            List<OnlineClient> clients = new ArrayList<>();
            clients.add(new Pilot(1360825, "DAL4RX", "Simon Lund", 34976, 35000, 523, "EKCH", "KJFK", "+..EBONY N325A ELSIR/M082F370 NATS MALOT/N0476F390 EVRIN UL607 SPI UT180 PESOV T180 UNOKO", "+VFPS+/V/PBN/A1B1C1D1O1S2 DOF/170923 REG/N810NW EET/KZBW0002 CZQM0100 CZQX0140 50N050W0229 51N040W0314 EGGX0358 53N020W0442 EISN0504 EGTT0543 EBUR0622 EDUU0645 EDGG0648 OPR/DAL RALT/CYYT LPLA EINN RMK/TCAS SIMBRIEF"));
            clients.add(new ATC(1284176, "ENOS_M_CTR", "Haavard Halvorsen", "118.870", ATC.FacilityType.CTR));

            for(OnlineClient client: clients) {
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

        private void addPilotLines(TableLayout tl) {
            List<Pilot> onlinepilots = new ArrayList<>();
            onlinepilots.add(new Pilot(1360825, "DAL4RX", "Simon Lund", 34976, 35000, 523, "EKCH", "KJFK", "+..EBONY N325A ELSIR/M082F370 NATS MALOT/N0476F390 EVRIN UL607 SPI UT180 PESOV T180 UNOKO", "+VFPS+/V/PBN/A1B1C1D1O1S2 DOF/170923 REG/N810NW EET/KZBW0002 CZQM0100 CZQX0140 50N050W0229 51N040W0314 EGGX0358 53N020W0442 EISN0504 EGTT0543 EBUR0622 EDUU0645 EDGG0648 OPR/DAL RALT/CYYT LPLA EINN RMK/TCAS SIMBRIEF"));
            onlinepilots.add(new Pilot(835801, "DAL8804", "Peter Lund", 34994, 35000, 559, "EKCH", "KJFK", "SKORR3.YNKEE BETTE DCT KANNI N139A PORTI/N0494F370 DCT 47N050W/M086F370 48N040W 49N030W 50N020W DCT SOMAX DCT ATSUR DCT GAPLI/N0492F370 UN20 SAM UM140 DVR UL9 KONAN UL607 SPI UT180 PESOV T180 UNOKO", "+VFPS+/V/PBN/A1B1C1D1L1O1S1 NAV/RNVD1E2A1 DOF/170923 REG/N674US EET/KZBW0006 CZQM0045 CZQX0141 PORTI0203 4750N0213 4840N0257 EGGX0343 5020N0427 SOMAX0449 EGTT0520 EBUR0605 EDVV0624 EDUU0625 EDGG0634 SEL/HMAK RVR/75 OPR/DELTA AIR LINES PER/E RALT/CYQX LPPD EINN"));

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

        private void addATCLines(TableLayout tl) {
            List<ATC> onlineatc = new ArrayList<>();
            onlineatc.add(new ATC(1138158, "EKDK_CTR", "Claus Hemberg Joergensen", "121.370", ATC.FacilityType.CTR));
            onlineatc.add(new ATC(1284176, "ENOS_M_CTR", "Haavard Halvorsen", "118.870", ATC.FacilityType.CTR));

            for(ATC atc: onlineatc) {
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
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
