package com.github.handler;

import com.github.dto.MessageDto;
import com.github.exception.GeneralRepositoryWebException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralRepositoryWebException.class)
    protected ResponseEntity<Object> handleClientError(GeneralRepositoryWebException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(MessageDto.builder()
                .message(ex.getMessage())
                .httpStatus(ex.getHttpStatus())
                .build());
    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<Object> handleFeignException(FeignException ex) {
        return ResponseEntity.status(ex.status()).body(MessageDto.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.resolve(ex.status()))
                .build());
    }
}

