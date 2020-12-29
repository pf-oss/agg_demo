package com.config.configpropertie;

import com.config.configpropertie.config.OrderProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ConfigPropertieApplicationTests {

    @Autowired
    private OrderProperties orderProperties;

    @Test
    public void OrderPropertiesCommandLineRunner(){
        log.info("payTimeoutSeconds:" + orderProperties.getPayTimeoutSeconds());
        log.info("createFrequencySeconds:" + orderProperties.getCreateFrequencySeconds());
    }

}
