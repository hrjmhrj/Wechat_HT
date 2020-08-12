package com.aisino.entity.cxcf;

public class YiChuLiException extends RuntimeException {
    public YiChuLiException() {
        super();
    }

    public YiChuLiException(String message) {
        super(message);
    }

    public YiChuLiException(String message, Throwable cause) {
        super(message, cause);
    }

    public YiChuLiException(Throwable cause) {
        super(cause);
    }

    protected YiChuLiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
