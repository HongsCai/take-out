package com.hongs.skyserver.handler;

import com.hongs.skycommon.constant.MessageConstant;
import com.hongs.skycommon.exception.BaseException;
import com.hongs.skyserver.common.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    public BaseResponse handleException(BaseException e) {
        log.error("异常信息：{}", e.getMessage());
        return BaseResponse.error(e.getMessage());
    }

    /**
     * 处理SQL异常
     * @param e
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public BaseResponse handleException(SQLIntegrityConstraintViolationException e) {
        String message = e.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String username = split[2];
            return BaseResponse.error(username + MessageConstant.ALREAD_EXISTS);
        } else {
            return BaseResponse.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
}
