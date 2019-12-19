package com.example.demo.Controller;


import com.example.demo.dataobject.UserInfo;
import com.example.demo.service.Impi.UserInfoServiceImpi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminUserInfo/userInfo")
@Slf4j
@Api(description = "管理员")
public class AdminUserInfoController {
    @Autowired
    private UserInfoServiceImpi userInfoServiceImpi;
    //  http://localhost:8081/zxhw/adminUserInfo/userInfo/list

    @ApiOperation(value = "用户列表", notes = "显示列表")
    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<UserInfo> userInfoList = userInfoServiceImpi.findAll();
        map.put("userInfoList", userInfoList);
        map.put("url", "/zxhw/adminUserInfo/userInfo/list");
        return new ModelAndView("userInfo/list", map);
    }
}
