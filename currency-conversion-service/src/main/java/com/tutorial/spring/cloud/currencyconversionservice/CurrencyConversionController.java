package com.tutorial.spring.cloud.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @GetMapping("currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse returnCurrencyCalculatedValue(@PathVariable String from, @PathVariable String to,
        @PathVariable BigDecimal quantity) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionResponse> responseEntity = new RestTemplate().getForEntity(
            "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionResponse.class, uriVariables);
        CurrencyConversionResponse currencyConversionResponse = responseEntity.getBody();
        return new CurrencyConversionResponse(currencyConversionResponse.getId(), from, to,
            currencyConversionResponse.getConversionMultiple(), quantity,
            quantity.multiply(currencyConversionResponse.getConversionMultiple()), currencyConversionResponse.getPort());
    }
    
    @GetMapping("currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse returnCurrencyCalculatedValueFeign(@PathVariable String from, @PathVariable String to,
        @PathVariable BigDecimal quantity) {

        CurrencyConversionResponse currencyConversionResponse = currencyExchangeServiceProxy.retrieveCurrencyExchange(from, to);

        logger.info("{}", currencyConversionResponse.toString());
        
        return new CurrencyConversionResponse(currencyConversionResponse.getId(), from, to,
            currencyConversionResponse.getConversionMultiple(), quantity,
            quantity.multiply(currencyConversionResponse.getConversionMultiple()), currencyConversionResponse.getPort());
    }

}
