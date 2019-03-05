package com.smooth.systems.solutions.smtp.mock.sink;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MailSink {

	private List<MailMessage> messages = new ArrayList<>();

	public void appendMailMessage(MailMessage message) {
		log.info("Mail: mailId={}, smtpFrom={}, smtpTo={}", message.getMailId(), message.getSmtpSender(), message.getSmtpRecipients());
		log.info("      from={}, to={}, subject={}, size={}", message.getSender(), message.getRecipients(), message.getSubject(), message.getSize());
		// TODO check if message with id already exists
		messages.add(message);
	}

	public MailMessage getByMailId(final String mailId) {
		Optional<MailMessage> message = messages.stream().filter(mail -> mailId.equals(mail.getMailId())).findFirst();
		if (message.isPresent()) {
			return message.get();
		}
		return null;
	}

	public List<MailMessage> getMailMessages() {
		return messages;
	}

	public List<MailMessage> getBySmtpSender(String from) {
		return messages.stream().filter(mail -> from.equals(mail.getSmtpSender())).collect(Collectors.toList());
	}

	public List<MailMessage> getBySmtpRecepient(String to) {
		return messages.stream().filter(mail -> mail.getSmtpRecipients().contains(to)).collect(Collectors.toList());
	}
}
