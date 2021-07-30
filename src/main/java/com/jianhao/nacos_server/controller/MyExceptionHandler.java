package com.jianhao.nacos_server.controller;

import java.util.stream.Collectors;

import com.jianhao.nacos_server.enity.Result;
import com.jianhao.nacos_server.exception.MyException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice(annotations = RestController.class)
public class MyExceptionHandler {
    
    @ExceptionHandler(MyException.class)
    public Result<String> test(){
        return new Result<String>().setCode(400).setMsg("测试异常捕捉");
    }


    // 测试异常
    @ExceptionHandler(Exception.class)
    public Result<String>test1(Exception exception){
        System.out.println("获取到的异常"+exception.toString());
        if (exception instanceof Exception) {
            return new Result<String>().setCode(400).setMsg(exception.getMessage());
        }
        return new Result<String>().setCode(200).setMsg("获取到的异常不是基类异常！");
    }


    // 数据校验不通过则抛出Validated异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> validatadException(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new Result<String>().setCode(500).setMsg(message);
    }
}