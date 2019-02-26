package com.smooth.systems.solutions.smtp.mock.sink;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class MailSink {

	private List<MailMessage> messages = new ArrayList<>();

	public void appendMailMessage(MailMessage message) {
		log.info("mail from={}, to={}, subject={}, size={}", message.getSender(), message.getRecipients(),
						message.getSubject(), message.getSize());
		// TODO check if message with id already exists
		messages.add(message);
	}

	public MailMessage getMailMessage(final String mailId) {
		Optional<MailMessage> message = messages.stream().filter(mail -> mailId.equals(mail.getMessageId())).findFirst();
		if (message.isPresent()) {
			return message.get();
		}
		return null;
	}
}
