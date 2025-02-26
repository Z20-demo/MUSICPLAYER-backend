package com.nju.user.exception;

import com.nju.user.model.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = UserException.class)
    public ResultVO<String> handleAIExternalException(UserException e) {
        e.printStackTrace();
        return ResultVO.buildFailure(e.getMessage());
    }
}
