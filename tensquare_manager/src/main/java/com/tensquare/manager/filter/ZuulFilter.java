package com.tensquare.manager.filter;

import com.netflix.discovery.converters.Auto;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tensquare.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class ZuulFilter extends com.netflix.zuul.ZuulFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String filterType() {
        //在请求前"pre"或者请求后"post"执行
        return "pre";   //表示操作之前
    }

    @Override
    public int filterOrder() {
        //多个过滤器的执行顺序，数字越小越先执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //当前过滤器是否开启
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        /*//添加敏感头信息，试了不可以的；
        RequestContext ctx = RequestContext.getCurrentContext();   //获取当前上下文，拿到请求的内容
        HttpServletRequest request = ctx.getRequest();
        String header = request.getHeader("Authorization");
        if (!header.isEmpty()) {
            ctx.addZuulRequestHeader("Authorization", header);
        }*/
        RequestContext ctx = RequestContext.getCurrentContext();   //获取当前上下文，拿到请求的内容
        HttpServletRequest request = ctx.getRequest();


        /**
         * 这两个放行不太懂
         */
        //网关是做二次请求的
        //第一次请求需要经过网关分发的请求，他不会带头信息，所以永远不会进入这个（ if (!header.isEmpty()) {）判断的。所以第一次直接放行
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }
        //登陆放行
        if(request.getRequestURI().indexOf("login")!=-1){
            return null;
        }


        String header = request.getHeader("Authorization");
        if (!header.isEmpty()) {




            if(header.startsWith("Bear ")){
                String token=header.substring(7);
                try {
                    Claims claims= jwtUtil.parseJWT(header);
                    String roles= (String) claims.get("roles");
                    if(roles.equals("admin")){
                        ctx.addZuulRequestHeader("Authorization", header);
                        return null;
                    }
                    //省略些逻辑，没意思的逻辑
                }catch (Exception e){
                    ctx.setSendZuulResponse(false);  //终止运行
                }

            }

        }

        return null;
    }
}
