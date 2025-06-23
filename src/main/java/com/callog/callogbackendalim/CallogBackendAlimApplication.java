package com.callog.callogbackendalim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class CallogBackendAlimApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallogBackendAlimApplication.class, args);
    }

}
