package br.com.freitas.msuser.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.freitas.msuser.domain.User;
import br.com.freitas.msuser.web.EmailDto;

@Component
public class UserProducer {

	final RabbitTemplate rabbitTemplate;

	public UserProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value(value = "${broker.queue.email.name}")
	private String routingKey;

	public void publishMessage(User user) {
		var email = EmailDto.builder()
				.userId(user.getUserId())
				.emailTo(user.getEmail())
				.subject("Cadastro realizado com sucesso!")
				.text(user.getName()
						+ ", seja bem vindo(a)! \nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!")
				.build();

		rabbitTemplate.convertAndSend("", routingKey, email);
	}
}
