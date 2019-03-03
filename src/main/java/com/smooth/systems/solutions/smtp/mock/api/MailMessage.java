package com.smooth.systems.solutions.smtp.mock.api;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class MailMessage {

	private long size;
	private String smtpSender;
	private List<String> smtpRecipients;

	private String mailId;
	private String subject;
	private Date sentDate;
	private Date receivedDate;
	private String contentId;
	private String contentType;

	private String sender;
	private List<String> from;
	private List<String> replyTo;
	private List<String> recipients;
}