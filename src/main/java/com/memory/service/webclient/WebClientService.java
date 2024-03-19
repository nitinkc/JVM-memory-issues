package com.memory.service.webclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

@Service
@Slf4j
public class WebClientService {

	public void loadWebClientCalls(Integer noOfCalls, String url, String imagePath) {
		int i = 0;
        log.info(" Time: " + LocalTime.now());
		Instant start = Instant.now();
		while (i < noOfCalls) {
			webHeavyClientCall(i, url, imagePath);
			i++;
		}
		Instant end = Instant.now();
		Duration duration = Duration.between(start, end);
		log.info("Time taken: " + duration.toMillis() + " milliseconds");

	}

	public void webClientCall(Integer id, String url, String imagePath) {
		WebClient webClient = WebClient.create(url);

		webClient.get().retrieve().bodyToMono(String.class).subscribe(result -> {
			// Handle the response
			log.info("Response: " + result);
		}, error -> {
			// Handle errors
			System.err.println("Error: " + error.getMessage());
		}, () -> {
			// This block is called when the response is successfully processed
			log.info("Request completed " + id);
		});
		log.info("waiting...");

	}

	public void webHeavyClientCall(Integer id, String url, String imagePath) {

		// Create a WebClient instance
		WebClient webClient = WebClient.create();

		// Prepare the image file
		File imageFile = new File(imagePath);

		// Perform the POST request with the image as a part of the request body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new FileSystemResource(imageFile));
		log.info("Starting to post an image for Id" + id);
		webClient.post().uri(url).contentType(MediaType.MULTIPART_FORM_DATA).body(BodyInserters.fromMultipartData(body))
				.retrieve().bodyToMono(String.class).subscribe(response -> {
					log.info("Response Id" + id + ":" + response);
				});
		log.info(" Time: " + LocalTime.now());
	}
	
}
