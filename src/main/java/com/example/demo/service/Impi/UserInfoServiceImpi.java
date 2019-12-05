package com.example.demo.service.Impi;

import com.example.demo.dataobject.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpi implements UserInfoService
{

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUserId(Integer userId)
    {
        return userInfoRepository.findByUserId(userId);
    }

    @Override
    public UserInfo save(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfo Login(String userName, String userPassword) {
        return userInfoRepository.findByUserNameAndUserPassword(userName,userPassword);
    }

}
