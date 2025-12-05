package top.natsuki.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import top.natsuki.domain.enums.AppHttpCodeEnum;

import java.io.Serializable;

/**
 * @author nat4u
 * @date 2025/11/30  20:50
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public ResponseResult() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg =  AppHttpCodeEnum.SUCCESS.getMsg();
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
    public static <T> ResponseResult<T> errorResult(Integer code, String msg) {
        ResponseResult<T> result = new ResponseResult<>();
        return result.error(code, msg);
    }

    public static <T> ResponseResult<T> okResult(Integer code, String msg) {
        ResponseResult<T> result = new ResponseResult<>();
        return result.ok(code, msg);
    }
    public static <T> ResponseResult<T> okResult(T data) {
        ResponseResult<T> result = setAppHttpCodeEnum(AppHttpCodeEnum.SUCCESS, AppHttpCodeEnum.SUCCESS.getMsg());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum appHttpCodeEnum) {
        return setAppHttpCodeEnum(appHttpCodeEnum, appHttpCodeEnum.getMsg());
    }

    public static <T> ResponseResult<T> errorResult(AppHttpCodeEnum appHttpCodeEnum, String msg) {
        return setAppHttpCodeEnum(appHttpCodeEnum, msg);
    }

    public static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum appHttpCodeEnum) {
        return okResult(appHttpCodeEnum.getCode(), appHttpCodeEnum.getMsg());
    }

    private static <T> ResponseResult<T> setAppHttpCodeEnum(AppHttpCodeEnum appHttpCodeEnum, String msg) {
        return okResult(appHttpCodeEnum.getCode(), msg);
    }

    public ResponseResult<T> error(Integer code, String msg){
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    public ResponseResult<T> error(Integer code, T data){
        this.setData(data);
        this.setCode(code);
        return this;
    }

    public ResponseResult<T> ok(Integer code, String msg){
        this.setCode(code);
        this.setMsg(msg);
        return this;
    }

    public ResponseResult<T> ok(Integer code, T data){
        this.setData(data);
        this.setCode(code);
        return this;
    }

}
