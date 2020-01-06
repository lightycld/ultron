package com.ultron.boss.controller

import com.ultron.boss.domain.ResponseBean
import com.ultron.boss.domain.req.LoginReq
import com.ultron.boss.domain.vo.AdministratorVO
import com.ultron.boss.service.AdministratorService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Create By yangwei
 * Create at 2020/01/06 10:51
 * Description:
 */

@Slf4j
@Validated
@CompileStatic
@RestController
class AdministratorController {

    @Autowired
    AdministratorService administratorService

    @PostMapping("/login")
    ResponseBean login(@RequestBody LoginReq loginReq) {
        try {
            administratorService.login(loginReq.phone, loginReq.password)
//            // setup session
//            Session session = sessionRepository.createSession()
//            session.setAttribute("user_id", loginReq.phone)
//            session.setAttribute("login_time", System.currentTimeMillis())
//            sessionRepository.save(session)
            ResponseBean.success()
        } catch (Exception e) {
            log.info("[Login] login error with ${e.message}")
            ResponseBean.error(e.message)
        }
    }

    @PostMapping("/logout/{phone}")
    ResponseBean logout(@PathVariable("phone") String phone) {
//        sessionRepository.deleteById(phone)
        ResponseBean.success()
    }

    @PostMapping("/admin")
    ResponseBean save(@RequestBody AdministratorVO administratorVO) {
        try {
            administratorService.save(administratorVO)
            ResponseBean.success()
        } catch (Exception e) {
            log.info("[Login] create administrator error. ${e.message}")
            ResponseBean.error(e.message)
        }
    }

    @GetMapping("/exist")
    ResponseBean exist(@RequestParam("value") String value, @RequestParam("type") String type) {
        boolean exist = administratorService.exist(value, type)
        ResponseBean.success(["exist": exist])
    }

}
