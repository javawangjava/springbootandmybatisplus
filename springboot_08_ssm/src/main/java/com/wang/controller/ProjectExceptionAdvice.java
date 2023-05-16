package com.wang.controller;

import com.wang.exception.BusinessException;
import com.wang.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
 *
 * //@RestControllerAdvice注解：Rest风格类注解，此注解自带@ResponseBody注解与@Component注解，具备对应的功能
 * //@ExceptionHandler注解：方法注解，设置指定异常的处理方案，功能等同于控制器方法，出现异常后终止原始控制器执行，并转入当前方法执行。此类方法可以根据处理的异常不同，制作多个方法分别处理对应的异常。
 *
 * 项目各个层级均可能出现异常：
 * 出现异常现象的常见位置与常见诱因如下：
 *       1.框架内部抛出的异常：因使用不合规导致；
 *       2.数据层抛出的异常：因外部服务器故障导致（例如：服务器访问超时）；
 *       3.业务层抛出的异常：因业务逻辑书写错误导致（例如：遍历业务书写操作，导致索引异常等）；
 *       4.表现层抛出的异常：因数据收集、校验等规则导致（例如：不匹配的数据类型间导致异常）；
 *       5.工具类抛出的异常：因工具类书写不严谨不够健壮导致（例如：必要释放的连接长期未释放等）；
 *
 * 将异常统一处理：把全部异常放在Controller层统一处理，在异常通知类中拦截并处理异常。
 * 项目异常分类及其处理方案：
 *     【项目业务异常（BusinessException）】：
 *          1.规范的用户行为产生的异常；
 *          2.不规范的用户行为操作产生的异常。
 *          【处理方案】：发送对应消息传递给用户，提醒规范操作。
 *
 *     【系统异常（SystemException）】：
 *          1.项目运行过程中可预计且无法避免的异常；
 *          【处理方案】：发送固定消息传递给用户，安抚用户；发送特定消息给运维人员，提醒维护；记录日志；
 *
 *     【其他异常（Exception）】：
 *          1.编程人员未预期到的异常。
 *          【处理方案】：发送固定消息传递给用户，安抚用户；发送特定消息给编程人员，提醒维护（纳入预期范围内）；记录日志。
 *
 *
 * */

//

// 在异常通知类中拦截并处理异常
//@RestControllerAdvice：Rest风格类注解，此注解自带@ResponseBody注解与@Component注解，具备对应的功能
//@ExceptionHandler：方法注解，设置指定异常的处理方案，功能等同于控制器方法，出现异常后终止原始控制器执行，并转入当前方法执行。此类方法可以根据处理的异常不同，制作多个方法分别处理对应的异常。
// Result类有三个实例成员变量：Object data,Integer code,String msg。

@RestControllerAdvice
public class ProjectExceptionAdvice {

    //@ExceptionHandler用于设置当前处理器类对应的异常类型【系统异常（SystemException）】
    // 指定该方法处理系统异常（SystemException）
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException ex) {
        // 模拟异常处理，处理之后返回处理异常的结果
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    // 【项目业务异常（BusinessException）】
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex) {
        return new Result(ex.getCode(), null, ex.getMessage());
    }

    //【其他异常（Exception）】。保留对Exception类型的异常处理，用于处理非预期的异常
    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception ex) {
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员,ex对象发送给开发人员
        return new Result(Code.SYSTEM_UNKNOW_ERR, null, "系统繁忙，请稍后再试！");
    }

}
