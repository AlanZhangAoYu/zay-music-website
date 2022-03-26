package application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZAY
 */
@SpringBootApplication
@MapperScan("mapper")
@ComponentScan(basePackages = {"controller","mapper","service","config"})
public class ZayMusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZayMusicApplication.class, args);
    }
}
