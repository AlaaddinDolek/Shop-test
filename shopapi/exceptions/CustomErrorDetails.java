package com.shop.shopapi.exceptions;

import java.util.Date;

public class CustomErrorDetails {
    
    private Date timestamp;
    private String message;
    private String errordetails;
    
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getErrordetails() {
        return errordetails;
    }
    public void setErrordetails(String errordetails) {
        this.errordetails = errordetails;
    }
    public CustomErrorDetails(Date timestamp, String message, String errordetails) {
        this.timestamp = timestamp;
        this.message = message;
        this.errordetails = errordetails;
    }



    
}
