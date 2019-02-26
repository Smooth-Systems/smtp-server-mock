package com.smooth.systems.solutions.smtp.mock.sink;

import com.smooth.systems.solutions.smtp.mock.api.MailMessage;
import com.smooth.systems.solutions.smtp.mock.utils.MailMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.james.protocols.smtp.MailEnvelope;
import org.apache.james.protocols.smtp.SMTPSession;
import org.apache.james.protocols.smtp.hook.HookResult;
import org.apache.james.protocols.smtp.hook.MessageHook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageReceiver implements MessageHook {

	@Autowired
	private MailSink sink;

	@Override
	public HookResult onMessage(SMTPSession smtpSession, MailEnvelope mailEnvelope) {
		MailMessage message = MailMessageMapper.mapMessage(mailEnvelope);
		sink.appendMailMessage(message);
		return HookResult.OK;
	}

	@Override
	public void init(Configuration configuration) throws ConfigurationException {

	}

	@Override
	public void destroy() {

	}
}
