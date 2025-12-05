package top.natsuki.config;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.JSONWriter;
import org.springframework.beans.factory.BeanRegistrarDslMarker;
import org.springframework.boot.http.converter.autoconfigure.ServerHttpMessageConvertersCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverters;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.alibaba.fastjson2.support.config.FastJsonConfig;
import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration(proxyBeanMethods = false)
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
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
        };
    }

    /**
     * 【推荐写法】直接注册为 Bean
     * 替换原有的 configureMessageConverters 方法
     * Spring Boot 会自动把这个 Bean 加入转换器列表，并优先使用
     */
    @Bean
    public ServerHttpMessageConvertersCustomizer fastJson2HttpMessageConvertersCustomizer() {
        return new ServerHttpMessageConvertersCustomizer() {
            @Override
            public void customize(HttpMessageConverters.ServerBuilder converters) {
                System.out.println("DEBUG: 开始配置 FastJson2转换器..."); // 添加日志验证是否执行

                // 1. 创建转换器
                FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

                // 2. 配置参数
                FastJsonConfig config = new FastJsonConfig();
                config.setDateFormat("yyyy-MM-dd HH:mm:ss");
                config.setWriterFeatures(
                        JSONWriter.Feature.WriteLongAsString, // 解决 Long 精度丢失
                        JSONWriter.Feature.WriteEnumsUsingName,
                        // JSONWriter.Feature.PrettyFormat, // 调试时可开启
                        JSONWriter.Feature.WriteMapNullValue
                );
                config.setReaderFeatures(JSONReader.Feature.AllowUnQuotedFieldNames);

                converter.setFastJsonConfig(config);
                converter.setDefaultCharset(StandardCharsets.UTF_8);
                converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));

                // 3. 【核心步骤】插队到第一位
                // 这一步至关重要，它确保 FastJson2 排在 Jackson 之前
                converters.withJsonConverter(converter);
//                converters.addCustomConverter(converter)
                System.out.println("DEBUG: FastJson2转换器配置完成，已插入队首");
            }
        };
    }
}
