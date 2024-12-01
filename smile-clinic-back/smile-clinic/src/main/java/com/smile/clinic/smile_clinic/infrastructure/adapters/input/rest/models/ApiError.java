package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ApiError implements Serializable {

    private String backendMessage;

    private String message;

    private String url;

    private String method;

    private LocalDateTime timestamp;
}
