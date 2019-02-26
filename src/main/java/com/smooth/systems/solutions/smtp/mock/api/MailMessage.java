package com.smooth.systems.solutions.smtp.mock.api;

import java.util.List;

public interface MailMessage {

	String getMessageId();

	String getSender();

	List<String> getRecipients();

	String getSubject();

	long getSize();
}
