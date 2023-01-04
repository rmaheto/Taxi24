package com.codemaniac.taxi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${mail.host}")
    private String mailServer;
    @Value("${mail.port}")
    private int portNumber;
    @Value("${mail.username}")
    private String mailServerUsername;
    @Value("${mail.password}")
    private String mailServerPassword;
    @Value("${mail.protocol}")
    private String mailProtocol;
    @Value("${mail.properties.mail.smtp.auth}")
    private String mailAuth;
    @Value("${mail.debug}")
    private String debugMailServer;


    @Bean
    public JavaMailSender emailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailServer);
        mailSender.setPort(portNumber);

        mailSender.setUsername(mailServerUsername);
        mailSender.setPassword(mailServerPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProtocol);
        props.put("mail.smtp.auth", mailAuth);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", debugMailServer);

        return mailSender;
    }
}
