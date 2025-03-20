package com.vanh.urboxapiintegration.dto;

import lombok.Data;

@Data
public class UrboxResponse<T> {
    private int done;
    private String msg;
    private String microtime;
    private int status;
    private T data;
}
