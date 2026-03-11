package com.rewards.openrewards.shared;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiDefaultResponse<Void>> handleBusiness(BusinessException ex) {
        ApiDefaultResponse<Void> body = ApiDefaultResponse.businessError(
                ex.getStatus().value(),
                ex.getCode(),
                ex.getMessage()
        );

        return ResponseEntity.status(ex.getStatus()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDefaultResponse<Void>> handleValidation(MethodArgumentNotValidException ex) {
        List<ApiDefaultResponse.ErrorDetail> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ApiDefaultResponse.ErrorDetail(
                        err.getField(),
                        err.getDefaultMessage()
                ))
                .toList();

        ApiDefaultResponse<Void> body = ApiDefaultResponse.error(
                400,
                "Validation error",
                errors
        );

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiDefaultResponse<Void>> handleGeneric(Exception ex) {
        ApiDefaultResponse<Void> body = ApiDefaultResponse.internalError(
                500,
                "INTERNAL_ERROR",
                "Unexpected error."
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiDefaultResponse<Void>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        if (ex.getMessage() != null && ex.getMessage().contains("idempotency_key")) {
            ApiDefaultResponse<Void> body = ApiDefaultResponse.businessError(
                    HttpStatus.CONFLICT.value(),
                    "TRANSACTION_ALREADY_PROCESSED",
                    "Esta transação já foi processada anteriormente."
            );

            return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
        }

        ApiDefaultResponse<Void> body = ApiDefaultResponse.businessError(
                HttpStatus.BAD_REQUEST.value(),
                "DATABASE_ERROR",
                "Erro de integridade nos dados."
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

}

