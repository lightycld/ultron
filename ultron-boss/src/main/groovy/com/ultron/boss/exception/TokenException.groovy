package com.ultron.boss.exception

import groovy.transform.CompileStatic

/**
 * Create By yangwei
 * Create at 2020/01/06 11:49
 * Description: 
 */
@CompileStatic
class TokenException extends RuntimeException {

    String message

    TokenException() {}

    TokenException(String message) {
        super(message)
        this.message = message
    }
}
