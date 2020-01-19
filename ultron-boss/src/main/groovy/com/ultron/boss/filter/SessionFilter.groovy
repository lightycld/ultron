package com.ultron.boss.filter

import com.ultron.boss.config.JwtConfig
import com.ultron.boss.config.TokenHolder
import com.ultron.boss.domain.ResponseBean
import com.ultron.boss.exception.TokenException
import com.ultron.boss.util.CookieUtil
import com.ultron.boss.util.JSONUtil
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Create By yangwei
 * Create at 2020/01/17 17:06
 * Description:
 */
@Component
@WebFilter
class SessionFilter extends OncePerRequestFilter {


    @Autowired
    JwtConfig jwtConfig

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        Set<String> urls = ["/login", "/logout"]
        if (urls.contains(request.getRequestURI())) {
            return true
        }
        return false
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            checkToken(request, response)
        } catch (Exception e) {
            logger.info("An exception occurs during check token, ${e.message}", e)
            dueException(response, e.message, 200)
        }
        doFilterInternal(request, response, filterChain)
    }

    /**
     * check token and set token to cookie
     * @param request
     * @param response
     */
    private void checkToken(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtil.getCookie(request, "_token")
        if (StringUtils.isBlank(token)) {
            throw new TokenException("Invalid token.")
        }
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.secretKey).parseClaimsJws(token).getBody()
        if (claims == null) {
            throw new TokenException("Invalid token.")
        }
        Long id = claims.get("admin_id", Long)
        if (id == null || !TokenHolder.isOnline(id)) {
            throw new TokenException("Login again plz.")
        }

        CookieUtil.writeCookie(response, "_token", token, jwtConfig.expireSeconds, null)
    }

    /**
     * process token exception in filter
     * @param response
     * @param error
     * @param status
     */
    private void dueException(HttpServletResponse response, String error, int status) {
        response.setStatus(status)
        response.setHeader("Content-type", "application/json;charset=UTF-8")
        try {
            response.getWriter().write(JSONUtil.toString(ResponseBean.error(error)))
        } catch (Exception e) {
            logger.error(error, e)
        }
    }

}
