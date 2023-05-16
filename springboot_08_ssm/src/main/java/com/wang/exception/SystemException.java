package com.wang.exception;

// 自定义系统级异常类【自定义异常处理器】，用于封装异常信息，对异常进行分类
// 设定异常的成员变量和成员方法，并设定构造器，当出现业务异常时，创建系统异常对象，可以上抛也可以捕获处理。

//自定义项目系统级异常继承RuntimeException

public class SystemException extends RuntimeException{

    // 根据需要设定构造器，用于在创建对象的时候给异常对象来传参
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
