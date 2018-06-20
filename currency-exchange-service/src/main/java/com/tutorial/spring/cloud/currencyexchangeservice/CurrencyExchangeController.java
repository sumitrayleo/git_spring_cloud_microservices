package com.tutorial.spring.cloud.currencyexchangeservice;

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
    
    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;
    
    @GetMapping("/from/{from}/to/{to}")
    public CurrencyExchange retrieveCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from, to);
        if(currencyExchange == null) {
            throw new CurrencyNotFoundException(String.format("Currency %s and %s may not be valid", from, to));
        }
        currencyExchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        
        return currencyExchange;
    }
}
