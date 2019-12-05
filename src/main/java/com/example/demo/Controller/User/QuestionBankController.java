package com.example.demo.Controller.User;

import com.example.demo.Enum.QuestionBankEnum;
import com.example.demo.dataobject.Option;
import com.example.demo.dataobject.QuestionBank;
import com.example.demo.dataobject.QuestionBankDTO;
import com.example.demo.service.Impi.QuestionBankServiceImpi;
import com.example.demo.utils.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin//前后端分离接口
//@Controller
@RestController
@RequestMapping("/question/bank")
@Slf4j
public class QuestionBankController {

    @Autowired
    private QuestionBankServiceImpi questionBankServiceImpi;
    //localhost:8080/zxhw/question/bank/list
//    @GetMapping("/list")
//    public Msg list()
//    {
//       List<QuestionBank> questionBank=questionBankServiceImpi.findAll();
//        return Msg.success().add("questionBank",questionBank);
//    }

    @GetMapping("/list")
    public Msg list()
    {
        //list无法得到每一组里的单独的字段属性
        List<QuestionBank> questionBank=questionBankServiceImpi.findAll();
        //   JSONArray jsonArray=JSONArray.fromObject("questionBank");
        String[] splitOption=null;
        List<String> a=new ArrayList<>();
        String[] questionBank2=null;
       for(QuestionBank questionBank1:questionBank)
       {
          // questionBank2=questionBank1.getQuestionOption().split(",");
           System.out.println(questionBank1);
           String sss="";
           splitOption=questionBank1.getQuestionOption().split(",");
           for (String ss :splitOption)
           {
               a.add(ss);
            //   System.out.println("text:"+ss);
           }
           System.out.println(a);
           System.out.println("*************************************************************");

        }
        return Msg.success().add("questionBank",questionBank+"text:"+a);
    }

    //   json格式的控制输出
    @GetMapping("/findOne")
    //Map<String,Object>中string是它的键，存储的类型为String
    //object是它的值，object为所有数据类型的父类，就是说可以存储任何类型的数据，调用时，可以进行转型
    //save 方法名
    //定义一个map，通过put进行赋值，返回
    public Msg findOne(@RequestParam("questionId") Integer questionId, Map<String,Object> map){
        QuestionBank questionBank=questionBankServiceImpi.findByQuestionId(questionId);
        QuestionBank questionBank1=new QuestionBank();
        List<String> list=new ArrayList<>();
        questionBank1.setQuestionOption("text:"+questionBank.getQuestionOption());
        questionBank1.setQuestionAnswer(questionBank.getQuestionAnswer());
        questionBankServiceImpi.save(questionBank1);
        String[] splitOption=questionBank.getQuestionOption().split(",");
        String sss="";
        for (String ss :splitOption)
        {
            list.add(ss);
        }

        map.put("questionBank1",questionBank1);
        return Msg.success().add("questionBank1",questionBank1);
    }
/*
    //单个选项，获得选项为数组
    @GetMapping("/list2")
    public Msg list2()
    {
        //接收
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        //这个是用来调用findAll()方法并存入questionBank中
        List<QuestionBank> questionBank=questionBankServiceImpi.findAll();
        //用来赋值
        QuestionBankDTO questionBankDTO=new QuestionBankDTO();
        String[] splitOption=null;
        for(QuestionBank questionBank1:questionBank)
        {
//            questionBankDTO.setQuestionId(questionBank1.getQuestionId());
//            questionBankDTO.setCreateTime(questionBank1.getCreateTime());
//            questionBankDTO.setUpdateTime(questionBank1.getUpdateTime());
//            questionBankDTO.setQuestionAnswer(questionBank1.getQuestionAnswer());
//            questionBankDTO.setQuestionContent(questionBank1.getQuestionContent());
//            questionBankDTO.setQuestionStatus(questionBank1.getQuestionStatus());
//            questionBankDTO.setQuestionSubject(questionBank1.getQuestionSubject());
//            questionBankDTO.setQuestionType(questionBank1.getQuestionType());
            BeanUtils.copyProperties(questionBank1,questionBankDTO);
            //使用Map<String,String>map不能直接给它一个空值，因为这样会出现空指针报错  new 出来的时候要用HashMap<>();
            //为什么用HashMap<>();是因为
            Map<String,String> map = new HashMap<>();
          // Map<String,String> map =null; 错误因为这样会出现空异常was java.lang.NullPointerException
         //  Map map =null;
          //  System.out.println(questionBank1);
            splitOption=questionBank1.getQuestionOption().split(",");
            for (String ss :splitOption)
            {
               map.put("text:",ss);
            }
            //把选项的这个每一项前赋值"text:"的数组加入到questionBankDTO中
            //相当于将选项由原来的字符串，切割改装为每一项前赋值"text:"的数组
            questionBankDTO.setQuestionOption(map);
        }
        //把选项的这个数组加入到
        questionBankDTOList.add(questionBankDTO);
        return Msg.success().add("questionBank",questionBankDTOList);
    }*/

