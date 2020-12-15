package com.event.eventdemo.publish;

import com.event.eventdemo.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/15 11:17
 */
@Service
@Slf4j
public class UserService implements ApplicationEventPublisherAware {


    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username){
        log.info("执行注册逻辑:{}", username);
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, username));
    }
}
