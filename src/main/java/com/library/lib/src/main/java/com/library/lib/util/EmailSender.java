//package com.library.common.common.util;
//
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//public class EmailSender {
//    private final JavaMailSender mailSender;
//
//    public EmailSender(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    public String buildTextForVerificationCode(String content, String code) {
//        return String.format(content, code);
//    }
//
//    public void sendVerificationCode(String to, String subject, String text) {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(text, true);
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            log.error("fail to send verification code");
//            throw new IllegalArgumentException("이메일 발송에 실패하였습니다.");
//        }
//    }
//}