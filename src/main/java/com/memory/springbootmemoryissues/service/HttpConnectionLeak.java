package com.memory.springbootmemoryissues.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Service
@Slf4j
public class HttpConnectionLeak {
	private final String HTTP_CONNECTION_URL = "https://www.google.com/";

	public void connect() {
		URL webUrl = null;
		HttpURLConnection connection = null;
		try {
			webUrl = new URL(HTTP_CONNECTION_URL);
			connection = (HttpURLConnection) webUrl.openConnection();
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			log.info("Leaking http connection");

			if (responseCode == HttpURLConnection.HTTP_OK) {
				// Try with resources with File io operations.
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					String line = reader.readLine();
				}
			} else {
				log.info("Failed to fetch content.");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the connection in the finally block to ensure it gets closed even if an exception occurs
			if (connection != null) {
				//connection.disconnect();//Simulating http connection leak.
			}
		}
	}

	public void start() {
		while(true) {
			connect();//leaking http connections
		}
	}
}