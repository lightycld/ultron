package com.ultron.boss.domain.req

import groovy.transform.CompileStatic

import javax.validation.constraints.NotBlank

/**
 * Create By yangwei
 * Create at 2020/01/06 12:05
 * Description: 
 */
@CompileStatic
class LoginReq {

    @NotBlank
    String phone

    @NotBlank
    String password
}
