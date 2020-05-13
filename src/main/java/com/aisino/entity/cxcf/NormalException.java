package com.aisino.entity.cxcf;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @program: gyxxdz
 * @description: 通用异常
 * @author: Bruse Queen
 * @create: 2019-03-01 11:33
 **/
public class NormalException extends RuntimeException {
    public NormalException() {
        super();
    }

    public NormalException(String message) {
        super(message);
    }

    public NormalException(String message, Throwable cause) {
        super(message, cause);
    }

    public NormalException(Throwable cause) {
        super(cause);
    }

    protected NormalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
