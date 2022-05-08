package config;

import interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;

/**
 * @author ZAY
 * 网页拦截器配置
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Resource
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //添加映射路径
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //设置放行哪些原始域   SpringBoot2.4.4下低版本使用.allowedOrigins("*")
                .allowedOriginPatterns("*")
                //放行哪些请求方式
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS","HEAD")
                //.allowedMethods("*") //或者放行全部
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //cookie设置
                .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)以及所有查询，其他任何更改数据库的数据的请求都拦截
        excludePath.add("/adminLogin");
        excludePath.add("/userRegister");
        excludePath.add("/userLogin");
        excludePath.add("/previewFile/*");
        excludePath.add("/selectAllSinger");
        excludePath.add("/selectAllSingerCount");
        excludePath.add("/selectSingerByPara");
        excludePath.add("/error");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
    }

}
