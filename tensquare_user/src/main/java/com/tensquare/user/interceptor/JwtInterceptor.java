package com.tensquare.user.interceptor;

import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       log.info("经过了拦截器");
       //true 是放行，false是不让走了
        //无论如何都放行，具体能不能操作还是在具体的操作中去判断
        //拦截器只是负责把有请求头包含token的令牌进行一个解析。
        String header=request.getHeader("Authorization");
        if(null!=header && !"".equals(header)){
            //如果包含有头信息，就对其解析
           if(header.startsWith("Bearer ")){

               //对判断以Bearer开头，但是token乱写的疑问
               //如果token 乱写，虽然放行，但是claims_* 是不会有值的；比如说在进行删除操作时，它会判断claims_* 是否有值，如果没值就抛异常，也不会删除成功的


               String token=header.substring(7);
               //对令牌进行验证
               try {
                   Claims claims=jwtUtil.parseJWT(token);
                   String roles= (String) claims.get("roles");
                   if(roles!=null || roles.equals("admin")){
                      request.setAttribute("claims_admin",token);
                   }
                   if(roles!=null || roles.equals("user")){
                       request.setAttribute("claims_user",token);
                   }
               }catch (Exception e){
                   throw new RuntimeException("令牌不正确!");
               }
           }
        }
        return true;
    }

}
