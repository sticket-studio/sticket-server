package com.ec.sticket.exceptions;

public class ModifyAuthorException extends RuntimeException {

    public ModifyAuthorException() {
        super();
    }

    public ModifyAuthorException(String message) {
        super(message);
    }

    public ModifyAuthorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModifyAuthorException(Throwable cause) {
        super(cause);
    }

    protected ModifyAuthorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
