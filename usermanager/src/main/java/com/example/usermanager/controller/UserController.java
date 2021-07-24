package com.example.usermanager.controller;

import com.example.usermanager.mapper.UserMapper;
import com.example.usermanager.model.UserInfo;
import com.example.usermanager.tools.AppFinal;
import com.example.usermanager.tools.ResponseBody;
import com.example.usermanager.tools.SessionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName:UserController
 * Package:com.example.usermanager.controller
 * Description:
 *
 * @Author:HP
 * @date:2021/7/22 17:50
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource  //将某个类注入到当前类当中
    private UserMapper userMapper;

    /**
     * 登录接口
     *
     * @param username
     * @param password
     * @param req
     * @return
     */
    @RequestMapping("/login")
    public ResponseBody<UserInfo> login(@RequestParam String username, @RequestParam String password, HttpServletRequest req) {
        UserInfo userInfo = userMapper.login(username, password);
        int status = -1;
        String message = "用户或密码错误";
        if (userInfo != null && userInfo.getId() > 0) {
            status = 0;
            message = "";
            HttpSession session = req.getSession();
            session.setAttribute(AppFinal.USERINFO_SESSION_KEY, userInfo);
        }
        return new ResponseBody<>(status, message, userInfo);
    }

    /**
     * 查询当前登录用户的权限
     *
     * @param request
     * @return
     */
    @RequestMapping("/checkAdmin")
    public ResponseBody<Integer> checkAdmin(HttpServletRequest request) {
        int data = 0;
        HttpSession session = request.getSession(false);
        UserInfo userInfo = null;
        if (session != null && session.getAttribute(AppFinal.USERINFO_SESSION_KEY) != null) {
            userInfo = (UserInfo) session.getAttribute(AppFinal.USERINFO_SESSION_KEY);
            data = userInfo.getIsadmin();
        }
        return new ResponseBody<>(0, "", data);
    }

    /**
     * 添加用户
     *
     * @param userInfo
     * @param req
     * @return
     */
    @RequestMapping("/add")
    public ResponseBody<Integer> add(UserInfo userInfo, HttpServletRequest req) {
        int data = 0;
        int status = 0;
        String message = "";

        //安全校验
        UserInfo user = SessionUtil.getUserBySession(req);
        if (user == null) {
            status = -1;
            message = "当前用户未登录";
        } else if (userInfo.getIsadmin() == 1) {
            if (user.getIsadmin() == 1) {
                data = userMapper.add(userInfo);
                status = 0;
            } else if (user.getIsadmin() == 0) {
                message = "当前用户权限不足";
                status = -2;
            }
        } else if (userInfo.getIsadmin() == 0) {
            data = userMapper.add(userInfo);
        }
        return new ResponseBody<>(status, message, data);
    }

    /**
     * 修改用户列表
     *
     * @param uid
     * @return
     */
    @RequestMapping("/getuser")
    public ResponseBody<UserInfo> getUser(@RequestParam int uid) {
        int status = -1;
        String message = "";
        UserInfo userInfo = userMapper.getUser(uid);
        if (userInfo != null) {
            status = 0;
        }
        return new ResponseBody<>(status, message, userInfo);
    }

    @RequestMapping("/update")
    public ResponseBody<Integer> upDate(UserInfo userInfo) {
        int data = 0;
        data = userMapper.update(userInfo);
        return new ResponseBody<>(0, "", data);
    }

    /**
     * 获取列表页
     *
     * @param name
     * @param address
     * @param email
     * @param cpage
     * @param psize
     * @return
     */
    @RequestMapping("/list")
    public ResponseBody<HashMap<String, Object>> getListByPage(String name, String address, String email, int cpage, int psize, HttpServletRequest req) {
        //权限校验
        UserInfo userInfo = SessionUtil.getUserBySession(req);
        if (userInfo == null) {
            return new ResponseBody<>(-1, "用户未登录", null);
        }
        Integer isadmin = null;
        if (userInfo.getIsadmin() == 0) {
            isadmin = 0;
        }
        name = name.equals("") ? null : name;
        address = address.equals("") ? null : address;
        email = email.equals("") ? null : email;

        //跳转数量
        int skipCount = (cpage - 1) * psize;
        List<UserInfo> list = userMapper.getListByPage(name, address, email, skipCount, psize, isadmin);
        //查询满足条件的数据条数
        int tCount = userMapper.getCount(name, address, email, isadmin);
        //总数
        int tpage = (int) Math.ceil((tCount / (psize * 1.0)));

        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("tCount", tCount);
        map.put("tpage", tpage);
        return new ResponseBody<>(0, "", map);
    }

    /**
     * 单条删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/del")
    public ResponseBody<Integer> del(@RequestParam int id, HttpServletRequest req) {
        UserInfo userInfo = SessionUtil.getUserBySession(req);
        if (userInfo == null) {
            return new ResponseBody<>(-1, "用户未登录", 0);
        }
        //判断删除的是否是自己
        if (id == userInfo.getId()) {
            return new ResponseBody<>(-2, "不能删除当前用户", 0);
        }
        //权限校验
        Integer isadmin = null;
        if (userInfo.getIsadmin() == 0) {
            isadmin = 0;
        }
        int res = userMapper.del(id, isadmin);
        return new ResponseBody<>(0, "", res);
    }

}
