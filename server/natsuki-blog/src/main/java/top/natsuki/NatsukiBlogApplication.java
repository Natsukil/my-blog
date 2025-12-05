package top.natsuki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nat4u
 * @date 2025/11/30  14:52
 */

@SpringBootApplication
@MapperScan("top.natsuki.mapper")
public class NatsukiBlogApplication {
    public static void main( String[] args ) {
        SpringApplication.run(NatsukiBlogApplication.class, args);

    }
}

