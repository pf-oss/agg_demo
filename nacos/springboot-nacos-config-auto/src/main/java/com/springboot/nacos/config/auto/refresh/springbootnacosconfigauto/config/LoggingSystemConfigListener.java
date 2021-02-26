package com.springboot.nacos.config.auto.refresh.springbootnacosconfigauto.config;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.util.parse.DefaultYamlConfigParse;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @Description:
 * @author: pengfei_yao
 * @create: 2020/9/23 15:48
 */
@Component
public class LoggingSystemConfigListener {

    /**
     * 日志配置项的前缀
     */
    private static final String LOGGER_TAG = "logging.level.";

    @Resource
    private LoggingSystem loggingSystem;

    @NacosConfigListener(dataId = "${nacos.filter.data-id}", type = ConfigType.YAML, timeout = 5000)
    public void onChange(String newLog) throws Exception {
        Properties properties = new DefaultYamlConfigParse().parse(newLog);

        for (Object t : properties.keySet()) {
            String key = String.valueOf(t);
            if (key.startsWith(LOGGER_TAG)) {
                String strLevel = properties.getProperty(key, "info");
                LogLevel level = LogLevel.valueOf(strLevel.toUpperCase());
                loggingSystem.setLogLevel(key.replace(LOGGER_TAG, ""), level);
            }
        }
    }

}
