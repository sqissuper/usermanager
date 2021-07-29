package com.example.usermanager.mapper;

import com.example.usermanager.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ClassName:UserMapper
 * Package:com.example.usermanager.mapper
 * Description:
 *
 * @Author:HP
 * @date:2021/7/23 14:59
 */
@Mapper
public interface UserMapper {
    public UserInfo login(String username, String password);

    public int add(UserInfo userInfo);

    public UserInfo getUser(int uid);

    public int update(UserInfo userInfo);

    public List<UserInfo> getListByPage(String name, String address, String email, int skipCount, int psize, Integer isadmin);

    public int getCount(String name, String address, String email, Integer isadmin);

    public int del(int id, Integer isadmin);

    public int dels(String[] ids);

    public int regin(UserInfo userInfo);
}
