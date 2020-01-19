package com.ultron.boss.config

import com.ultron.boss.domain.entity.Administrator
import groovy.transform.CompileStatic
import org.springframework.stereotype.Component

import java.util.concurrent.ConcurrentHashMap

/**
 * Create By yangwei
 * Create at 2020/01/19 14:53
 * Description: 
 */
@Singleton
@Component
@CompileStatic
class TokenHolder {

    static Map<Long, Administrator> adminOnlineMap = new ConcurrentHashMap<>()

    static online(Administrator admin) {
        adminOnlineMap.put(admin.getId(), admin)
    }

    static offline(Long id) {
        adminOnlineMap.remove(id)
    }

    static isOnline(Long id) {
        adminOnlineMap.containsKey(id)
    }
}
