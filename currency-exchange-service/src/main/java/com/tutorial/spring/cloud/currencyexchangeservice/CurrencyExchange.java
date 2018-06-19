package com.tutorial.spring.cloud.currencyexchangeservice;

import java.math.BigDecimal;

public class CurrencyExchange {

    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    
    public CurrencyExchange() {
        
    }
    
    public CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }
    
}
