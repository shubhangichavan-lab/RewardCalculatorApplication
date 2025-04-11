package com.retail.reward.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
public class ErrorResponse {

    private String message;
    private int statusCode;
    private long date;

    public ErrorResponse(String message,int statusCode, long date)
    {
        super();

        this.message = message;
        this.statusCode= statusCode;
        this.date= date;
    }
}
