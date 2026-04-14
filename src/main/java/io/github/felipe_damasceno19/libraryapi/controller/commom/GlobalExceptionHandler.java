package io.github.felipe_damasceno19.libraryapi.controller.commom;

import io.github.felipe_damasceno19.libraryapi.controller.dto.FieldErrorDTO;
import io.github.felipe_damasceno19.libraryapi.controller.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<FieldErrorDTO> errorsList = fieldErrors.stream()
                .map(fe -> new FieldErrorDTO(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ResponseError(HttpStatus.UNPROCESSABLE_ENTITY.value()
                , "Validation Error"
                , errorsList);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseError handleAcessDeniedException(AccessDeniedException e){
        return new ResponseError(HttpStatus.FORBIDDEN.value(),
                "Acess denied",
                List.of());
    }
}
