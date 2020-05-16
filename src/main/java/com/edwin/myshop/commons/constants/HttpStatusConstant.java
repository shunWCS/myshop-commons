package com.edwin.myshop.commons.constants;

public enum HttpStatusConstant {

    BAD_GETWAY(502,"没找到上游方法");

    private int status;
    private String message;

    private HttpStatusConstant(int status,String message){
        this.status=status;
        this.message=message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
