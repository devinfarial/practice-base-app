package com.mfarial.practicebaseapp.services;


import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    public void sendEmail(String token, String email) throws EmailException {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName("smtp.mailtrap.io");
        mail.setSmtpPort(2525);
        mail.setAuthenticator(new DefaultAuthenticator("c98200aafc803a", "dafadfda9b6ace"));
        mail.setFrom("admin@baseapp.com", "From");
        mail.addTo(email, "To");
        mail.setSubject("BASE APP - Registration Verification");
        mail.setHtmlMsg("<p style='font-size:16px;color:green'>Click <a href='http://localhost:8080/api/auth/verify?token="+ token +"'>here</a> to verify your account</p>");
        mail.send();
    }

}
