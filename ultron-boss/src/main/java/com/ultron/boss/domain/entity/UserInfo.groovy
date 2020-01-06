package com.ultron.boss.domain.entity

import com.ultron.boss.enums.SexEnum

import java.sql.Timestamp 
/**
 * Create By yangwei
 * Create at 2020/01/06 10:24
 * Description:
 */
class UserInfo {

    Long id

    Long userId

    String username

    String webChatId

    String phone

    String email

    String province

    String city

    String district

    String birthday

    SexEnum sex

    String createUser

    Timestamp createTime

}
