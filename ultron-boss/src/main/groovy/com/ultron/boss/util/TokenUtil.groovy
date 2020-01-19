package com.ultron.boss.util

import com.ultron.boss.config.JwtConfig
import com.ultron.boss.domain.vo.Admin
import groovy.transform.CompileStatic
import io.jsonwebtoken.JwtBuilder
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm

import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter
import java.security.Key

/**
 * Create By yangwei
 * Create at 2020/01/19 11:23
 * Description: 
 */
@CompileStatic
class TokenUtil {

    /**
     * generate jwt token
     * @param jwtConfig
     * @param admin
     * @return
     */
    static String generate(JwtConfig jwtConfig, Admin admin) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256

        long nowMillis = System.currentTimeMillis()
        Date now = new Date(nowMillis)

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfig.secretKey)
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName())

        Map properties = JSONUtil.parse(JSONUtil.toString(admin), HashMap)
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .addClaims(properties)
                .setIssuer(jwtConfig.getServerName())
                .setAudience(jwtConfig.getServerId())
                .signWith(signatureAlgorithm, signingKey)
        //添加Token过期时间
        if (jwtConfig.expireSeconds >= 0) {
            long expMillis = nowMillis + (jwtConfig.expireSeconds * 1000)
            Date exp = new Date(expMillis)
            builder.setExpiration(exp).setNotBefore(now)
        }

        //生成JWT
        return builder.compact()
    }
}
