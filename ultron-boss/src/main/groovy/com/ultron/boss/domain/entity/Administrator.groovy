package com.ultron.boss.domain.entity

import groovy.transform.CompileStatic

import java.sql.Timestamp
/**
 * Create By yangwei
 * Create at 2020/01/06 10:49
 * Description:
 */

@CompileStatic
class Administrator {

    Long id

    String username

    String email

    String phone

    String password

    Timestamp createTime

    boolean status

    String saltKey

}
