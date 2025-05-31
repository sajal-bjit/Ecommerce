package com.group3.ECommerce.categorize.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {

    private String message;
    private T data;
    private int statusCode;
    private String status;

    public static <T> ResponseDTO<T> success(String message, T data) {
        return ResponseDTO.<T>builder()
                .message(message)
                .data(data)
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    public static <T> ResponseDTO<T> error(String message, HttpStatus httpStatus) {
        return ResponseDTO.<T>builder()
                .message(message)
                .data(null)
                .statusCode(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .build();
    }
}