  @GetMapping("/list3")
  public Msg list3()
  {
      List<QuestionBank> questionBankList=questionBankServiceImpi.findAll();
      List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
      for(QuestionBank questionBank:questionBankList)
      {
          List<Object> a=new ArrayList<>();
//          Map<String,String> map = new HashMap<>();
          //这个变量对象不能放到for循环外面，
          QuestionBankDTO questionBankDTO=new QuestionBankDTO();
          BeanUtils.copyProperties(questionBank,questionBankDTO);
          String[] splitOption=questionBank.getQuestionOption().split(",");
          for (String options :splitOption)
          {
              Option option=new Option();
              option.setText(options);
//              map.put("text:",options);
              a.add(option);
          }
          questionBankDTO.setQuestionOption(a);
          questionBankDTOList.add(questionBankDTO);
      }
    // questionBankDTOList.add(questionBankDTO);不能放这里
      return Msg.success().add("questionBank",questionBankDTOList);
  }

    @GetMapping("/list4")
    public Msg list4(@RequestParam("questionSubject") String questionSubject)
    {
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionSubject(questionSubject);
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList)
        {
            List<List> a=new ArrayList<>();
//          Map<String,String> map = new HashMap<>();
            //这个变量对象不能放到for循环外面，
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            String[] splitOption=questionBank.getQuestionOption().split(",");
            for (String options :splitOption)
            {
                List<String> c=new ArrayList<>();
                String b="text:"+options;
                c.add(b);
//              map.put("text:",options);
                a.add(c);
            }
            questionBankDTO.setQuestionOption(a);
            questionBankDTOList.add(questionBankDTO);
        }
        // questionBankDTOList.add(questionBankDTO);不能放这里
        return Msg.success().add("questionBank",questionBankDTOList);
    }
    /*
    //这个方法虽然能把选项字符串以数组的形式输出，前端实际需要的是对象里包数组，数组再包对象这种形式，
   // 因为每一个选项就是一个对象
    @GetMapping("/list5")
    public Msg list5(@RequestParam("questionSubject") String questionSubject)
    {
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionSubject(questionSubject);
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList)
        {
            List<String> a=new ArrayList<>();
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            String[] splitOption=questionBank.getQuestionOption().split(",");
            for (String options :splitOption)
            {
                String b="txt:"+options;
                a.add(b);
            }
            questionBankDTO.setQuestionOption(a);
            questionBankDTOList.add(questionBankDTO);
        }
        return Msg.success().add("questionBank",questionBankDTOList);
    }
*/


    @GetMapping("/list6")
    public Msg list6(@RequestParam("questionSubject") String questionSubject){
        List<QuestionBankDTO> questionBankDTOList = new ArrayList<>();
        if (questionSubject!=null && questionSubject == QuestionBankEnum.MATH.getMsg()) {
            List<QuestionBank> questionBankList = questionBankServiceImpi.findByQuestionSubject(questionSubject);
            for (QuestionBank questionBank : questionBankList) {
                List<Object> a = new ArrayList<>();
                List<Object> c = new ArrayList<>();
                QuestionBankDTO questionBankDTO = new QuestionBankDTO();
                BeanUtils.copyProperties(questionBank, questionBankDTO);
                String[] splitOption = questionBank.getQuestionOption().split(",");
                String[] splitAnswer =questionBank.getQuestionAnswer().split(",");
                for (String options : splitOption) {
                    Option option = new Option();
                    option.setText(options);
                    a.add(option);
                }
                for (String answers: splitAnswer){
                    Option option = new Option();
                    option.setText(answers);
                    c.add(option);
                }
                questionBankDTO.setQuestionAnswer(c);
                questionBankDTO.setQuestionOption(a);
                questionBankDTOList.add(questionBankDTO);
            }
            // questionBankDTOList.add(questionBankDTO);不能放这里
        }
        return Msg.success().add("questionBank", questionBankDTOList);
        }
}
