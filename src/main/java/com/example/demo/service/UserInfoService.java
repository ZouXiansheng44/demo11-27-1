package com.example.demo.service;

import com.example.demo.dataobject.UserInfo;

import java.util.List;

public interface UserInfoService
{
    public UserInfo findByUserId(Integer userId);
    UserInfo save (UserInfo userInfo);
    public UserInfo Login(String userName,String userPassword);
    List<UserInfo> findByUserName(String userName);
    List<UserInfo> findAll();
    public UserInfo findByUserNameAndUserPasswordAndUserEmailAndUserPhone(String userName,String userPassword,String userEmail,String userPhone);
    public UserInfo findByUserEmailAndUserPhone(String userEmail,String userPhone);
}
