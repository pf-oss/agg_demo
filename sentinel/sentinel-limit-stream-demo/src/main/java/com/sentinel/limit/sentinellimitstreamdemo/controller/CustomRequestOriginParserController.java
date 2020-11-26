package com.sentinel.limit.sentinellimitstreamdemo.controller;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;


/**
 * @Description: 黑白名单控制
 * @author: pengfei_yao
 * @create: 2020/11/26 15:39
 */
@Component
public class CustomRequestOriginParserController implements RequestOriginParser {

    @Override
    public String parseOrigin(HttpServletRequest request) {
        // <X> 从 Header 中，获得请求来源
        String origin = request.getHeader("s-user");
        // <Y> 如果为空，给一个默认的
        if (StringUtils.isEmpty(origin)) {
            origin = "default";
        }
        return origin;
    }

}
