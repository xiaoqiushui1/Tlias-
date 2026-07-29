package hhjvm.java.config;

import hhjvm.java.interceptor.Tokeninterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 配置：解决前后端分离跨域问题
 * 允许前端（如 Vue / React 开发服务器）跨域访问后端 API
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOriginPatterns("*")          // 允许所有来源
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
//@Autowired
//private Tokeninterceptor tokeninterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {//注册拦截器
//        registry.addInterceptor(tokeninterceptor)
//                .addPathPatterns("/**")//拦截所有请求
//                .excludePathPatterns("/login");//不拦截的请求
   // }
}
