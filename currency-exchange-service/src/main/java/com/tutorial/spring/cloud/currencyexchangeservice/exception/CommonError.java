package com.tutorial.spring.cloud.currencyexchangeservice.exception;

import java.util.Date;

public class CommonError {
    
    private String status;
    private Date timestamp;
    private String message;
    
    public CommonError(String status, Date timestamp, String message) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
    
    

}
