package com.tensquare.sms.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信监听类
 */
@Slf4j
@Component
@RabbitListener(queues = "sms")
public class SmsListener {


    @RabbitHandler
    public void sendSms(Map<String,String> message){

        log.info("手机号: {}",message.get("mobile"));
        log.info("验证码 {}",message.get("code"));
    }
}
