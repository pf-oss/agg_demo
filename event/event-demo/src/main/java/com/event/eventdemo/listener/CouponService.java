package com.event.eventdemo.listener;

import com.event.eventdemo.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: pf
 * @create: 2020/12/15 11:29
 */
@Service
@Slf4j
public class CouponService {

    @EventListener
    public void addCoupon(UserRegisterEvent event){
        log.info("给用户发放优惠券", event.getUsername());
    }

}
