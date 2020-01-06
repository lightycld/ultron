package com.ultron.boss.service.impl

import com.ultron.boss.domain.entity.Administrator
import com.ultron.boss.domain.vo.AdministratorVO
import com.ultron.boss.exception.BossBizException
import com.ultron.boss.mapper.AdministratorMapper
import com.ultron.boss.service.AdministratorService
import groovy.transform.CompileStatic
import org.apache.commons.codec.digest.DigestUtils
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Create By yangwei
 * Create at 2020/01/06 11:26
 * Description:
 */
@CompileStatic
@Service
class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    AdministratorMapper administratorMapper

    @Override
    void save(AdministratorVO adminVO) {
        if (adminVO.password != adminVO.passwordConfirm) {
            throw new BossBizException("password not confirmed")
        }

        if(this.exist(adminVO.phone, "phone")) {
            throw new BossBizException("phone was in used")
        }

        if(this.exist(adminVO.username, "username")) {
            throw new BossBizException("username was in used")
        }

        String saltKey = ObjectId.get().toHexString()
        String pwdSha256 = DigestUtils.sha256Hex(adminVO.password + saltKey)
        adminVO.password = pwdSha256
        adminVO.saltKey = saltKey
        administratorMapper.insert(adminVO)
    }

    @Override
    void modifyPassword(Long id, String pwd, String pwdConfirm) {

    }

    @Override
    void resetPassword(Long id) {

    }

    @Override
    List<Administrator> listAll() {
        return null
    }

    @Override
    void update(Administrator administrator) {

    }

    @Override
    void login(String phone, String password) {
        Administrator admin = administratorMapper.findByPhone(phone)
        if (!admin) {
            throw new BossBizException("Admin not found")
        }

        String passwordGen = DigestUtils.sha256Hex(password + admin.saltKey)
        if (password != passwordGen) {
            throw new BossBizException("Invalid password")
        }
    }

    @Override
    boolean exist(String value, String type) {
        if (type == 'username') {
            return administratorMapper.usernameExist(value) > 0
        } else {
            return administratorMapper.phoneExist(value) > 0
        }
    }
}
