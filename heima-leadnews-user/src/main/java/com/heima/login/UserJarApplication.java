package com.heima.login;

import com.heima.login.config.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/115:34
 */
@SpringBootApplication
public class UserJarApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserJarApplication.class, args);
    }
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
