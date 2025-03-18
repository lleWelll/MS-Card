package org.example.mscard.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record RestExceptionResponse(Integer httpStatusCode, String exception, String message, LocalDateTime timestamp, String path) {

}
