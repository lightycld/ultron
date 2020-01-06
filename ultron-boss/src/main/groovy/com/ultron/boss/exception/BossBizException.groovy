package com.ultron.boss.exception

import groovy.transform.CompileStatic

/**
 * Create By yangwei
 * Create at 2020/01/06 11:49
 * Description: 
 */
@CompileStatic
class BossBizException extends RuntimeException {

    String message

    BossBizException (String message) {
        super(message)
    }
}
