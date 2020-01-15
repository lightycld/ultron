package com.ultron.boss.service.impl

import com.ultron.boss.domain.CreditInfo
import com.ultron.boss.domain.entity.User
import com.ultron.boss.domain.req.UserRegisterReq
import com.ultron.boss.domain.vo.UserVO
import com.ultron.boss.exception.BossBizException
import com.ultron.boss.mapper.UserMapper
import com.ultron.boss.service.UserService
import com.ultron.boss.util.CreditUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/**
 * Create By yangwei
 * Create at 2020/01/08 21:19
 * Description: 
 */

@Slf4j
@Service
@CompileStatic
class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper

    @Override
    CreditInfo registerUser(UserRegisterReq req) {
        int p = userMapper.isPhoneExist(req.phone)
        if (p) {
            throw new BossBizException("Phone was exist.")
        }
        int c = userMapper.isCreditNoExist(req.creditNo)
        if (c) {
            throw new BossBizException("Credit card no was exist.")
        }

        User u = new User(phone: req.phone,
                username: req.username,
                creditNo: req.creditNo,
                weChatId: req.weChatId
        )

        CreditInfo creditInfo = CreditUtil.getInfo(req.creditNo)
        u.setUpCredit(creditInfo)
        userMapper.insert(u)
        return creditInfo
    }

    List<UserVO> listByCondition(String username, String creditNo, String phone) {
        return null
    }
}
