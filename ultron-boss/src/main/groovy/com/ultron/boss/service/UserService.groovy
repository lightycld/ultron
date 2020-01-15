package com.ultron.boss.service

import com.ultron.boss.domain.CreditInfo
import com.ultron.boss.domain.req.UserRegisterReq
import com.ultron.boss.domain.vo.UserVO
import groovy.transform.CompileStatic

/**
 * Create By yangwei
 * Create at 2020/01/08 21:18
 * Description: 
 */
@CompileStatic
interface UserService {

    CreditInfo registerUser(UserRegisterReq userRegisterReq)

    List<UserVO> listByCondition(String username, String creditNo, String phone)
}
