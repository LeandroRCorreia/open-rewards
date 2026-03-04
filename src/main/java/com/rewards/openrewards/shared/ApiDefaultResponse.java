package com.rewards.openrewards.shared;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiDefaultResponse<T> {

    @Builder.Default
    private Boolean success = true;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);

    private Integer status;

    private String message;

    private String errorCode;

    @Builder.Default
    private String traceId = MDC.get("traceId");

    private T data;

    private List<ErrorDetail> errors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorDetail {
        private String field;
        private String message;
    }

    public static <T> ApiDefaultResponse<T> success(T data) {
        return ApiDefaultResponse.<T>builder()
                .success(true)
                .status(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> ApiDefaultResponse<T> created(T data) {
        return ApiDefaultResponse.<T>builder()
                .status(201)
                .message("Created successfully")
                .data(data)
                .build();
    }

    public static ApiDefaultResponse<Void> businessError(Integer status, String code, String message) {
        return ApiDefaultResponse.<Void>builder()
                .success(false)
                .status(status)
                .errorCode(code)
                .message(message)
                .build();
    }

    public static ApiDefaultResponse<Void> error(Integer status, String message, List<ErrorDetail> errors) {
        return ApiDefaultResponse.<Void>builder()
                .status(status)
                .message(message)
                .errors(errors)
                .build();
    }

    public static ApiDefaultResponse<Void> internalError(Integer status, String code, String message) {
        return ApiDefaultResponse.<Void>builder()
                .success(false)
                .status(status)
                .errorCode(code)
                .message(message)
                .build();
    }

}
