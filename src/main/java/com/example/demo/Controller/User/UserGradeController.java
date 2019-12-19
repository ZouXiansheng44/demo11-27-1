package com.example.demo.Controller.User;

import com.example.demo.DTO.GradeDTO;
import com.example.demo.dataobject.UserGrade;
import com.example.demo.dataobject.UserInfo;
import com.example.demo.service.Impi.UserGradeServiceImpi;
import com.example.demo.service.Impi.UserInfoServiceImpi;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/user/grade")
@Slf4j
@Api(description = "成绩")
public class UserGradeController {
    @Autowired
    private UserGradeServiceImpi userGradeServiceImpi;
    @Autowired
    private UserInfoServiceImpi UserInfoServiceImpi;
    @PostMapping("/save")
    public Msg save(@RequestParam ("userId") Integer userId,
                    @RequestParam("gradeValue") Integer gradeValue)
    {
        UserGrade userGrade=new UserGrade();
        userGrade.setGradeValue(gradeValue);
        userGrade.setUserId(userId);
        userGrade.setQuestionSubject("数学");
        userGradeServiceImpi.save(userGrade);
        return Msg.success();
    }

    @GetMapping("/list1")
    public Msg list1(){
     List<UserGrade> userGradeList=userGradeServiceImpi.findByQuestionSubject("数学");
    //    List<UserGrade> userGradeList2=new ArrayList<>();
        return Msg.success().add("userGradeList",userGradeList);
    }

    @GetMapping("/list2")
    public Msg list2(){//根据数学这门科目，返回用户数学分数从大到小的排名列表
        List<UserGrade> userGradeList=userGradeServiceImpi.querylike2("数学");
        List<UserGrade> userGradeList1=new ArrayList<>();
        int count=0;//计数器
        for(UserGrade userGrade:userGradeList){//遍历
            count++;
            if (count<=3){//控制的数据量
                userGradeList1.add(userGrade);//用来接收限制后的数据
            }else {
                break;//终止遍历
            }
       //     System.out.println(count);
        }
        return Msg.success().add("userGradeList1",userGradeList1);
    }

    @GetMapping("/list3")
    public Msg list3(){
        List<UserGrade> userGradeList=userGradeServiceImpi.findAll();
        return Msg.success().add("userGradeList",userGradeList);
    }

    @GetMapping("/list4")
    public Msg list4(){
        // Page<UserGrade> userGradePage=userGradeServiceImpi.findByGradeId(PageRequest.of(1,10));
        List<UserGrade> userGradeList=userGradeServiceImpi.querylike2("英语");
        return Msg.success().add("userGradeList",userGradeList);
    }

    @GetMapping("/list5")//数据拼接 数学成绩
    public Msg list5(){
        List<UserGrade> userGradeList=userGradeServiceImpi.querylike2("数学");
        List<GradeDTO> gradeDTOList=new ArrayList<>();
        int count=0;//计数器
        for(UserGrade userGrade:userGradeList)
        {//遍历
           UserInfo userInfo=UserInfoServiceImpi.findByUserId(userGrade.getUserId());
            GradeDTO gradeDTO=new GradeDTO();
            BeanUtils.copyProperties(userGrade,gradeDTO);
            gradeDTO.setUserName(userInfo.getUserName());
            count++;
            if (count<=10){
                gradeDTOList.add(gradeDTO);
            }else {
                break;
            }
        }
        return Msg.success().add("gradeDTOList",gradeDTOList);
    }

    @GetMapping("/list6")//数据拼接 英语成绩
    public Msg list6(){
        List<UserGrade> userGradeList=userGradeServiceImpi.querylike2("英语");
        List<GradeDTO> gradeDTOList=new ArrayList<>();
        int count=0;//计数器
        for(UserGrade userGrade:userGradeList)
        {//遍历
            UserInfo userInfo=UserInfoServiceImpi.findByUserId(userGrade.getUserId());
            GradeDTO gradeDTO=new GradeDTO();
            BeanUtils.copyProperties(userGrade,gradeDTO);
            gradeDTO.setUserName(userInfo.getUserName());
            count++;
            if (count<=10){
                gradeDTOList.add(gradeDTO);
            }else {
                break;
            }
        }
        return Msg.success().add("gradeDTOList",gradeDTOList);
    }

    @GetMapping("/list7")//数据拼接 计算机成绩
    public Msg list7(){
        List<UserGrade> userGradeList=userGradeServiceImpi.querylike2("计算机");
        List<GradeDTO> gradeDTOList=new ArrayList<>();
        int count=0;//计数器
        for(UserGrade userGrade:userGradeList)
        {//遍历
            UserInfo userInfo=UserInfoServiceImpi.findByUserId(userGrade.getUserId());
            GradeDTO gradeDTO=new GradeDTO();
            BeanUtils.copyProperties(userGrade,gradeDTO);
            gradeDTO.setUserName(userInfo.getUserName());
            count++;
            if (count<=10){
                gradeDTOList.add(gradeDTO);
            }else {
                break;
            }
        }
        return Msg.success().add("gradeDTOList",gradeDTOList);
    }

    @GetMapping("/list8")//数据拼接 计算机成绩
    public Msg list8(){
        List<UserGrade> userGradeList1=userGradeServiceImpi.querylike2("数学");
        List<UserGrade> userGradeList2=userGradeServiceImpi.querylike2("英语");
        List<UserGrade> userGradeList3=userGradeServiceImpi.querylike2("计算机");
        List<GradeDTO> gradeDTOList1=new ArrayList<>();//数学
        List<GradeDTO> gradeDTOList2=new ArrayList<>();//英语
        List<GradeDTO> gradeDTOList3=new ArrayList<>();//计算机
        int count1=0;//计数器
        for(UserGrade userGrade:userGradeList1)//数学
        {//遍历
            UserInfo userInfo=UserInfoServiceImpi.findByUserId(userGrade.getUserId());
            GradeDTO gradeDTO=new GradeDTO();
            BeanUtils.copyProperties(userGrade,gradeDTO);
            gradeDTO.setUserName(userInfo.getUserName());
            count1++;
            if (count1<=10){
                gradeDTOList1.add(gradeDTO);
            }else {
                break;
            }
        }
        int count2=0;//计数器
        for(UserGrade userGrade:userGradeList2)//英语
        {//遍历
            UserInfo userInfo=UserInfoServiceImpi.findByUserId(userGrade.getUserId());
            GradeDTO gradeDTO=new GradeDTO();
            BeanUtils.copyProperties(userGrade,gradeDTO);
            gradeDTO.setUserName(userInfo.getUserName());
            count2++;
            if (count2<=10){
                gradeDTOList2.add(gradeDTO);
            }else {
                break;
            }
        }
        int count3=0;//计数器
        for(UserGrade userGrade:userGradeList3)//计算机
        {//遍历
            UserInfo userInfo=UserInfoServiceImpi.findByUserId(userGrade.getUserId());
            GradeDTO gradeDTO=new GradeDTO();
            BeanUtils.copyProperties(userGrade,gradeDTO);
            gradeDTO.setUserName(userInfo.getUserName());
            count3++;
            if (count3<=10){
                gradeDTOList3.add(gradeDTO);
            }else {
                break;
            }
        }
        return Msg.success().add("gradeDTOList3",gradeDTOList3).add("gradeDTOList2",gradeDTOList2).add("gradeDTOList1",gradeDTOList1);
    }
}
