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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class MailMessageMapper {

	public static MailMessage mapMessage(MailEnvelope mail) {

		MailMessage.MailMessageBuilder builder = MailMessage.builder();
		builder.size(mail.getSize());
		builder.smtpSender(mail.getSender().asString());
		builder.smtpRecipients(mail.getRecipients().stream().map(rcpt -> rcpt.asString()).collect(Collectors.toList()));
		try {
			MimeMessage msg = new MimeMessage(null, mail.getMessageInputStream());

			builder.mailId(msg.getMessageID());
			builder.subject(msg.getSubject());
			builder.sentDate(msg.getSentDate());
			builder.contentId(msg.getContentID());
			builder.contentType(msg.getContentType());
			builder.receivedDate(Calendar.getInstance().getTime());

			builder.from(convertAddressArray(msg.getFrom()));
			builder.sender(convertAddress(msg.getSender()));
			builder.recipients(convertAddressArray(msg.getRecipients(Message.RecipientType.TO)));
			builder.replyTo(convertAddressArray(msg.getReplyTo()));
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

	private static List<String> convertAddressArray(Address[] addresses) {
		List<Address> addressesList = Arrays.asList(addresses);
		return addressesList.stream().map(MailMessageMapper::convertAddress).collect(Collectors.toList());
	}
}
