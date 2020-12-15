package com.event.eventdemo.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/15 11:09
 */
@Data
public class UserRegisterEvent extends ApplicationEvent {


    private String username;

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }
}
