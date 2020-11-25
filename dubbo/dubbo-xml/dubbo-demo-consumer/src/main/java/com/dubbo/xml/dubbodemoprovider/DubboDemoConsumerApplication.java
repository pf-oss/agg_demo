package com.dubbo.xml.dubbodemoprovider;

import com.dubbo.xml.dubbodemoxml.api.UserRpcService;
import com.dubbo.xml.dubbodemoxml.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class DubboDemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoConsumerApplication.class, args);
    }


    @Component
    public class UserRpcServiceTest implements CommandLineRunner {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        @Resource
        private UserRpcService userRpcService;

        @Override
        public void run(String... args) throws Exception {
            UserDTO user = userRpcService.get(1);
            logger.info("[run][发起一次 Dubbo RPC 请求，获得用户为({})", user);
        }

    }

}
