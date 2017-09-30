package dk.vsview;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import dk.vsview.model.IOnlineDataConsumer;
import dk.vsview.model.OnlineData;

public class OnlineDataProviderTask extends
		AsyncTask<Integer, Void, OnlineData> {

	IOnlineDataConsumer onlineDataConsumer;
	private static OnlineData cachedOnlineData = new OnlineData();

	// final String onlineDataUrl = "http://info.vroute.net/vatsim-data.txt";
	final String onlineDataUrl = "http://info.vroute.net/vatsim-data.txt";

	public OnlineDataProviderTask(IOnlineDataConsumer onlineDataConsumer) {
		this.onlineDataConsumer = onlineDataConsumer;
	}

	@Override
	protected OnlineData doInBackground(Integer... params) {

		HttpURLConnection urlConnection = null;

		if (cachedOnlineData.isOutdated()) {

			cachedOnlineData = new OnlineData();

			try {
				URL url = new URL(onlineDataUrl);
				urlConnection = (HttpURLConnection) url.openConnection();

				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
				OnlineDataParser parser = new OnlineDataParser();
				parser.parseOnlineData(reader);
				cachedOnlineData = parser.getOnlineData();
			} catch (MalformedURLException e) {
				Log.w("OnlineDataProviderTask", "Error while retrieving data"
						+ e.toString());
			} catch (IOException e) {
				Log.w("OnlineDataProviderTask", "Error while retrieving data"
						+ e.toString());
			} finally {
				if(urlConnection != null) {
					urlConnection.disconnect();
				}
			}
		}

		return cachedOnlineData;
	}



	protected void onPostExecute(OnlineData data) {
		onlineDataConsumer.dataFetched(data);
	}
}
