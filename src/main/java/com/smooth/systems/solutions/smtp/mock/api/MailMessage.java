package com.smooth.systems.solutions.smtp.mock.api;

import javax.mail.internet.MimeMessage;
import java.util.List;

public interface MailMessage {

	String getMailId();

	String getSender();

	List<String> getRecipients();

	String getSmtpSender();

	List<String> getSmtpRecipients();

	String getSubject();

	long getSize();
}
