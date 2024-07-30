package com.uptc.service;

public interface EmailService {
    void send(String to, String subject, String content);
    String buildEmailVerifyMail(String token);
    String buildResetPasswordMail(String token);

    void sendMail(String from,String to,String subject,String body);
}
