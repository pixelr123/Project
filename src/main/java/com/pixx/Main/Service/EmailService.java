package com.pixx.Main.Service;

import org.springframework.mail.SimpleMailMessage;

import javax.mail.MessagingException;

public interface EmailService {
    public void sendEmail(SimpleMailMessage email) throws MessagingException;

}
