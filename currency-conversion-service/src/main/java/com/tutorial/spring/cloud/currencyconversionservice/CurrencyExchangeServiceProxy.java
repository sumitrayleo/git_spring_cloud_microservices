package com.tutorial.spring.cloud.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange-service", path="localhost:8000")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyConversionResponse retrieveCurrencyExchange(@PathVariable String from, @PathVariable String to);
}
