package com.ultron.boss.util

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.util.StringUtils

/**
 * Create By yangwei
 * Create at 2020/01/06 19:51
 * Description: 
 */
@CompileStatic
class JSONUtil {

    private static ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.newInstance().build()

    static <T> String toString(T t) {
        if (t == null) {
            return null
        }
        return t instanceof String ? (String) t : objectMapper.writeValueAsString(t)
    }

    static <T> T parse(String str, Class<T> c) {
        if (StringUtils.isEmpty(str) || c == null) {
            return null
        }
        return c == String.class ? (T) str : objectMapper.readValue(str, c)
    }

}
