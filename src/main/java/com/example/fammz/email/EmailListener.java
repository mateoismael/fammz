package com.example.fammz.email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    @Async
    public void handleUserRegistrationEvent(UserRegistrationEvent event) {
        try {
            emailService.sendTemplateMessage(event.getEmail(), event.getSubject(), event.getTemplateName(), event.getVariables());
        } catch (MessagingException e) {
            // Log the error
            e.printStackTrace();
        }
    }
}