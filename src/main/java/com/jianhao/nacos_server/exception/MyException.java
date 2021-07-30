package com.jianhao.nacos_server.exception;

import com.jianhao.nacos_server.enity.resultEnum;


public class MyException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private resultEnum resultEnum;

    public MyException(resultEnum resultEnum){
        this.resultEnum = resultEnum;
    }

    public MyException(){
        
    }

    public resultEnum getResultEnum(){
        return resultEnum;
    }

    public void setResultEnum(resultEnum resultEnum){
        this.resultEnum = resultEnum;
    }

}