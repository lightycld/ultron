package com.ultron.boss.service.impl


import com.ultron.boss.domain.entity.Administrator
import com.ultron.boss.domain.vo.AdministratorVO
import com.ultron.boss.exception.BossBizException
import com.ultron.boss.mapper.AdministratorMapper
import com.ultron.boss.service.AdministratorService
import com.ultron.boss.util.PasswordUtil
import groovy.transform.CompileStatic
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
        byte[] saltKey = PasswordUtil.createSalt()
        String pwdCom = PasswordUtil.createCredential(adminVO.password, saltKey)
        adminVO.password = pwdCom
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
        if(!admin) {
            throw new BossBizException("Admin not found")
        }

        String passwordGen = PasswordUtil.createCredential(password, admin.saltKey.bytes)
        if(password != passwordGen) {
            throw new BossBizException("Invalid password")
        }
    }
}
