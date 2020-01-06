package com.ultron.boss.util

import groovy.transform.CompileStatic

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom

/**
 * Create By yangwei
 * Create at 2020/01/06 11:54
 * Description: 
 */
@CompileStatic
class PasswordUtil {

    /**
     * 生成盐
     * @return
     */
    static byte[] createSalt() {
        byte[] salt = new byte[16]
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG")
            random.nextBytes(salt)
            return salt
        } catch (NoSuchAlgorithmException e) {
            return null
        }
    }

    /**
     * 生成摘要
     * @param password
     * @param salt
     * @return
     */
    static byte[] digest(String password, byte[] salt) {

        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA")
            if (salt != null && salt.length > 0) {
                msgDigest.update(salt)
            }

            byte[] digest = msgDigest.digest(password.getBytes())
            return digest
        } catch (NoSuchAlgorithmException e) {
            return null
        }
    }

    static String createCredential(String password, byte[] salt) {
        return digest(password, salt).toString()
    }
}
