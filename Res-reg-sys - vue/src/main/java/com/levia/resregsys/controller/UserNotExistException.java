package com.levia.resregsys.controller;

public class UserNotExistException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String msg;
    public UserNotExistException(String msg) {
        super("user not exist");
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
