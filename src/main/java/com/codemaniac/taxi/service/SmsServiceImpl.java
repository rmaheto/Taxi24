package com.codemaniac.taxi.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl implements SmsService{
    @Value("${spring.twilio.accountSid}")
    private String accountSid;

    @Value("${spring.twilio.authToken}")
    private String authToken;

    @Value("${spring.twilio.phoneNumber}")
    private String phoneNumber;

    @Override
    public void sendSms(String to, String body) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(phoneNumber),
                body
        ).create();
    }
}
