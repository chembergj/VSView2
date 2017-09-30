package dk.vsview;

import android.app.Activity;
import android.os.AsyncTask;

import dk.vsview.model.IOnlineDataConsumer;
import dk.vsview.model.OnlineData;

/**
 * Created by Claus on 28-09-2017.
 */

public class OnlinePageChangeListener implements android.support.v4.view.ViewPager.OnPageChangeListener {

    IOnlineDataConsumer onlineDataConsumer;
    Activity mainActivity;

    public OnlinePageChangeListener(Activity mainActivity, IOnlineDataConsumer onlineDataConsumer) {
        this.onlineDataConsumer = onlineDataConsumer;
        this.mainActivity = mainActivity;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                mainActivity.setTitle( mainActivity.getApplicationContext().getString(R.string.app_name) + " - " +  mainActivity.getApplicationContext().getString(R.string.title_atc));
                OnlineDataProviderTask task = new OnlineDataProviderTask(onlineDataConsumer);
                task.execute();
                break;
            case 1:
                mainActivity.setTitle(mainActivity.getApplicationContext().getString(R.string.app_name) + " - " + mainActivity.getApplicationContext().getString(R.string.title_pilots));
                break;
            case 2:
                mainActivity.setTitle(mainActivity.getApplicationContext().getString(R.string.app_name) + " - " + mainActivity.getApplicationContext().getString(R.string.title_friends));
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
