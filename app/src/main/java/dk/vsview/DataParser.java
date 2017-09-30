package dk.vsview;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class DataParser {
	
	protected void readServers(BufferedReader reader) throws IOException {
		// For now, just skip the block
		String line = reader.readLine();
		while (line != null && !line.startsWith(";")) {
			line = reader.readLine();
		}
	}
	
	protected void readPrefile(BufferedReader reader) throws IOException {
		// For now, just skip the block
		String line = reader.readLine();
		while (line != null && !line.startsWith(";")) {
			line = reader.readLine();
		}
	}

	protected void readVoiceServers(BufferedReader reader) throws IOException {
		// For now, just skip the block
		String line = reader.readLine();
		while (line != null && !line.startsWith(";")) {
			line = reader.readLine();
		}
	}


	protected String skipCommentsAndGetNextSection(BufferedReader reader)
			throws IOException {
		String line = reader.readLine();
		while (line != null && line.startsWith(";")) {
			line = reader.readLine();
		}

		return line;
	}
}
