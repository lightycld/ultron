package com.ultron.boss.filter

import groovy.transform.CompileStatic
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
/**
 * Create By yangwei
 * Create at 2020/01/06 15:43
 * Description: 
 */
@CompileStatic
class SessionFilter extends OncePerRequestFilter {

    /**
     * Creates a new instance.
     *
     * @param sessionRepository the <code>SessionRepository</code> to use. Cannot be null.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
