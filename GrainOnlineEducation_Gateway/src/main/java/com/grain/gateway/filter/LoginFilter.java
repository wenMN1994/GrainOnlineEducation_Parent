package com.grain.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2020/3/14 15:05
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class LoginFilter extends ZuulFilter {

    //定义过滤器的类型
    @Override
    public String filterType() {
        return PRE_TYPE;  //PRE
    }

    //过滤器执行顺序，返回的值越小，越先执行
    @Override
    public int filterOrder() {
        return 1;
    }

    //决定是否执行下面的run方法
    //如果返回false，表示过滤器放行操作，不执行run方法
    //如果返回true，过滤器没有放行，执行run方法
    @Override
    public boolean shouldFilter() {
        //判断：访问路径包含/eduvod/vid/getPlayAuthFront进行登录校验
        //1 获取请求的路径 uri，HttpServletRequest是请求对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String requestURI = request.getRequestURI();

        System.out.println("uri: "+requestURI);

        String palyUrl = "/vod/get-play-auth/";
        //2 根据获取请求路径判断，路径里面是否包含  /vod/get-play-auth/
        if(!StringUtils.isEmpty(requestURI) && requestURI.contains(palyUrl)) {
            //3 如果包含，进行验证操作，return true
            return true; //run方法执行，进行校验
        }
        return false;  //放行
    }

    //run方法写过滤器具体逻辑
    @Override
    public Object run() throws ZuulException {
        System.out.println("run方法执行了.........");
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)) {
            //不能访问接口
            currentContext.setSendZuulResponse(false); //不会向后执行
            //设置不能访问状态码
            currentContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
