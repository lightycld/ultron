package com.ultron.boss.domain

import com.ultron.boss.enums.SexEnum
import groovy.transform.CompileStatic

/**
 * Create By yangwei
 * Create at 2020/01/06 19:35
 * Description: 
 */
@CompileStatic
class CreditInfo {

    String province

    String city

    String district

    int age

    SexEnum sex

    String birthday
}
