package com.itheima.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.Specification;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/79:32
 */
@SpringBootApplication
@MapperScan("com.itheima.admin.dao")
public class AdminJarApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminJarApplication.class, args);
    }
}
