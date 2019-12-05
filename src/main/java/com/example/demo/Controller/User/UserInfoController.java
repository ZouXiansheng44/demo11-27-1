package com.example.demo.Controller.User;

import com.example.demo.dataobject.UserInfo;
import com.example.demo.service.Impi.UserInfoServiceImpi;
import com.example.demo.utils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/user/info")
@Slf4j
public class UserInfoController {
    @Autowired
    private UserInfoServiceImpi userInfoServiceImpi;
    //http://localhost:8080/zps/swagger-ui.html#/user-info-controller
//    @RequestMapping("/registers")
//    public ModelAndView zhuce(){//注册
//        ModelAndView mv = new ModelAndView("register");
//        return mv;
//    }

    @GetMapping("/login")
    public Msg login(@RequestParam(value = "userName") String userName,
                      @RequestParam("userPassword") String userPassword,
                      Map<String, Object> map) {
        UserInfo userInfo = userInfoServiceImpi.Login(userName, userPassword);
        map.put("userInfo", userInfo);
        return Msg.success().add("userInfo", userInfo);
    }

    @GetMapping("/findOne")
    public Msg findOne(@RequestParam("findOne") Integer userId, Map<String, Object> map) {
        UserInfo userInfo = userInfoServiceImpi.findByUserId(userId);
        map.put("userInfo", userInfo);
        return Msg.success().add("userInfo", userInfo);
    }

   /* @PostMapping("/register")
    public ModelAndView register(@RequestParam ("userName") String userName,
                                 @RequestParam ("userPassword") String userPassword,
                                 @RequestParam ("userEmail") String userEmail,
                                 @RequestParam ("userPhone") String userPhone,
                                 Map<String,Object> map){
        ModelAndView success = new ModelAndView();
        // Msg msg=new Msg();
        //List<Login> register=loginService.save(username,password);
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        userInfo.setUserEmail(userEmail);
        userInfo.setUserPhone(userPhone);
        userInfoServiceImpi.save(userInfo);
        map.put("userInfo", userInfo);
        success.setViewName("success");
        //return msg.add("userInfo", userInfo);
       // return new ModelAndView("success",map);
     //   return success;
    }*/

    @PostMapping("/register")  //此注册方法有待完善，用户密码的二次校验等等
    public Msg register(@RequestParam ("userName") String userName,
                                 @RequestParam ("userPassword") String userPassword,
                                 @RequestParam ("userEmail") String userEmail,
                                 @RequestParam ("userPhone") String userPhone,
                                 Map<String,Object> map){
        // ModelAndView success = new ModelAndView();
        // Msg msg=new Msg();
        //List<Login> register=loginService.save(username,password);
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        userInfo.setUserEmail(userEmail);
        userInfo.setUserPhone(userPhone);
        userInfoServiceImpi.save(userInfo);
        map.put("userInfo", userInfo);//不加也可以
     //   success.setViewName("success");
        //return msg.add("userInfo", userInfo);
       // return new ModelAndView("success",map);
     //   return success;
        return Msg.success();
    }

//    @PostMapping("/register1")//没有用到
//    public ModelAndView register(@RequestParam UserInfo userInfo,
//                                 Map<String,Object> map){
//        ModelAndView success = new ModelAndView();
//        userInfoServiceImpi.save(userInfo);
//        success.setViewName("success");
//        return success;
//    }

}
