package com.ec.sticket.util;

import lombok.Data;
import lombok.Getter;

@Data
public class ApiMessage {
    private final int code;
    private final String message;

    public ApiMessage(Status status) {
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public static ApiMessage getSuccessMessage(){
        return new ApiMessage(Status.SUCCESS);
    }

    public static ApiMessage getFailMessage(){
        return new ApiMessage(Status.FAIL);
    }

    public static ApiMessage getUnauthorizationMessage(){
        return new ApiMessage(Status.UNAUTHORIZATION);
    }

    @Getter
    public enum Status{
        SUCCESS(200, "Success"), UNAUTHORIZATION(403, "You don't have authorization")
        , FAIL(403, "Fail");

        private int code;
        private String message;

        Status(int code, String message){
            this.code = code;
            this.message = message;
        }
    }
}
