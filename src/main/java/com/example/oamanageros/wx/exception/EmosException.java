package com.example.oamanageros.wx.exception;

import lombok.Data;

/**
 * @author 笑的心酸 - Red4Lion - mnmnmssd
 * @date 2021.12.20
 * 自定义异常类  方便前端调试
 * 继承RuntimeException 既可以自动处理 也可以手动处理
 */

@Data
public class EmosException extends RuntimeException {
    private String msg;
    private int code = 500;

    public EmosException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public EmosException(Throwable cause, String msg) {
        super(msg, cause);
        this.msg = msg;
    }

    public EmosException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public EmosException(Throwable cause, int code, String msg) {
        super(msg, cause);
        this.msg = msg;
        this.code = code;
    }
}
