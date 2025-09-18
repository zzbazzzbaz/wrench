package top.chatgqt.wrench.rate.limiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.chatgqt.wrench.rate.limiter.aop.RateLimiterAOP;

/**
 * 限流配置
 */
@Configuration
public class RateLimiterAutoConfig {

    @Bean
    public RateLimiterAOP rateLimiterAOP() {
        return new RateLimiterAOP();
    }
}
