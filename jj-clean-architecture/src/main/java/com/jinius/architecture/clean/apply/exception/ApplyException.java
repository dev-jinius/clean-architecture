package com.jinius.architecture.clean.apply.exception;

import com.jinius.architecture.clean.common.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ApplyException extends RuntimeException {

    ApplyErrorType applyErrorType;

}
