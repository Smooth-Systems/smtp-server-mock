package com.smooth.systems.solutions.smtp.mock;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import com.smooth.systems.solutions.smtp.mock.client.RestClientConfiguration;
import com.smooth.systems.solutions.smtp.mock.client.RestClientMessagesDetails;

import java.util.Collections;
import java.util.List;

public class MainTesting {

	public static void main(String[] args) {
		RestClientMessagesDetails client = new RestClientMessagesDetails(new RestClientConfiguration());

		System.out.println("\nRetrieve all emails details:");
		List<MailMessage> messages = client.getAllMessages();
		printMessages(messages);

		if(!messages.isEmpty()) {
			System.out.println("\nRetrieve with mailId:");
			MailMessage message = client.getMessagesWithMailId(messages.get(0).getMailId());
			printMessages(Collections.singletonList(message));
		}

		System.out.println("\nRetrieve with smtp sender:");
		messages = client.getMessagesWithSmtpFrom("real@rgagnon.com");
		printMessages(messages);

		System.out.println("\nRetrieve for smtp recipient:");
		messages = client.getMessagesForSmtpRecipient("real@rgagnon.com");
		printMessages(messages);
	}

	private static void printMessages(List<MailMessage> messages) {
		messages.forEach(msg -> {
			System.out.println("Msg: " + msg);
		});
	}
}
