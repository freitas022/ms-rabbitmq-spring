package br.com.freitas.msemail.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.freitas.msemail.domain.Email;
import br.com.freitas.msemail.enums.StatusEmail;
import br.com.freitas.msemail.repository.EmailRepository;

@Service
public class EmailService {

	final EmailRepository emailRepository;
    final JavaMailSender emailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSender) {
        this.emailRepository = emailRepository;
        this.emailSender = emailSender;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;

    @SuppressWarnings("finally")
	@Transactional
    public Email sendEmail(Email model) {
        try{
            model.setSendDateEmail(LocalDateTime.now());
            model.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(model.getEmailTo());
            message.setSubject(model.getSubject());
            message.setText(model.getText());
            emailSender.send(message);

            model.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e){
            model.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(model);
        }
    }

}
