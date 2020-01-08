package com.ultron.boss.config

import groovy.transform.CompileStatic
import org.springframework.context.annotation.Bean
import org.springframework.session.MapSessionRepository
import org.springframework.session.Session
import org.springframework.session.SessionRepository
import org.springframework.session.web.http.SessionRepositoryFilter
/**
 * Create By yangwei11
 * Create at 2020/01/06 17:11
 * Description: 
 */
//@Configuration
@CompileStatic
class SessionConfig //extends AbstractHttpSessionApplicationInitializer
{

    @Bean
    MapSessionRepository sessionRepository(){
        return new MapSessionRepository(new HashMap<String, Session>())
    }

    @Bean
    SessionRepositoryFilter sessionRepositoryFilter(SessionRepository sessionRepository) {
        return new SessionRepositoryFilter<Session>(sessionRepository)
    }
}
