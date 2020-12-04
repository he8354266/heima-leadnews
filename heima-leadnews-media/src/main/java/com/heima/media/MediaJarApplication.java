package com.heima.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/39:12
 */
@SpringBootApplication
public class MediaJarApplication {
    public static void main(String[] args) {
        SpringApplication.run(MediaJarApplication.class, args);
    }
}
