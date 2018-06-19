package com.tutorial.spring.cloud.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;
    
    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(65L));
        currencyExchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        
        return currencyExchange;
    }
}
