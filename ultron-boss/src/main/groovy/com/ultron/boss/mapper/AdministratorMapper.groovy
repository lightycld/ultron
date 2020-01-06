package com.ultron.boss.mapper

import com.ultron.boss.domain.entity.Administrator
import org.apache.ibatis.annotations.*

/**
 * Create By yangwei
 * Create at 2020/01/06 10:51
 * Description:
 */

@Mapper
interface AdministratorMapper {

    @Options(useGeneratedKeys = true, keyColumn = "id")
    @Insert('''INSERT INTO `administrator` (`username`, 
                `email`, `password`, `phone`, `salt_key`, `create_user`
                ) VALUES (
                    #{admin.username}, #{admin.email},
                     #{admin.password}, #{admin.phone}, 
                     #{admin.saltKey}, #{admin.createUser})''')
    void insert(@Param("admin") Administrator administrator)

    @Update("UPDATE `administrator` SET `password` = #{password} WHERE `id` = #{id}")
    void updatePassword(@Param("id") Long id, @Param("password") String password)

    @Update("UPDATE `admin` SET `status` = 1 WHERE `id` = #{id}")
    void enabled(@Param("id") Long id)

    @Update("UPDATE `admin` SET `status` = 0 WHERE `id` = #{id}")
    void disabled(@Param("id") Long id)

    @Select('''SELECT `id`, `username`, `email`, `phone`, `create_time`, 
            `status` FROM `administrator`''')
    List<Administrator> listAll()

    @Select('''SELECT * FROM `administrator` WHERE `phone` = #{phone}''')
    Administrator findByPhone(@Param("phone") String phone)

    @Select('''SELECT count(*) FROM `administrator` WHERE `username` = #{username}''')
    int usernameExist(@Param("username") String username)

    @Select('''SELECT count(*) FROM `administrator` WHERE `phone` = #{phone}''')
    int phoneExist(@Param("phone") String phone)
}
