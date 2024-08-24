package com.memory.springbootmemoryissues.service.fileconnectionleak;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Slf4j
//Service to simulate file connection leak
public class FileConnectionLeakService {

	private static final String SAMPLE_FILE_NAME = "log/samplefile.txt";

	/**
	 * Connects to a sample file and does not close it.
	 */
	public void connect() {
		BufferedReader reader = null;
		try {
			log.info("Leaking File connections new");
			reader = new BufferedReader(new FileReader(SAMPLE_FILE_NAME));
			String line;
			while ((line = reader.readLine()) != null) {
				//IO operations
			}
		} catch (Exception e) {
			log.error(e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
            	log.error(element.toString());
            }
			
			e.printStackTrace();
		}
	}

	/**
	 * Creates a sample file
	 */
	public void createSampleFile() {
		try {
			FileWriter fileWriter = new FileWriter(new File(SAMPLE_FILE_NAME));
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 1; i <= 10; i++) {
				String line = "This is line " + i;
				bufferedWriter.write(line);
				bufferedWriter.newLine(); // Add a newline character
			}

			bufferedWriter.close();

			log.info("Sample file created ");
		} catch (IOException e) { 
			e.printStackTrace();
		} finally{

		}
	}

	/**
	 * Connect and leak a configured number of times with a sleep, allowing the user to attach the process to an analyzer.
	 * @throws InterruptedException 
	 */
	public void start() throws InterruptedException {
		createSampleFile();
		Thread.sleep(1);
		while(true) {
			connect();
		}
	}
}