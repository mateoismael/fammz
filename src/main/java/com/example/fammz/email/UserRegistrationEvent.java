package com.example.fammz.email;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import java.util.Map;

@Getter
public class UserRegistrationEvent extends ApplicationEvent {
    private final String email;
    private final String subject;
    private final String templateName;
    private final Map<String, Object> variables;

    public UserRegistrationEvent(String email, String subject, String templateName, Map<String, Object> variables) {
        super(email);
        this.email = email;
        this.subject = subject;
        this.templateName = templateName;
        this.variables = variables;
    }

}