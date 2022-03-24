package config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
/**
 * @author ZAY
 */
@Configuration
public class MyCorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        //新建跨域配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许所有请求源(url)
        corsConfiguration.addAllowedOrigin("*");
        //允许所有请求头信息
        corsConfiguration.addAllowedHeader("*");
        //允许所有请求url中包含的方法名
        corsConfiguration.addAllowedMethod("*");
        //允许所有请求url中包含用户的凭证信息
        corsConfiguration.setAllowCredentials(true);
        //新建基于url的配置源信息
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //将跨域规则注册进去(类似于List中的add方法)
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}