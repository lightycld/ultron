package com.ultron.boss.controller

import com.ultron.boss.domain.CreditInfo
import com.ultron.boss.domain.ResponseBean
import com.ultron.boss.domain.req.UserRegisterReq
import com.ultron.boss.domain.vo.UserVO
import com.ultron.boss.service.UserService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Create By yangwei
 * Create at 2020/01/08 21:10
 * Description: 
 */

@Slf4j
@RestController
@CompileStatic
class UserController {

    @Autowired
    UserService userService

    @PostMapping("/user")
    ResponseBean register(@RequestBody UserRegisterReq registerReq) {
        CreditInfo creditInfo = userService.registerUser(registerReq)
        ResponseBean.success(creditInfo)
    }

    @GetMapping("/users")
    ResponseBean list(@RequestParam("username") String username,
                      @RequestParam("creditNo") String creditNo,
                      @RequestParam("phone") String phone) {
        List<UserVO> userVos = userService.listByCondition(username, creditNo, phone)
        ResponseBean.success(userVos)
    }


}
