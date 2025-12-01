package com.hongs.skyserver.common;


import lombok.Data;
import java.io.Serializable;


/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class BaseResponse<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public static <T> BaseResponse<T> success() {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.code = 1;
        return baseResponse;
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.data = data;
        baseResponse.code = 1;
        return baseResponse;
    }

    public static <T> BaseResponse<T> error(String msg) {
        BaseResponse<T> baseResponse = new BaseResponse<>();
        baseResponse.code = 0;
        baseResponse.msg = msg;
        return baseResponse;
    }
}
