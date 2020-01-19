package com.ultron.boss.util

import org.apache.commons.lang3.StringUtils

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Create By yangwei
 * Create at 2020/01/19 15:02
 * Description:
 */
class CookieUtil {

    static void writeCookie(HttpServletResponse response, String cookieName, String value, long maxAge, String domain) {
        Cookie cookie = new Cookie(cookieName, value)
        cookie.setPath("/")
        if (StringUtils.isNotBlank(domain)) {
            cookie.setDomain(domain)
        }

        cookie.setMaxAge(maxAge as int)
        response.addCookie(cookie)
    }

    static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies()
        List<String> values = cookies?.find {it.name == cookieName}?.collect {it.value}
        return values?.get(0)
    }
}
