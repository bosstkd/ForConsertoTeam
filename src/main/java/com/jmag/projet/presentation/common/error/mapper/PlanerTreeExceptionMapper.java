package com.jmag.projet.presentation.common.error.mapper;
import com.jmag.projet.domain.exceptions.PlanerTreeBadRequestException;
import com.jmag.projet.domain.exceptions.PlanerTreeNotFoundException;
import com.jmag.projet.domain.validation.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class PlanerTreeExceptionMapper {

    @ExceptionHandler(PlanerTreeNotFoundException.class)
    public ResponseEntity<ErrorMessage> PlanerTreeNotFoundException(PlanerTreeNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                ex.getDetails());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlanerTreeBadRequestException.class)
    public ResponseEntity<ErrorMessage> PlanerTreeServerException(PlanerTreeBadRequestException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDetails());

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                null);

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
