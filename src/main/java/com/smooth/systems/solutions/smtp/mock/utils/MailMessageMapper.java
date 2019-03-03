package com.smooth.systems.solutions.smtp.mock.utils;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.james.protocols.smtp.MailEnvelope;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class MailMessageMapper {

	public static MailMessage mapMessage(MailEnvelope mail) {

		MailMessageImpl.MailMessageImplBuilder builder = MailMessageImpl.builder();
		builder.size(mail.getSize());
		builder.smtpSender(mail.getSender().asString());
		builder.smtpRecipients(mail.getRecipients().stream().map(rcpt -> rcpt.asString()).collect(Collectors.toList()));
		try {
			MimeMessage msg = new MimeMessage(null, mail.getMessageInputStream());

			builder.sender(convertAddress(msg.getSender()));
			List<Address> addresses = Arrays.asList(msg.getRecipients(Message.RecipientType.TO));
			builder.recipients(addresses.stream().map(MailMessageMapper::convertAddress).collect(Collectors.toList()));
			builder.subject(msg.getSubject());
			builder.mailId(msg.getMessageID());
		} catch(IOException | MessagingException e) {
			log.error("Unable to read email. Reason: {}", e.getMessage(), e);
		}
		return builder.build();
	}

	public static String convertAddress(Address address) {
		if(address == null) {
			return null;
		}
		// TODO validate
		return address.toString();
	}

	@Data
	@Builder
	private static class MailMessageImpl implements MailMessage {

		private long size;
		private String smtpSender;
		private List<String> smtpRecipients;

		private String mailId;
		private String subject;
		private String sender;
		private List<String> recipients;
	}
}
