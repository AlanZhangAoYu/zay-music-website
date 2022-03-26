package config;

import interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZAY
 * 网页拦截器配置
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    private TokenInterceptor tokenInterceptor;
    @Bean
    public WebInterceptorConfig webInterceptorConfig(){
        return new WebInterceptorConfig();
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
        excludePath.add("/user/register");
        excludePath.add("/user/login");
        excludePath.add("/static/**");
        excludePath.add("/assets/**");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
