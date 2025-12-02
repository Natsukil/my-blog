package top.natsuki.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 覆盖所有请求路径
        registry.addMapping("/**")
                // 允许发送 Cookie (凭证)
                // 如果设为 true，前端 (Axios/Fetch) 也必须设置 withCredentials: true
                .allowCredentials(true)
                // 允许的源 (域名)
                // 推荐使用 allowedOriginPatterns 而不是 allowedOrigins，
                // 因为 allowedOrigins("*") 和 allowCredentials(true) 在新版 Spring 中不兼容。
                .allowedOriginPatterns("*")
                // 允许的请求头
                .allowedHeaders("*")
                // 允许的方法 (GET, POST, etc.)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 跨域允许时间 (秒)，在此时间内浏览器不需要再次发送预检请求 (OPTIONS)
                .maxAge(3600);
    }
}
