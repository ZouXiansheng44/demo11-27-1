package com.example.demo.service;

import com.example.demo.dataobject.UserInfo;

public interface UserInfoService
{
    public UserInfo findByUserId(Integer userId);
    UserInfo save (UserInfo userInfo);
    public UserInfo Login(String userName,String userPassword);
}
