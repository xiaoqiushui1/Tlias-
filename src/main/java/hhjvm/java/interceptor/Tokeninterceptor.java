package hhjvm.java.interceptor;

import hhjvm.java.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class Tokeninterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求路径
        String path = request.getRequestURI();//获取请求路径，不带HTTP
        //2.判断是否是登录请求，如果路径包含/login，说明是登录操作
        if (path.contains("/login")){
            log.info("正在登录,放行");

            return true ;
        }
        //3.获取请求头的token
        String token=request.getHeader("token");
        //4.判断是否存在token，如果不存在，说明用户没有登录，响应401码
        if (token==null || token.isEmpty()){//判断是否为空
            log.info("令牌为空，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //5.如果token存在，效验令牌，如果效验失败-》响应401码
        try {
            JwtUtils.parseJWT( token);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        //6.如果效验成功，放行
        log.info("令牌合法，放行");
   return true;
    }
}
