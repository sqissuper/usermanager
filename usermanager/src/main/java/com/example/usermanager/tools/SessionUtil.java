package com.example.usermanager.tools;

import com.example.usermanager.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ClassName:SessionUtil
 * Package:com.example.usermanager.tools
 * Description:
 *
 * @Author:HP
 * @date:2021/7/24 10:30
 */
public class SessionUtil {
    /**
     * 获取session中的用户对象
     * @param req
     * @return
     */
    public static UserInfo getUserBySession(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        UserInfo userInfo = null;
        if(session != null && session.getAttribute(AppFinal.USERINFO_SESSION_KEY) != null) {
            userInfo =  (UserInfo)session.getAttribute(AppFinal.USERINFO_SESSION_KEY);
        }
        return userInfo;
    }
}
