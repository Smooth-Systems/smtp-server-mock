package com.smooth.systems.solutions.smtp.mock.client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestClientConfiguration {

	private int port = 8010;

	private String host = "localhost";

	public RestClientConfiguration() {
	}

	public RestClientConfiguration(String host) {
		this.host = host;
	}
}
