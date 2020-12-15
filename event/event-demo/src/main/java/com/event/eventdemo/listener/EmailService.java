package com.event.eventdemo.listener;

import com.event.eventdemo.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/15 11:22
 */
@Service
@Slf4j
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    @Override
    @Async
    public void onApplicationEvent(UserRegisterEvent userRegisterEvent) {
        log.info("给用户发送邮件:{}", userRegisterEvent.getUsername());
    }
}
