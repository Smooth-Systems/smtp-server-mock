package com.smooth.systems.solutions.smtp.mock.client;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import com.smooth.systems.solutions.smtp.mock.rest.MailMessageList;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class EmailsDetailsSinkClient {

	private static String PREFIX_MSG_DETAILS = "/email-details";

	private RestTemplate client = new RestTemplate();

	private RestClientConfiguration config;

	public EmailsDetailsSinkClient(RestClientConfiguration config) {
		this.config = config;
	}

	public List<MailMessage> getAllMessages() {
		MailMessageList details = client.getForObject(generateServerUrl(), MailMessageList.class);
		return details.getMessageDetails();
	}

	public MailMessage getMessagesWithMailId(String mailId) {
		String url = generateServerUrl() + "/mail-id/" + mailId;
		return client.getForObject(url, MailMessage.class);
	}

	public List<MailMessage> getMessagesWithSmtpFrom(String from) {
		String url = generateServerUrl() + "/smtp-sender/" + from;
		MailMessageList details = client.getForObject(url, MailMessageList.class);
		return details.getMessageDetails();
	}

	public List<MailMessage> getMessagesForSmtpRecipient(String recipient) {
		String url = generateServerUrl() + "/smtp-to/" + recipient;
		MailMessageList details = client.getForObject(url, MailMessageList.class);
		return details.getMessageDetails();
	}

	private String generateServerUrl() {
		return String.format("http://%s:%d%s", config.getHost(), config.getPort(), PREFIX_MSG_DETAILS);
	}
}
