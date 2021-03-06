package com.ultron.boss.domain.entity

import com.ultron.boss.domain.vo.Admin
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

    String createUser

    Timestamp createTime

    boolean enabled

    String saltKey

    Admin toAdminVO() {
        return new Admin(id: id, username: username, phone: phone, email: email)
    }

}
