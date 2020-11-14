package com.zj.test.transaction.exception;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/13 15:36
 * @description:
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class TransactionTestException extends Exception{

    public TransactionTestException() {
    }

    public TransactionTestException(String message) {
        super(message);
    }

    public TransactionTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionTestException(Throwable cause) {
        super(cause);
    }

    public TransactionTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
