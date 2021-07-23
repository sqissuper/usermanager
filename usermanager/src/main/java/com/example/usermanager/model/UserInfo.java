package com.example.usermanager.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * ClassName:UserInfo
 * Package:com.example.usermanager.model
 * Description:
 *
 * @Author:HP
 * @date:2021/7/22 17:48
 */
@Data
public class UserInfo {
    private int id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private int age;
    private String address;
    private String qq;
    private String email;
    private int isadmin;
}
