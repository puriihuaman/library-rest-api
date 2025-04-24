package com.purihuaman.exception;

import com.purihuaman.enums.APIError;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class APIRequestException extends RuntimeException {
    private final Boolean hasError = true;
    private final String title;
    private final String message;
    private final HttpStatus httpStatus;
    private Map<String, String> reasons;
    private APIError error;
    
    
    public APIRequestException(final APIError error) {
        super();
        this.title = error.getTitle();
        this.message = error.getMessage();
        this.httpStatus = error.getStatus();
        this.error = error;
    }
    
    public APIRequestException(
        final String title,
        final String message,
        final HttpStatus httpStatus,
        final Map<String, String> reasons
    )
    {
        super();
        this.title = title;
        this.message = message;
        this.httpStatus = httpStatus;
        this.reasons = reasons;
    }
    
    public APIRequestException(final APIError error, final String title, final String message) {
        super();
        this.title = title;
        this.message = message;
        this.httpStatus = error.getStatus();
    }
}
