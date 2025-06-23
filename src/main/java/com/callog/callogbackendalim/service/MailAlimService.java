package com.callog.callogbackendalim.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class MailAlimService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String to, String subject,String nickName, String messageContent) throws MessagingException {
        // 1. context에 변수 전달
        Context context = new Context();
        context.setVariable("username", nickName);
        context.setVariable("message", messageContent);

        // 2. 템플릿 렌더링
        String htmlContent = templateEngine.process("diet-complete-alim", context);

        // 3. 메일 생성
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true); // true → HTML 모드
        helper.setFrom("bravadosw@gmail.com");

        // 4. 전송
        mailSender.send(mimeMessage);
    }
}
