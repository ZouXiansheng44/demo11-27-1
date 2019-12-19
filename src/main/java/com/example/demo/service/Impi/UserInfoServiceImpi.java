package com.example.demo.service.Impi;

import com.example.demo.dataobject.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<UserInfo> findByUserName(String userName) {
        return userInfoRepository.findByUserName(userName);
    }

    @Override
    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    @Override
    public UserInfo findByUserNameAndUserPasswordAndUserEmailAndUserPhone(String userName, String userPassword, String userEmail, String userPhone) {
        return userInfoRepository.findByUserNameAndUserPasswordAndUserEmailAndUserPhone(userName,userPassword,userEmail,userPhone);
    }

    @Override
    public UserInfo findByUserEmailAndUserPhone(String userEmail, String userPhone) {
        return userInfoRepository.findByUserEmailAndUserPhone(userEmail, userPhone);
    }

}
