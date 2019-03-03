package com.smooth.systems.solutions.smtp.mock.rest;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class MailMessageList {

	private List<MailMessage> messageDetails;

	public MailMessageList() {
		messageDetails = new ArrayList<>();
	}
}
