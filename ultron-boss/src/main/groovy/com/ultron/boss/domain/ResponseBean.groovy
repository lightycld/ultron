package com.ultron.boss.domain

import groovy.transform.CompileStatic

/**
 * Create By yangwei
 * Create at 2019/08/02 13:54
 * Description:
 */
@CompileStatic
class ResponseBean<T> {

    Code code

    String message = code.message

    T data

    ResponseBean(Code code) {
        this.code = code
        this.message = code.message
    }

    ResponseBean(Code code, T data) {
        this.code = code
        this.message = code.message
        this.data = data
    }

    ResponseBean(Code code, String message) {
        this.code = code
        this.message = message
    }

    static <T> ResponseBean success(T data) {
        new ResponseBean(Code.SUCCESS, data)
    }

    static ResponseBean success() {
        new ResponseBean(Code.SUCCESS)
    }

    static ResponseBean failed() {
        return new ResponseBean(Code.FAILED)
    }

    static ResponseBean failed(String message) {
        return new ResponseBean(Code.FAILED, message)
    }


    static ResponseBean error() {
        return new ResponseBean(Code.ERROR)
    }

    static ResponseBean error(String message) {
        return new ResponseBean(Code.ERROR, message)
    }

    enum Code {
        SUCCESS("SUCCESS"),

        FAILED("FAILED"),

        ERROR("ERROR")

        String message

        Code(String message) {
            this.message = message
        }
    }
}
