package com.tutorial.spring.cloud.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tutorial.spring.cloud.limitsservice.bean.LimitConfiguration;
import com.tutorial.spring.cloud.limitsservice.configuration.Configuration;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;
    
    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsConfiguration() {
        return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
    }

    @GetMapping("/limits/fault-tolerance")
    @HystrixCommand(fallbackMethod = "fallbackRetrieveLimitsConfiguration")
    public LimitConfiguration retrieveFaultTolerantLimitsConfiguration() {
        throw new RuntimeException("Not Available");
    }

    public LimitConfiguration fallbackRetrieveLimitsConfiguration() {
        return new LimitConfiguration(8, 888);
    }
}