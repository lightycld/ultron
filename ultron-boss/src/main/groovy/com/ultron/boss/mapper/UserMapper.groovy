package com.ultron.boss.mapper

import com.ultron.boss.domain.entity.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

/**
 * Create By yangwei
 * Create at 2020/01/06 10:44
 * Description:
 */

@Mapper
interface UserMapper {

    @Select("SELECT count(*) FROM `user` WHERE `phone` = #{phone}")
    int isPhoneExist(@Param("phone") String phone)

    @Select("SELECT count(*) FROM `user` WHERE `credit_no` = #{creditNo}")
    int isCreditNoExist(@Param("creditNo") String creditNo)

    @Insert(''' INSERT INTO `user` (`username`, `phone`, `credit_no`, `email`, `we_chat_id`, 
                   `area`, `birthday`, `age`, `sex`, `credit_verified`, `verified`, `create_user`
                )''')
    void insert(User user)
}
