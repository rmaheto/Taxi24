package com.codemaniac.taxi.aop;

import com.codemaniac.taxi.entity.Driver;
import com.codemaniac.taxi.service.EmailService;
import com.codemaniac.taxi.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class NotificationsAspect {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;

    @Value("${msgs.registrations.subject}")
    private String subject;

    @Value("${msgs.registrations.welcome}")
    private String welcomeMessage;

    @After("execution(* com.codemaniac.taxi.service.DriverService.addDriver(..))")
    public void afterDriverRegistration(JoinPoint joinPoint){

        log.info("Called the '{}' method", joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        Driver driver = (Driver)args[0];
        String msg = String.format(welcomeMessage,driver.getName());
        emailService.sendSimpleMessage(driver.getEmail(), subject,msg);
    }
}
