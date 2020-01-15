package com.ultron.boss.domain.req

import groovy.transform.CompileStatic

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 * Create By yangwei
 * Create at 2020/01/06 10:24
 * Description:
 */

@CompileStatic
class UserRegisterReq {

    @NotEmpty(message = "username can not be null")
    @Size(max = 10, min = 2, message = "username size between 2-10")
    String username

    @NotEmpty(message = "mobile phone number can not be null")
    @Size(max = 11, min = 11, message = "username size must be 11")
    String phone

    String email

    String weChatId

    @NotEmpty(message = "credit card no can not be null")
    @Size(max = 18, min = 18, message = "username size must be 18.")
    String creditNo

    boolean verified

}
