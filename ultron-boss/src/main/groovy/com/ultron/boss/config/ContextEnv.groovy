package com.ultron.boss.config

import com.ultron.boss.domain.vo.Admin
import groovy.transform.CompileStatic

/**
 * Create By yangwei
 * Create at 2020/01/19 15:57
 * Description: 
 */
@CompileStatic
class ContextEnv {

    private static ThreadLocal<Admin> currentAdmin = new ThreadLocal<>()

    static void set(Admin admin) {
        currentAdmin.set(admin)
    }

    static Admin getAdmin(){
        currentAdmin.get()
    }

    static String getAdminName() {
        currentAdmin.get().getUsername()
    }
}
