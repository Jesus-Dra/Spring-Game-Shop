package com.ShopGameBD.ShopGamePostgre.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameNotFound.class)
    public ResponseEntity<ApiError> handleGameNotFound(GameNotFound ex, HttpServletRequest request){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.
                value(), "Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CategoryNotFound.class)
    public  ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFound ex, HttpServletRequest request){
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.
                value(),"Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(GameAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleGameAlreadyExistsException
            (GameAlreadyExistsException ex, HttpServletRequest request){

        ApiError error = new ApiError(HttpStatus.CONFLICT.
                value(), "Conflict", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CategoryAlreadyExistException.class)
    public ResponseEntity<ApiError> handleCategoryAlreadyExistsException
            (CategoryAlreadyExistException ex, HttpServletRequest request){
        ApiError error = new ApiError(HttpStatus.CONFLICT
                .value(), "Conflict", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleIntegrity(
            DataIntegrityViolationException ex, HttpServletRequest request
    ){
        ApiError apiError = new ApiError(HttpStatus.CONFLICT.
                value(),"Conflict","Ya existe un juego con ese nombre", request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }
}
