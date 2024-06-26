package com.jinius.architecture.clean.common;

import com.jinius.architecture.clean.apply.exception.ApplyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.status(500).body(new ErrorResponse("500", "에러가 발생했습니다."));
    }

    /**
     * 커스텀 예외 처리
     * @param e
     * @return
     */
    @ExceptionHandler(value = ApplyException.class)
    public ResponseEntity<ErrorResponse> handlePointServiceException(ApplyException e) {
        return ResponseEntity.status(400).body(new ErrorResponse("400", e.getMessage()));
    }
}
