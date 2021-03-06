package com.ultron.boss.controller

import com.ultron.boss.config.JwtConfig
import com.ultron.boss.config.TokenHolder
import com.ultron.boss.domain.ResponseBean
import com.ultron.boss.domain.req.LoginReq
import com.ultron.boss.domain.vo.Admin
import com.ultron.boss.domain.vo.AdministratorVO
import com.ultron.boss.enums.AdminUniqueEnum
import com.ultron.boss.service.AdministratorService
import com.ultron.boss.util.CookieUtil
import com.ultron.boss.util.TokenUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

import javax.servlet.http.HttpServletResponse

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

    @Autowired
    JwtConfig jwtConfig

    @PostMapping("/login")
    ResponseBean login(@RequestBody LoginReq loginReq, HttpServletResponse response) {
        try {
            Admin admin = administratorService.login(loginReq.phone, loginReq.password)

            String token = TokenUtil.generate(jwtConfig, admin)
            CookieUtil.writeCookie(response, "_token", token, 3600 * 12, null)
            ResponseBean.success()
        } catch (Exception e) {
            log.info("[Login] login error with ${e.message}")
            ResponseBean.error(e.message)
        }
    }

    @PostMapping("/logout/{id}")
    ResponseBean logout(@PathVariable("id") long id) {
        TokenHolder.offline(id)
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
    ResponseBean exist(@RequestParam("value") String value, @RequestParam("type") AdminUniqueEnum uniqueType) {
        boolean exist = administratorService.exist(value, uniqueType)
        ResponseBean.success(["exist": exist])
    }

}
