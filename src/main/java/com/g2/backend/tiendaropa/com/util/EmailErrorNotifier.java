package com.g2.backend.tiendaropa.com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailErrorNotifier {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarNotificacionError(String asunto, String mensaje) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("steven.melendez001@gmail.com");
        mailMessage.setSubject(asunto);
        mailMessage.setText(mensaje);
        mailSender.send(mailMessage);
    }
}
