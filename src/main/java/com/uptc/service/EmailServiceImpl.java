package com.uptc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final Environment environment;

    @Override @Async
    public void send(String to, String subject, String content) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, "UTF-8");
            messageHelper.setText(content, true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String from,String to,String subject,String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }

    /*

    @Override @Async
    public void send(String to, String subject, String content) {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail smtp host","smtp gmail com");
        propiedad.setProperty("mail.smtp.starttls.enable","true");
        propiedad.setProperty("mail smtp port","587");
        propiedad.setProperty("mail.smtp.auth","true");

        Session session = Session.getDefaultInstance(propiedad);

        String correoEnvia = "toil.syc@gmail.com";
        String contraseña = "uptc2022";
        String destinatario= to;
        //String asunto = "Confirmacion de Correo Electronico";
        //String mensaje = "Hola querido programador, estas a punto de ser parte de la sociedad de Toil SYC, por favor das click en el siguiente boton para confirmar tu correo y finalizar este proceso.\nDe parte de toda la familia de Toil SYC BIENVENIDO!!!.";

        MimeMessage mail = new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(RecipientType.TO,new InternetAddress(destinatario));
            mail.setSubject(subject);
            mail.setText(content);

            Transport transporte = session.getTransport("smtp");
            transporte.connect(correoEnvia,contraseña);
            transporte.sendMessage(mail,mail.getRecipients(RecipientType.TO));
            transporte.close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }*/

    @Override
    public String buildEmailVerifyMail(String token) {
        String url = environment.getProperty("app.root.frontend") + "/verify-email/" + token;
        return buildEmailBody(
                url,
                "Confirmacion de Correo Electronico",
                "Hola querido programador, estas a punto de ser parte de la sociedad de Toil SYC, por favor das click en el siguiente enlace para confirmar tu correo y finalizar este proceso.\nDe parte de toda la familia de Toil SYC BIENVENIDO!!!.",
                "Click para Verificar"
        );
    }

    @Override
    public String buildResetPasswordMail(String token) {
        String url = environment.getProperty("app.root.frontend") + "/reset-password/" + token;
        return buildEmailBody(
                url,
                "Hola Compañer@\n",
                "Has solicitado un cambio en tu contraseña, si no has sido tu, has caso omiso a este correo" +
                        ", si realmente deseas cambiar tu contraseña accede al siguiente link.\n",
                "Get New Password"
        );
    }
/*
    private String buildEmailBody(String url, String emailBodyHeader, String emailBodyDetail, String buttonText) {
        return "<div style=\"margin: 0 auto; width: 500px; text-align: center; background: #ffffff; border-radius: 5px; border: 3px solid #838383;\">" +
                    "<h2 style=\"background: #838383; padding: 15px; margin: 0; font-weight: 700; font-size: 24px; color: #ffffff;\">" + emailBodyHeader + "</h2>" +
                    "<p style=\"padding: 20px; font-size: 20px; color: #202020;\">" + emailBodyDetail + "</p>" +
                    "<a style=\"display: inline-block; padding: 10px 20px; margin-bottom: 30px; text-decoration: none; background: #3f51b5; font-size: 16px; border-radius: 3px; color: #ffffff;\" href=\" " + url + " \">" + buttonText + "</a>" +
                "</div>";
    }*/

    private String buildEmailBody(String url, String emailBodyHeader, String emailBodyDetail, String buttonText) {
        return emailBodyHeader +"\n"+
                emailBodyDetail +"\n"+
                url;
    }
}
