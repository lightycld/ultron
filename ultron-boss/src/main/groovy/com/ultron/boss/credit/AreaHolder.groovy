package com.ultron.boss.credit

import com.ultron.boss.util.JSONUtil
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.springframework.util.StreamUtils

import javax.annotation.PostConstruct
import java.nio.charset.StandardCharsets
/**
 * Create By yangwei
 * Create at 2020/01/06 19:45
 * Description: 
 */
@Slf4j
@Component
@CompileStatic
class AreaHolder {

    static Map<String, String> areaMap

    @PostConstruct
    static void init() {
        Resource resource = new DefaultResourceLoader().getResource("classpath:json/areacode.json")
        String codes = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8)
        areaMap = JSONUtil.parse(codes, HashMap)
        log.info("Load credit area code ${areaMap.size()}.")
    }

    static String getByCode(String code) {
        return areaMap.get(code)
    }
}
