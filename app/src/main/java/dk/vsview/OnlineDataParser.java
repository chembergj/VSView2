package dk.vsview;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import dk.vsview.model.OnlineClient;
import dk.vsview.model.TimestampedData;
import dk.vsview.model.OnlineClient.ClientType;
import dk.vsview.model.OnlineData;
import dk.vsview.model.ATC;
import dk.vsview.model.Pilot;

public class OnlineDataParser extends DataParser {

	OnlineData onlineData = new OnlineData();

	public OnlineData getOnlineData() {
		return onlineData;
	}

	public void parseOnlineData(BufferedReader reader) throws IOException {

		String section = skipCommentsAndGetNextSection(reader);

		while (section != null) {
			if (section.equals("!GENERAL:"))
				readGeneral(reader, onlineData);
			else if (section.equals("!VOICE SERVERS:"))
				readVoiceServers(reader);
			else if (section.equals("!CLIENTS:"))
				onlineData.setOnlineClients(readClients(reader));
			else if (section.equals("!SERVERS:"))
				readServers(reader);
			else if (section.equals("!PREFILE:"))
				readPrefile(reader);

			section = skipCommentsAndGetNextSection(reader);
		}
	}

	private List<OnlineClient> readClients(BufferedReader reader)
			throws IOException {

		List<OnlineClient> clients = new ArrayList<OnlineClient>();
		String line;

		while (!(line = reader.readLine()).equals(";")) {
			OnlineClient client = null;
			String[] values = line.split(":");
			ClientType clientType = ClientType.valueOf(values[3].length() == 0 ? "UNKNOWN" : values[3]);

			if(clientType.equals(ClientType.PILOT)) {
				Pilot pilot = new Pilot(
				        Integer.parseInt(values[1]),
                        values[0],
                        values[2],
						values[12],
						values[7].isEmpty() ? 0 : Integer.parseInt(values[7]),
						values[8].isEmpty() ? 0 : Integer.parseInt(values[8]),
                        values[11],
                        values[13],
                        values[30],
                        values[29]);
				client = pilot;
			}
			else if(clientType.equals(ClientType.ATC)) {
				ATC atc = new ATC(Integer.parseInt(values[1]), values[0], values[2], values[4], ATC.FacilityType.parseString(values[16]));
				client = atc;
			}

			clients.add(client);
		}

		return clients;
	}

	private void readGeneral(BufferedReader reader, TimestampedData tsData) throws IOException {

		reader.readLine(); // Skip VERSION
		String reloadLine = reader.readLine(); // RELOAD
		tsData.setMinutesToReload(Integer.parseInt(reloadLine
				.substring(9)));

		String updateLine = reader.readLine(); // UPDATE
		DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyyMMddHHmmss")
				.withZone(DateTimeZone.UTC);
		tsData.setUpdateDate(fmt.parseDateTime(updateLine
				.substring(9)));

		// Skip the remains of the block
		String line = reader.readLine();
		while (line != null && !line.startsWith(";")) {
			line = reader.readLine();
		}
	}

}
