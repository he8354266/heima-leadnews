package com.heima.behavior;

import com.heima.behavior.config.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/11/2517:29
 */
@SpringBootApplication
public class BehaviorJarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BehaviorJarApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
