package com.ultron.boss.domain.entity

import com.ultron.boss.domain.CreditInfo
import com.ultron.boss.enums.SexEnum
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

    String area

    String birthday

    SexEnum sex

    Integer age

    boolean creditVerified

    String createUser

    Timestamp createTime

    Timestamp updateTime

    boolean verified

    void setUpCredit(CreditInfo creditInfo) {
        this.area = creditInfo.area
        this.birthday = creditInfo.birthday
        this.sex = creditInfo.sex
        this.age = creditInfo.age
        this.creditVerified = creditInfo.verified
    }
}
