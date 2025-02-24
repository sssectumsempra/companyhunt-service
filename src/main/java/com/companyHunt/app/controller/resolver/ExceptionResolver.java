package com.companyHunt.app.controller.resolver;

import com.companyHunt.app.exception.CompanyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class ExceptionResolver {

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception e, WebRequest request) {
        if (e instanceof CompanyNotFoundException) {
            log.error("Error occurred on {} message: {}",
                    request.getContextPath(),
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
        log.error("Internal server error", e);
        return ResponseEntity.badRequest().body("Internal server error");
    }
}
