package com.ultron.boss.domain.vo

import com.ultron.boss.enums.SexEnum
import groovy.transform.CompileStatic

import java.sql.Timestamp

/**
 * Create By yangwei
 * Create at 2020/01/08 21:24
 * Description: 
 */
@CompileStatic
class UserVO {

    Long id

    String phone

    String email

    String weChatId

    String creditNo

    String area

    String birthday

    SexEnum sex

    String createUser

    Timestamp createTime

    boolean creditVerified

}
