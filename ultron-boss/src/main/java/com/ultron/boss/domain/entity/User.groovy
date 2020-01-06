package com.ultron.boss.domain.entity

import groovy.transform.CompileStatic

import java.sql.Timestamp

/**
 * Create By yangwei
 * Create at 2020/01/06 10:24
 * Description:
 */

@CompileStatic
class User {

    Long id

    String username

    String phone

    String email

    String weChatId

    String creditNo

    String createUser

    Timestamp createTime

    Timestamp updateTime

    boolean verified

}
