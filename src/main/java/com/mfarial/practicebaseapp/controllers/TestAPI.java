package com.mfarial.practicebaseapp.controllers;

import com.mfarial.practicebaseapp.services.MailSenderImpl;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestAPI {

    @Autowired
    MailSenderImpl mailSender;

    @PostMapping
    public String testEmail() throws EmailException {
//        mailSender.sendEmail();
        return "";
    }
}
