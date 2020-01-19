package com.ultron.boss.config

import groovy.transform.CompileStatic
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * Create By yangwei
 * Create at 2020/01/19 15:21
 * Description: 
 */
@ConfigurationProperties(prefix = "jwt-token")
@Component
@CompileStatic
class JwtConfig {

    String serverId

    String serverName

    String secretKey

    long expireSeconds
}
