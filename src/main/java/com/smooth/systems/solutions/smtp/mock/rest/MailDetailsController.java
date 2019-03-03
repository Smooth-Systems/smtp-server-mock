package com.smooth.systems.solutions.smtp.mock.rest;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import com.smooth.systems.solutions.smtp.mock.sink.MailSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class MailDetailsController {

	@Autowired
	private MailSink mailSink;

	@RequestMapping("/email-details")
	public List<MailMessage> getMailDetails() {
		List<MailMessage> emails = mailSink.getMailMessages();
		log.info("Retrieved {} emails.", emails.size());
		return emails;
	}

	@RequestMapping("/email-details/mail-id/{mailId}")
	public MailMessage getMailDetailsForMailId(@PathVariable("mailId") String mailId) {
		log.info("getMailDetailsForMailId({})", mailId);
		MailMessage mailMessage = mailSink.getByMailId(mailId);
		return validateAndReturn(mailMessage, "mailId", mailId);
	}

	@RequestMapping("/email-details/smtp-sender/{from}")
	public List<MailMessage> getMailDetailsFromMailAddress(@PathVariable("from") String from) {
		log.info("getMailDetailsFromMailAddress({})", from);
		return mailSink.getBySmtpSender(from);
	}

	@RequestMapping("/email-details/smtp-to/{to}")
	public List<MailMessage> getMailDetailsToReceipent(@PathVariable("to") String to) {
		log.info("getMailDetailsToReceipent({})", to);
		return mailSink.getBySmtpRecepient(to);
	}

	private MailMessage validateAndReturn(MailMessage mailMessage, String varName, String value) {
		if(mailMessage == null) {
			String msg = String.format("No email found for %s: %s", varName, value);
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return mailMessage;
	}
}
