package com.ultron.boss

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
/**
 * Create By yangwei
 * Create at 2020/01/03 20:43
 * Description:
 */

@MapperScan("com.ultron.boss.mapper")
@SpringBootApplication
class BossApplication {

    static void main(String[] args) {
        SpringApplication.run(BossApplication.class, args)
    }

}
