package com.jianhao.nacos_server.enity;



public enum resultEnum {
    SUCCESS(400,"SUCCESS"),
    FAIL(500,"FAIL"),
    UNKNOWN_EXCEPTION(501,"UBKNOWN_EXCEPTION");


    private int code;
    private String msg;


    private resultEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}