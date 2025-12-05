package top.natsuki.domain.enums;

import lombok.Getter;

/**
 * @author nat4u
 * @date 2025/11/30  20:58
 */
@Getter
public enum AppHttpCodeEnum {
    SUCCESS(200,"操作成功"),
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"没有操作权限"),
    SYSTEM_ERROR(500,"系统内部错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONE_EXIST(502,"手机号已存在"), EMAIL_EXIST(503,"邮箱已存在"),
    REQUIRED_USERNAME(504,"用户名不能为空"),
    LOGIN_ERROR(505,"用户名或密码错误");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMsg) {
        this.code = code;
        this.msg = errorMsg;
    }

}
