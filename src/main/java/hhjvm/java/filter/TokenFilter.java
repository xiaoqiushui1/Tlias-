package hhjvm.java.filter;

import hhjvm.java.utils.CurrentHolder;
import hhjvm.java.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@WebFilter(urlPatterns = "/*")//拦截所有请求,//注释掉的话就拦截不了
@Slf4j
public class TokenFilter implements Filter {//不用写另外两个重写是因为默认default，且不需要看是否
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest)  request;//ServletRequest 转换为 HttpServletRequest本身就为 HttpServletRequest
        HttpServletResponse response1= (HttpServletResponse) response;
       //1.获取请求路径
       String path = request1.getRequestURI();//获取请求路径，不带HTTP
        //2.判断是否是登录请求，如果路径包含/login，说明是登录操作
        if (path.contains("/login")){
            log.info("正在登录,放行");
            chain.doFilter(request1,response1);
            return;
        }
        //3.获取请求头的token
        String token=request1.getHeader("token");
        //4.判断是否存在token，如果不存在，说明用户没有登录，响应401码
        if (token==null || token.isEmpty()){//判断是否为空
            log.info("令牌为空，响应401");
            response1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
        }
        //5.如果token存在，效验令牌，如果效验失败-》响应401码
        try {
        Claims claims = JwtUtils.parseJWT(token);//解析令牌
     Integer empId=Integer.valueOf(claims.get("id").toString());//先从object转为String再转为intger,claims.getId获取的是jwt的编号（随机生成的）
                CurrentHolder.setCurrentId(empId);
                log.info("当前登录用户id为{},将其存入ThreadLocal",empId);
        } catch (Exception e) {
            log.info("令牌非法，响应401");
            response1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.如果效验成功，放行
        log.info("令牌合法，放行");
        chain.doFilter(request1,response1);
        //7.删除TheradLocal中的数据
        CurrentHolder.remove();
    }
}
