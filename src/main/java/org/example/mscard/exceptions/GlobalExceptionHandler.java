package org.example.mscard.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.example.mscard.dto.ErrorDetails;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCardTypeException.class)
    public ErrorDetails handleInvalidCardTypeException(InvalidCardTypeException ex) {
        log.error("Ошибка типа карты: ", ex);
        return new ErrorDetails(400, ex.getMessage());
    }

    @ExceptionHandler(InvalidPaymentSystemException.class)
    public ErrorDetails handleInvalidPaymentSystemException(InvalidPaymentSystemException ex) {
        log.error("Ошибка платежной системы: ", ex);
        return new ErrorDetails(400, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorDetails handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Ошибка чтения тела запроса: ", ex);
        return new ErrorDetails(400, "Некорректный формат данных в запросе");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDetails handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Ошибка валидации данных: ", ex);
        return new ErrorDetails(400, "Ошибка валидации данных");
    }
    @ExceptionHandler(IOException.class)
    public ErrorDetails handleIOException(IOException ex) {
        log.error("Ошибка при обработке данных: ", ex);
        return new ErrorDetails(400, "Некорректный формат даты или данных в запросе");
    }
    @ExceptionHandler(Exception.class)
    public ErrorDetails globalExceptionHandler(Exception ex) {
        log.error("Произошла ошибка: ", ex);
        return new ErrorDetails(500, "Ошибка сервера: " + ex.getMessage());
    }
}

