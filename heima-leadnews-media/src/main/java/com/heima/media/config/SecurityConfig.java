package com.heima.media.config;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: project
 * @Package * @Description:     * @author CodingSir
 * @date 2020/12/216:53
 */
@Configuration
@ServletComponentScan("com.heima.common.web.wm.security")
public class SecurityConfig {
}
