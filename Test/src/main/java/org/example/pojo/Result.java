package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    private static <D> Result<D> build(int code, String message, D data) {
        return new Result<D>(code, message, data);
    }

    public static <D> Result<D> success() {
        return build(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), null);
    }

    public static <D> Result<D> success(String message) {
        return build(ResultCode.SUCCESS.getCode(), message, null);
    }

    public static <D> Result<D> success(String message, D data) {
        return build(200, message, data);
    }

    public static <D> Result<D> failure(ResultCode resultCode) {
        return build(resultCode.getCode(), resultCode.getMessage(), null);
    }

    public static <D> Result<D> failure(ResultCode resultCode, String message) {
        return build(resultCode.getCode(), message, null);
    }

    public static <D> Result<D> failure(ResultCode resultCode, D data) {
        return build(resultCode.getCode(), resultCode.getMessage(), data);
    }
}
