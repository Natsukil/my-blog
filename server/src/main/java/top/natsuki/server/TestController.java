package top.natsuki.server;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // 【关键点】@CrossOrigin: 允许来自 http://localhost:5173 的请求访问这个接口
    // 如果不加这个，浏览器会报 CORS 红色错误
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/test")
    public String testConnection() {
        return "🎉 恭喜！后端 Spring Boot 和前端 Vue 联通成功！22";
    }
}