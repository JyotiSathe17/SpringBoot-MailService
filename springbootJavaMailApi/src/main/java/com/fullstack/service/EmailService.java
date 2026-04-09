package com.fullstack.service;


import com.fullstack.model.EmailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements IEmailService{

    @Value("${spring.mail.username}")

    private String userName;
    private final JavaMailSender javaMailSender;


    @Override
    public void sendEmail(EmailModel emailModel) {
    MimeMessage mimeMessage=javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);

            mimeMessageHelper.setFrom(userName);
            mimeMessageHelper.setTo(emailModel.getToEmail());
            mimeMessageHelper.setCc(emailModel.getCcEmail());
            mimeMessageHelper.setSubject(emailModel.getEmailSubject());
            mimeMessageHelper.setText(emailModel.getEmailBody());

            File file=new File(emailModel.getEmailAttachament());

            mimeMessageHelper.addAttachment(file.getName(), file);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
