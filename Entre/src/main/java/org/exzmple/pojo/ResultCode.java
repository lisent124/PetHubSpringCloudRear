package org.exzmple.pojo;


/**
 * 错误状态码
 *
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    SUCCESS_WITH_NO_DATA(230,"没有返回数据"),
    NO_REQUEST_DATA(460, "没有数据"),
    USER_IS_NOT_EXIST(461, "用户不存在"),
    USER_REGISTERED(462, "用户已注册"),
    PASSWD_NOT_THE_SAME(463, "前后密码不一致"),
    PASSWD_LEN_ERROR(467, "密码长度应大于8 小于64"),
    ID_VALIDATION_FAILED(464, "cookie验证失败"),
    ID_MISSING(465, "找不到ID"),
    ERROR_REQUEST(466, "错误的请求"),
    NO_MORE_BLOGS(467,"没有更多了！"),
    DATABASE_ERROR(530,"数据库出错了"),
    SERVICE_ERROR(540,"内部出错了"),

    ;

    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
