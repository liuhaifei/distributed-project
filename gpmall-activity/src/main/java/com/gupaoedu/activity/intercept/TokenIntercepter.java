package com.gupaoedu.activity.intercept;

import com.gupaoedu.activity.controller.Anoymous;
import com.gupaoedu.activity.controller.BaseController;
import com.gupaoedu.activity.utils.CookieUtil;
import com.gupaoedu.common.constants.GpmallWebConstant;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.CheckAuthRequest;
import com.gupaoedu.user.dto.CheckAuthResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 刘海飞

 */
public class TokenIntercepter extends HandlerInterceptorAdapter {

    private final String ACCESS_TOKEN="access_token";

    @Autowired
    IUserCoreService iUserCoreService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Object bean=handlerMethod.getBean();

        if(isAnoymous(handlerMethod)){
            return true;
        }
        if(!(bean instanceof BaseController)){
            throw new RuntimeException("must extend basecontroller");
        }
        String token=CookieUtil.getCookieValue(request,ACCESS_TOKEN);
        boolean isAjax=CookieUtil.isAjax(request);
        if(StringUtils.isEmpty(token)){
            if(isAjax){
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("{\"code\":\"-1\",\"msg\":\"error\",\"url\":\""+GpmallWebConstant.GPMALL_SSO_ACCESS_URL+"\"}");
                return false;
            }
            response.sendRedirect(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
            return false;
        }
        CheckAuthRequest checkAuthRequest=new CheckAuthRequest();
        checkAuthRequest.setToken(token);
        CheckAuthResponse checkAuthResponse=iUserCoreService.validToken(checkAuthRequest);
        if("000000".equals(checkAuthResponse.getCode())){
            BaseController baseController=(BaseController)bean;
            baseController.setUid(checkAuthResponse.getUid());
            return super.preHandle(request, response, handler);
        }
        if(isAjax){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":\""+checkAuthResponse.getCode()+"\"" +
                    ",\"msg\":\""+checkAuthResponse.getMsg()+"\",\"url\":\""+GpmallWebConstant.GPMALL_SSO_ACCESS_URL+"\"}");
            return false;
        }
        response.sendRedirect(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
        return false;
    }

    private boolean isAnoymous(HandlerMethod handlerMethod){
        Object bean=handlerMethod.getBean();
        Class clazz=bean.getClass();
        if(clazz.getAnnotation(Anoymous.class)!=null){
            return true;
        }
        Method method=handlerMethod.getMethod();
        return method.getAnnotation(Anoymous.class)!=null;
    }
}
