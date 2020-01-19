package com.ultron.boss.service


import com.ultron.boss.domain.entity.Administrator
import com.ultron.boss.domain.vo.Admin
import com.ultron.boss.domain.vo.AdministratorVO
import com.ultron.boss.enums.AdminUniqueEnum
import groovy.transform.CompileStatic
/**
 * Create By yangwei
 * Create at 2020/01/06 11:26
 * Description:
 */

@CompileStatic
interface AdministratorService {

    void save(AdministratorVO administratorVO)

    void modifyPassword(Long id, String pwd, String pwdConfirm)

    void resetPassword(Long id)

    List<Administrator> listAll()

    void update(Administrator administrator)

    Admin login(String phone, String password)

    boolean exist(String value, AdminUniqueEnum type)
}
