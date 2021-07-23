package com.example.usermanager.mapper;

import com.example.usermanager.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public UserInfo login(String username,String password);
}
