package com.mq.rockmq_demo1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParamConfigService {
    @Value("${rocket.group}")
    public String rocketGroup;
    @Value("${rocket.topic}")
    public String rocketTopic;
    @Value("${rocket.tag}")
    public String rocketTag;
}
