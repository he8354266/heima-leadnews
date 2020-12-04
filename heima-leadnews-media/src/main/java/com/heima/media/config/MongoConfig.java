package com.heima.media.config;


import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/414:29
 */
@Configuration
public class MongoConfig {
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("172.168.9.114", 27017);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "le5le");
    }

}
