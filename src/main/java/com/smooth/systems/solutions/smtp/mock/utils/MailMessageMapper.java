package com.smooth.systems.solutions.smtp.mock.utils;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import org.apache.james.protocols.smtp.MailEnvelope;

import java.util.List;
import java.util.stream.Collectors;

public interface MailMessageMapper {

	static MailMessage mapMessage(MailEnvelope mail) {
		return new MailMessage() {
			@Override
			public String getMessageId() {
				return null;
			}

			@Override
			public String getSender() {
				return mail.getSender().asPrettyString();
			}

			@Override
			public List<String> getRecipients() {
				return mail.getRecipients().stream().map(rcpt -> rcpt.asPrettyString()).collect(Collectors.toList());
			}

			@Override
			public String getSubject() {
				return null;
			}

			@Override
			public long getSize() {
				return mail.getSize();
			}
		};
	}
}
