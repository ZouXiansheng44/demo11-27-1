package com.example.demo.Controller.User;

import com.example.demo.Constant.CookieConstant;
import com.example.demo.Constant.RedisConstant;
import com.example.demo.Enum.ResultEnum;
import com.example.demo.dataobject.UserInfo;
import com.example.demo.form.UserForm;
import com.example.demo.service.Impi.UserInfoServiceImpi;

import com.example.demo.utils2.CookieUtil;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/user/info")
@Slf4j
@Api(description = "用户")
public class UserInfoController {
    @Autowired
    private UserInfoServiceImpi userInfoServiceImpi;

    @Autowired
    private StringRedisTemplate redisTemplate;
    //http://localhost:8080/zps/swagger-ui.html#/user-info-controller
//    @RequestMapping("/registers")
//    public ModelAndView zhuce(){//注册
//        ModelAndView mv = new ModelAndView("register");
//        return mv;
//    }
    @GetMapping("/list")
    public Msg list(){
        List<UserInfo> userInfo = userInfoServiceImpi.findAll();
        return Msg.success().add("userInfo",userInfo);
    }
        //完整版登录
    @GetMapping("/login")
    public Msg login(@RequestParam(value = "userName") String userName,
                      @RequestParam("userPassword") String userPassword,
                      Map<String, Object> map) {
        UserInfo userInfo = userInfoServiceImpi.Login(userName, userPassword);
        if(userInfo==null){
            map.put("msg", ResultEnum.USER_LOGIN_FAIL.getMsg());
            return Msg.failure();
        }
        map.put("userInfo", userInfo);//此句话作用是
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
   //此注册方法有待完善，用户密码的二次校验等等
   @PostMapping("/register")  //注册
    public Msg register(@RequestParam ("userName") String userName,
                                 @RequestParam ("userPassword") String userPassword,
                                 @RequestParam ("userEmail") String userEmail,
                                 @RequestParam ("userPhone") String userPhone){
        // ModelAndView success = new ModelAndView();
        // Msg msg=new Msg();
        //List<Login> register=loginService.save(username,password);
        UserInfo userInfo=new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        userInfo.setUserEmail(userEmail);
        userInfo.setUserPhone(userPhone);
        //如果用户的名字，密码，邮箱，电话同时一样，那返回用户已存在
     //   UserInfo userInfo2=userInfoServiceImpi.findByUserNameAndUserPasswordAndUserEmailAndUserPhone(userName,userPassword,userEmail,userPhone);
       // 如果用户的邮箱，电话同时一样，那返回用户已存在
        UserInfo userInfo2=userInfoServiceImpi.findByUserEmailAndUserPhone(userEmail,userPhone);
        if (userInfo2==null){
            userInfoServiceImpi.save(userInfo);
            return Msg.success();
        }
        else {
            return Msg.exists();
        }
    }

//    @PostMapping("/register1")//没有用到
//    public ModelAndView register(@RequestParam UserInfo userInfo,
//                                 Map<String,Object> map){
//        ModelAndView success = new ModelAndView();
//        userInfoServiceImpi.save(userInfo);
//        success.setViewName("success");
//        return success;
//    }

    @GetMapping("/login2")//登录
    public Msg login2(@RequestParam(value = "userName") String userName,
                 @RequestParam("userPassword") String userPassword) {
        UserInfo userInfo = userInfoServiceImpi.Login(userName, userPassword);
        if(userInfo==null){
            return Msg.failure();
        }
        return Msg.success().add("userInfo", userInfo);
}

/*
    @PostMapping("/register2")  //此注册方法有待完善，用户密码的二次校验等等
    public ModelAndView  register2(@RequestParam ("userName") String userName,
                        @RequestParam ("userPassword") String userPassword,
                         @RequestParam("Password2") String userPassword2,
                        @RequestParam ("userEmail") String userEmail,
                        @RequestParam ("userPhone") String userPhone,
                        Map<String,Object> map){
        //用户或密码为空的条件判断
        if(userName.isEmpty()||userPassword.isEmpty()||userPassword2.isEmpty()){
            success.setViewName("register");
            success.addObject("tip1","用户或密码不能为空");
            map.put("msg", ResultEnum.ADMIN_LOGOUT_SUCCESS.getMsg());
            return ModelAndView("sss",map);
        }
        //两次密码不一样的判断条件
        if(!userPassword.equals(userPassword2)){
            success.setViewName("register");
            success.addObject("tip2","两次密码不一样");
            return success;
        }
        //判断是否取到用户，如果没有就保存在数据库中
        List<UserInfo> userInfoList=userInfoServiceImpi.findByUserName(userName);
        if (userInfoList.size()==0){
            UserInfo userInfo=new UserInfo();
            userInfo.setUserName(userName);
            userInfo.setUserPassword(userPassword);
            userInfo.setUserEmail(userEmail);
            userInfo.setUserPhone(userPhone);
            userInfoServiceImpi.save(userInfo);
        }
        else {
            success.setViewName("404");
        }
        return success;
    }*/

    @PostMapping("/update")  //用户信息修改
    //save方法可以当修改方法用
    public Msg update(
                      @RequestParam ("userId") Integer userId,
                      @RequestParam ("userName") String userName,
                      @RequestParam ("userPassword") String userPassword,
                      @RequestParam ("userEmail") String userEmail,
                      @RequestParam ("userPhone") String userPhone,
                      @RequestParam ("userIcon") String userIcon) {
        UserInfo userInfo = new UserInfo();
        userInfo=userInfoServiceImpi.findByUserId(userId);
        userInfo.setUserName(userName);
        userInfo.setUserPassword(userPassword);
        userInfo.setUserEmail(userEmail);
        userInfo.setUserPhone(userPhone);
        userInfo.setUserIcon(userIcon);
        userInfoServiceImpi.save(userInfo);
        return Msg.success();
    }

    //不成功
    @GetMapping("/logout")
    public Msg logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        return Msg.success();
    }

    @PostMapping("/update2")
    public Msg update2(@Valid UserForm userForm, BindingResult bindingResult, Map<String,Object> map){
        UserInfo userInfo = userInfoServiceImpi.findByUserId(userForm.getUserId());
        BeanUtils.copyProperties(userForm,userInfo);
        userInfoServiceImpi.save(userInfo);
        return Msg.success();
    }
}
