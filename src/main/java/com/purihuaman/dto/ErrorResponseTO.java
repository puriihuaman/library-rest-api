package com.purihuaman.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Getter
@Setter
public class ErrorResponseTO {
    private Boolean hasError = true;
    private String title;
    private String message;
    private Integer statusCode;
    private Map<String, String> reasons;
    private LocalDateTime timeStamp = LocalDateTime.now();
}
