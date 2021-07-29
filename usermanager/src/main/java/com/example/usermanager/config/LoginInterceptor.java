package com.example.usermanager.config;

import com.example.usermanager.tools.AppFinal;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ClassName:LoginInterCepter
 * Package:com.example.usermanager.config
 * Description:
 *
 * @Author:HP
 * @date:2021/7/29 10:40
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && AppFinal.USERINFO_SESSION_KEY != null) {
            return true;
        }
        return false;
    }
}
