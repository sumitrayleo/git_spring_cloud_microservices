package com.tutorial.spring.cloud.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}