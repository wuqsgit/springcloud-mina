package com.springcloud.minabean.common;

import com.springcloud.minabean.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ResponseContent {
    String errorCode;
    String errorMsg;
    Object result;

    Map<String, Object> outerResults;

    public ResponseContent(){
        this(ResponseStatus.STATUS_0_SUCCESS.getCode(), "success");
    }

    public ResponseContent(String code, String msg) {
        this.errorCode = code;
        this.errorMsg = msg;
    }

}