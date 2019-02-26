package com.smooth.systems.solutions.smtp.mock.rest;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import com.smooth.systems.solutions.smtp.mock.sink.MailSink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class MailDetailsController {

	@Autowired
	private MailSink mailSink;

	@GetMapping("/email-details")
	public List<MailMessage> getMailDetails() {
		List<MailMessage> emails = Collections.emptyList();
		log.info("Retrieved {} emails.", emails.size());
		return emails;
	}

	@GetMapping("/email-details/{mailId}")
	public MailMessage getMailDetailsForMailId(@PathVariable("mailId") String mailId) {
		MailMessage mailMessage = mailSink.getMailMessage(mailId);
		if(mailMessage == null) {
			String msg = String.format("No email found for mailId: %s", mailId);
			log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return mailMessage;
	}
}
