package br.com.freitas.msemail.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.freitas.msemail.domain.Email;
import br.com.freitas.msemail.dto.EmailDto;
import br.com.freitas.msemail.services.EmailService;

@Component
public class EmailConsumer {

	final EmailService emailService;

	public EmailConsumer(EmailService emailService) {
		this.emailService = emailService;
	}

	@RabbitListener(queues = "${broker.queue.email.name}")
	public void listenEmailQueue(@Payload EmailDto emailDto) {
		var model = new Email();
		BeanUtils.copyProperties(emailDto, model);
		emailService.sendEmail(model);
	}

}
