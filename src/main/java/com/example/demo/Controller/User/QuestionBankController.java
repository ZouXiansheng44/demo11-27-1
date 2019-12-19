package com.example.demo.Controller.User;

import com.example.demo.DTO.*;
import com.example.demo.Enum.QuestionBankEnum;
import com.example.demo.dataobject.*;
import com.example.demo.service.Impi.*;
import com.example.demo.service.QuestionScoreService;
import com.example.demo.utils.Msg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "题库")
public class QuestionBankController {

    @Autowired
    private QuestionBankServiceImpi questionBankServiceImpi;
    @Autowired
    private QuestionAnalysisServiceImpi questionAnalysisServiceImpi;
    @Autowired
    //其实用Service或用ServiceImpi都可以，但我感觉ServiceImpi会执行的更快
    private QuestionScoreService questionScoreService;
    @Autowired
    private QuestionFeedbackServiceImpi questionFeedbackServiceImpi;
    @Autowired
    private QuestionMistakeServiceImpi questionMistakeServiceImpi;
    @Autowired
    private QuestionCollectServiceImpi questionCollectServiceImpi;
    @Autowired
    private UserInfoServiceImpi userInfoServiceImpi;
    //localhost:8080/zxhw/question/bank/list
//    @GetMapping("/list")
//    public Msg list()
//    {
//       List<QuestionBank> questionBank=questionBankServiceImpi.findAll();
//        return Msg.success().add("questionBank",questionBank);
//    }
    @GetMapping("/list")
    @ApiOperation(value = "",notes = "题库表数据切割显示")
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
    @ApiOperation(value = "",notes = "根据题库表的主键ID数据切割显示")
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

       // map.put("questionBank1",questionBank1);
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

  @GetMapping("/list3")//需要
  @ApiOperation(value = "",notes = "题库表数据切割控制JSON格式列表显示")
  public Msg list3()
  {
      List<AnswerAnalysis> questionAnalysisList=questionAnalysisServiceImpi.findAll();
      List<QuestionBank> questionBankList=questionBankServiceImpi.findAll();
      List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
      for(QuestionBank questionBank:questionBankList)
      {
          List<Object> a=new ArrayList<>();
          List<Object> c=new ArrayList<>();
//          Map<String,String> map = new HashMap<>();
          //这个变量对象不能放到for循环外面，
          QuestionBankDTO questionBankDTO=new QuestionBankDTO();
          BeanUtils.copyProperties(questionBank,questionBankDTO);
          String[] splitOption=questionBank.getQuestionOption().split(",");
          String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
          for (String options :splitOption)
          {
              Option option=new Option();
              option.setText(options);
//              map.put("text:",options);
              a.add(option);
          }
          for (String answers: splitAnswer){
              Option option = new Option();
              option.setText(answers);
              c.add(option);
          }
         // questionBankDTO.setQuestionAnswer();
          questionBankDTO.setQuestionAnswer(c);
          questionBankDTO.setQuestionOption(a);
          questionBankDTOList.add(questionBankDTO);
      }
    // questionBankDTOList.add(questionBankDTO);不能放这里
      return Msg.success().add("questionBank",questionBankDTOList).add("questionAnalysisList",questionAnalysisList);
      //return Msg.success().add("questionBank",questionBankDTOList);
  }

    @GetMapping("/list4")//例子
    @ApiOperation(value = "",notes = "根据前端传回的题目科目查找题库表数据并切割控制JSON格式列表显示（对象包数组）")
    public Msg list4(@RequestParam("questionSubject") String questionSubject)
    {
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionSubject(questionSubject);
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList)
        {
            List<List> a=new ArrayList<>();
         //  Map<String,String> map = new HashMap<>();

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
    @GetMapping("/list5")//例子
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

    @GetMapping("/list6")//需要
    @ApiOperation(value = "",notes = "根据前端传回的题目科目查找题库表数据并切割控制JSON格式列表显示（对象包数组再包对象）")
    public Msg list6(@RequestParam("questionSubject") String questionSubject){
        List<QuestionBankDTO> questionBankDTOList = new ArrayList<>();
        if (questionSubject!=null && questionSubject == QuestionBankEnum.MATH.getMsg()) {
            List<QuestionBank> questionBankList = questionBankServiceImpi.findByQuestionSubject(questionSubject);
            for (QuestionBank questionBank : questionBankList) {
                //中间变量1，用于对题目选项用add添加方法
                List<Object> a = new ArrayList<>();
                //中间变量2，用于对题目答案用add添加方法
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



    @GetMapping("/list7")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接")
    public Msg list7()
    {
//        AnswerAnalysis answerAnalysis=new AnswerAnalysis();
//        QuestionBank questionBank1=new QuestionBank();
//        QuestionScore questionScore=new QuestionScore();
//        if (answerAnalysis.getQuestionId()==questionBank1.getQuestionId()){
//
//        }
        List<QuestionBank> questionBankList=questionBankServiceImpi.findAll();
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList)
        {
            //*1
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            //*1
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
//          Map<String,String> map = new HashMap<>();
            //这个变量对象不能放到for循环外面，
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");

            //*3
            if(answerAnalysis==null){
                questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");

                //*3"还没有这个题目的解析"

             //   throw new QuestionException(ResultEnum.PARAM_ERROR);
            }
            else{
                //*2
                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
           // questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            //*2
            questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//            if (answerAnalysis.getQuestionId()==questionBank1.getQuestionId()){
//                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
//            }
//            if(questionScore.getQuestionType()==questionBank1.getQuestionType()){
//                questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//            }
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
//              map.put("text:",options);
                a.add(option);
            }
            for (String answers: splitAnswer){
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            // questionBankDTO.setQuestionAnswer();
//            if (answerAnalysis.getQuestionId()==questionBank1.getQuestionId()){
//                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
//            }
//            if(questionScore.getQuestionType()==questionBank1.getQuestionType()){
//                questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//            }
            questionBankDTO.setQuestionAnswer(c);
            questionBankDTO.setQuestionOption(a);
            questionBankDTOList.add(questionBankDTO);
        }

        return Msg.success().add("questionBank",questionBankDTOList);
    }

    @GetMapping("/list8")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理")
    public Msg list8()
    {
//        AnswerAnalysis answerAnalysis=new AnswerAnalysis();
//        QuestionBank questionBank1=new QuestionBank();
//        QuestionScore questionScore=new QuestionScore();
//        if (answerAnalysis.getQuestionId()==questionBank1.getQuestionId()){
//
//        }
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionSubject("数学");
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList)
        {
            //*1
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            //*1
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
//          Map<String,String> map = new HashMap<>();
            //这个变量对象不能放到for循环外面，
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            //*3
            if(answerAnalysis==null){
                questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");
                //*3"还没有这个题目的解析"

                //   throw new QuestionException(ResultEnum.PARAM_ERROR);
            }
            else{
                //*2
                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            // questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            //*2
            questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//            if (answerAnalysis.getQuestionId()==questionBank1.getQuestionId()){
//                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
//            }
//            if(questionScore.getQuestionType()==questionBank1.getQuestionType()){
//                questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//            }
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
//              map.put("text:",options);
                a.add(option);
            }
            for (String answers: splitAnswer){
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            // questionBankDTO.setQuestionAnswer();
//            if (answerAnalysis.getQuestionId()==questionBank1.getQuestionId()){
//                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
//            }
//            if(questionScore.getQuestionType()==questionBank1.getQuestionType()){
//                questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//            }
            questionBankDTO.setQuestionAnswer(c);
            questionBankDTO.setQuestionOption(a);
            questionBankDTOList.add(questionBankDTO);
        }
        return Msg.success().add("questionBank",questionBankDTOList);
    }

    @GetMapping("/list9")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理//随机数产生指定题数
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理")
    public Msg list9()
    {
        QuestionBank questionBank1 = new QuestionBank();
//        List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionBank1.getQuestionId());
        List<QuestionBank> questionBankList = questionBankServiceImpi.findByQuestionSubject("数学");
        List<QuestionBank> questionBankList2 = new ArrayList<>();
        List<QuestionBankDTO> questionBankDTOList = new ArrayList<>();
        int seed=0;
        for (QuestionBank questionBank2 : questionBankList)
        {
            //    Random random = new Random(10);
            int arr[] = new int[2];
            //循环取10次
            for (int i = 0; i < arr.length; i++)
            {
                //随机数的范围是questionBankList.size()的范围
                int index = (int) (Math.random() * questionBankList.size());
                arr[i] = index;
                for (int j = 0; j < i; j++)
                {
                    if (arr[j] == arr[i])
                    {
                        i--;
                        break;
                    }
                }
                System.out.println(arr[i]);
                if ( arr[i] == questionBank2.getQuestionId())
                {
                    questionBankList2.add(questionBank2);
                }
            }
        }
//        List< QuestionBank > questionBankList2=new ArrayList<>();
//        int i=(int)(Math.random()*questionBankList.size());
//        questionBankList2.add(i);
            for (QuestionBank questionBank : questionBankList2)
            {
                questionBank.getQuestionId();
                //*1
                AnswerAnalysis answerAnalysis = questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
                //*1
                QuestionScore questionScore = questionScoreService.findByQuestionType(questionBank.getQuestionType());
                List<Object> a = new ArrayList<>();
                List<Object> c = new ArrayList<>();
                QuestionBankDTO questionBankDTO = new QuestionBankDTO();
                BeanUtils.copyProperties(questionBank, questionBankDTO);
                String[] splitOption = questionBank.getQuestionOption().split(",");
                String[] splitAnswer = questionBank.getQuestionAnswer().split(",");
                //*3
                if (answerAnalysis == null) {
                    questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");
                } else {
                    //*2
                    questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
                }
                questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
                for (String options : splitOption) {
                    Option option = new Option();
                    option.setText(options);
//              map.put("text:",options);
                    a.add(option);
                }
                for (String answers : splitAnswer) {
                    Option option = new Option();
                    option.setText(answers);
                    c.add(option);
                }
                questionBankDTO.setQuestionAnswer(c);
                questionBankDTO.setQuestionOption(a);
                questionBankDTOList.add(questionBankDTO);
            }
        return Msg.success().add("questionBank", questionBankDTOList);
        }

    @GetMapping("/list10")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理和状态为收藏的错题以及评论嵌套
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回状态为收藏的错题以及评论嵌套")
    public Msg list10()
    {
        //1.首先找到状态为收藏（0）的错题
        List<QuestionMistake> questionMistakeList=questionMistakeServiceImpi.findByMistakeStatus(0);
        //2.定义一个题库List对象questionBankList，目的是为了便于接下来调用findByQuestionId2方法
        List<QuestionBank> questionBankList=new ArrayList<>();
        //3.再定义一个一个题库List对象questionBankList2，目的是为了便于接下来循环后使用add方法接收循环后的数据
        List<QuestionBank> questionBankList2=new ArrayList<>();
        //注意这里不能再用Object，因为接下来questionBankList2还要被遍历
        //如果取值的话还可以直接定义可以接收任何数据的Object
        //但循环遍历对象，用Object会出现类型报错
      //  List<Object> questionBankList3=new ArrayList<>();
        //4.遍历已经找到的状态为收藏（0）的题目
        for (QuestionMistake questionMistake:questionMistakeList)
        {
            //5.用之前2.定义的questionBankList使用findByQuestionId2，
            //将questionMistake.getQuestionId()作为findByQuestionId2的参数
            //这样就可以在题库表里找到收藏表中状态为收藏的所有题目
            questionBankList=questionBankServiceImpi.findByQuestionId2(questionMistake.getQuestionId());
            //遍历questionBankList，目的是为下一步questionBankList2使用add方法接收循环后的数据
            for(QuestionBank questionBank:questionBankList)
            {
                //add方法接收数据
                questionBankList2.add(questionBank);
            }
         //   questionBankList2=questionBankList;//不能这样写
            // 不能放这里，想要把所有数据遍历出来必须要再加一层循环，否则只能遍历到最后一个数据
         //   questionBankList2.add(questionBankList);
        }
        //questionBankList3.add(questionBankList);

        List<QuestionBankDTO> mistakeDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList2)
        {
            //*1
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            //*1
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            //首先根据questionBank.getQuestionId()找到此questionId对应的评论列表
            List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionBank.getQuestionId());
            List<Object> d=new ArrayList<>();
            QuestionBankDTO mistakeDTO=new QuestionBankDTO();
            //此方法要放在第二层所有for循环最上面
            BeanUtils.copyProperties(questionBank,mistakeDTO);
            //评论嵌套模块
            for (QuestionFeedback questionFeedback :questionFeedbackList){
                //用来根据评论表里的ID找用户表里对应的信息，再由userInfo找到用户名
                UserInfo userInfo=userInfoServiceImpi.findByUserId(questionFeedback.getUserId());
                UserComment user=new UserComment();
                user.setQuestionId(questionFeedback.getQuestionId());
                //将userInfo找到用户名赋值给这个映射字段
                user.setUserName(userInfo.getUserName());
                user.setComment(questionFeedback.getFeedbackContent());
                d.add(user);
            }
            mistakeDTO.setFeedbackContent(d);
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            //这个变量对象不能放到for循环外面，
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            //*3
            if(answerAnalysis==null){
                mistakeDTO.setAnalysisContent("还没有这个问题的答案哦");
                //*3"还没有这个题目的解析"
            }
            else{
                //*2
                mistakeDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            mistakeDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
                a.add(option);
            }
            for (String answers: splitAnswer){
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            mistakeDTO.setQuestionAnswer(c);
            mistakeDTO.setQuestionOption(a);
            mistakeDTOList.add(mistakeDTO);
        }
        return Msg.success().add("questionBank",mistakeDTOList);
    }

    @GetMapping("/list11")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理和状态为收藏的错题
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回状态为收藏的题目")
    public Msg list11(@RequestParam("userId") Integer userId)
    {
        //1.首先找到状态为收藏（0）的错题
        List<QuestionCollect> questionCollectList=questionCollectServiceImpi.findByCollectStatusAndUserId(0,userId);
        //2.定义一个题库List对象questionBankList，目的是为了便于接下来调用findByQuestionId2方法
        List<QuestionBank> questionBankList=new ArrayList<>();
        //3.再定义一个一个题库List对象questionBankList2，目的是为了便于接下来循环后使用add方法接收循环后的数据
        List<QuestionBank> questionBankList2=new ArrayList<>();
        //注意这里不能再用Object，因为接下来questionBankList2还要被遍历
        //如果取值的话还可以直接定义可以接收任何数据的Object
        //但循环遍历对象，用Object会出现类型报错
        //  List<Object> questionBankList3=new ArrayList<>();
        //4.遍历已经找到的状态为收藏（0）的题目
        for (QuestionCollect questionCollect:questionCollectList){
            //5.用之前2.定义的questionBankList使用findByQuestionId2，
            //将questionMistake.getQuestionId()作为findByQuestionId2的参数
            //这样就可以在题库表里找到收藏表中状态为收藏的所有题目
            questionBankList=questionBankServiceImpi.findByQuestionId2(questionCollect.getQuestionId());
            //遍历questionBankList，目的是为下一步questionBankList2使用add方法接收循环后的数据
            for(QuestionBank questionBank:questionBankList){
                //add方法接收数据
                questionBankList2.add(questionBank);
            }
            //   questionBankList2=questionBankList;//不能这样写
            // 不能放这里，想要把所有数据遍历出来必须要再加一层循环，否则只能遍历到最后一个数据
            //   questionBankList2.add(questionBankList);
        }
        //questionBankList3.add(questionBankList);

        List<CollectDTO> collectDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList2)
        {
            //*1
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            //*1
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            QuestionCollect questionCollect=questionCollectServiceImpi.findByQuestionIdAndUserId(questionBank.getQuestionId(),userId);
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            //这个变量对象不能放到for循环外面，
            CollectDTO collectDTO=new CollectDTO();
            BeanUtils.copyProperties(questionBank,collectDTO);
            collectDTO.setCreateTime(questionCollect.getCreateTime());
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            //*3
            if(answerAnalysis==null){
                collectDTO.setAnalysisContent("还没有这个问题的答案哦");
                //*3"还没有这个题目的解析"
            }
            else{
                //*2
                collectDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            //mistakeDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
                a.add(option);
            }
            for (String answers: splitAnswer){
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            collectDTO.setQuestionAnswer(c);
            collectDTO.setQuestionOption(a);
            collectDTOList.add(collectDTO);
        }
        return Msg.success().add("questionBank",collectDTOList);
    }
/*
    @GetMapping("/list12")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理//随机数产生指定题数
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理")
    public Msg list12()
    {
        //首先找到题目科目为1的题目列表
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionSubject("1");
        //要动态数组就完美了
        int arr[] = new int[10];
        int arr2[] = new int[10];
      //  List<String> arr2=new ArrayList<>();
        List<QuestionBank> ArrAdd=new ArrayList<>();//接收数据
        for(QuestionBank questionBank:questionBankList)
        {
            int  getQuestionId=questionBank.getQuestionId();
            // Random random=new Random();
            //循环10在取值的时候进行判断，如果id一样则回到上一步重新取
            int random = (int) ( Math.random () * 5 );
            for (int i = 0; i < 5; i++)
            {
                arr [i] =getQuestionId;
                for (int j = 0; j < i; j++)
                {
                    //如果取到的值相同，即题目相同，那么回到上一步再重新取值
                    if (arr[i] == arr[j])
                    {
                        i--;
                        break;
                    }
                }
                //随机数生成1到5的数(int) (Math.random()*5+1)
                arr2[i]=(arr[random]);
                ArrAdd.add(arr2);
            }
        }

        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        //数据改装
        for(QuestionBank questionBank:ArrAdd)
        {

           // questionBank.getQuestionId();
            //*1
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            //*1
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            //*3
            if(answerAnalysis==null){
                questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");
            }
            else{
                //*2
                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
//              map.put("text:",options);
                a.add(option);
            }
            for (String answers: splitAnswer)
            {
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            questionBankDTO.setQuestionAnswer(c);
            questionBankDTO.setQuestionOption(a);
            questionBankDTOList.add(questionBankDTO);
        }
        return Msg.success().add("questionBank",questionBankDTOList);
    }*/

    @GetMapping("/list13")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理和状态为收藏的错题以及评论嵌套
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回状态为收藏的错题以及评论嵌套/时间转换")
    public Msg list13(@RequestParam("userId") Integer userId)
    {
        List<QuestionMistake> questionMistakeList=questionMistakeServiceImpi.findByMistakeStatusAndUserId(0,userId);
        List<QuestionBank> questionBankList=new ArrayList<>();
        List<QuestionBank> questionBankList2=new ArrayList<>();
        for (QuestionMistake questionMistake:questionMistakeList)
        {
            questionBankList=questionBankServiceImpi.findByQuestionId2(questionMistake.getQuestionId());
            for(QuestionBank questionBank:questionBankList)
            {
                questionBankList2.add(questionBank);
            }
        }

        List<MistakeDTO> mistakeDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList2)
        {
            /**时间处理1*/
            QuestionMistake questionMistake=questionMistakeServiceImpi.findByQuestionIdAndUserId(questionBank.getQuestionId(),userId);
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionBank.getQuestionId());
            List<Object> d=new ArrayList<>();
            MistakeDTO mistakeDTO=new MistakeDTO();
            BeanUtils.copyProperties(questionBank,mistakeDTO);
            /**时间处理2*/
            mistakeDTO.setCreateTime(questionMistake.getCreateTime());
            mistakeDTO.setUpdateTime(questionMistake.getUpdateTime());
            for (QuestionFeedback questionFeedback :questionFeedbackList)
            {
                UserInfo userInfo=userInfoServiceImpi.findByUserId(questionFeedback.getUserId());
                UserComment user=new UserComment();
                user.setQuestionId(questionFeedback.getQuestionId());
         /***/       user.setUserName(userInfo.getUserName());
                user.setComment(questionFeedback.getFeedbackContent());
                d.add(user);
            }
            mistakeDTO.setFeedbackContent(d);
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            if(answerAnalysis==null){
                mistakeDTO.setAnalysisContent("还没有这个问题的答案哦");
            }
            else{
                mistakeDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            mistakeDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
                a.add(option);
            }
            for (String answers: splitAnswer){
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            mistakeDTO.setQuestionAnswer(c);
            mistakeDTO.setQuestionOption(a);
            mistakeDTOList.add(mistakeDTO);
        }

        return Msg.success().add("questionBank",mistakeDTOList);
    }

    @GetMapping("/list13/2")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理和状态为收藏的错题以及评论嵌套
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回状态为收藏的错题以及评论嵌套/时间转换")
    public Msg list132(@RequestParam("userId") Integer userId)
    {
        List<QuestionCollect> questionCollectList=questionCollectServiceImpi.findByCollectStatusAndUserId(0,userId);
        List<QuestionBank> questionBankList=new ArrayList<>();
        List<QuestionBank> questionBankList2=new ArrayList<>();
        for (QuestionCollect questionCollect:questionCollectList)
        {
            questionBankList=questionBankServiceImpi.findByQuestionId2(questionCollect.getQuestionId());
            for(QuestionBank questionBank:questionBankList)
            {
                questionBankList2.add(questionBank);
            }
        }

        List<CollectDTO> collectDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList2)
        {
            /**时间处理1*/
            QuestionMistake questionMistake=questionMistakeServiceImpi.findByQuestionIdAndUserId(questionBank.getQuestionId(),userId);
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionBank.getQuestionId());
            List<Object> d=new ArrayList<>();
            CollectDTO collectDTO=new CollectDTO();
            BeanUtils.copyProperties(questionBank,collectDTO);
            /**时间处理2*/
            collectDTO.setCreateTime(questionMistake.getCreateTime());

            for (QuestionFeedback questionFeedback :questionFeedbackList)
            {
                UserInfo userInfo=userInfoServiceImpi.findByUserId(questionFeedback.getUserId());
                UserComment user=new UserComment();
                user.setQuestionId(questionFeedback.getQuestionId());
                /***/       user.setUserName(userInfo.getUserName());
                user.setComment(questionFeedback.getFeedbackContent());
                d.add(user);
            }
          //  collectDTO.setFeedbackContent(d);
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            if(answerAnalysis==null){
                collectDTO.setAnalysisContent("还没有这个问题的答案哦");
            }
            else{
                collectDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
          //  collectDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
                a.add(option);
            }
            for (String answers: splitAnswer){
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            collectDTO.setQuestionAnswer(c);
            collectDTO.setQuestionOption(a);
            collectDTOList.add(collectDTO);
        }

        return Msg.success().add("questionBank",collectDTOList);
    }


    @GetMapping("/list14")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理和状态为收藏的错题以及评论嵌套
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回状态为收藏的错题以及评论嵌套")
    public Msg list14()
    {
        List<QuestionBank> questionBankList111=questionBankServiceImpi.findByQuestionSubject("数学");
        List<QuestionBank> questionBankList=new ArrayList<>();
        List<QuestionBank> questionBankList2=new ArrayList<>();
        for (QuestionBank questionBank:questionBankList111)
        {
            questionBankList=questionBankServiceImpi.findByQuestionId2(questionBank.getQuestionId());
            for(QuestionBank questionBank11:questionBankList)
            {
                questionBankList2.add(questionBank11);
            }
        }
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList2)
        {
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionBank.getQuestionId());
            List<Object> d=new ArrayList<>();
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            for (QuestionFeedback questionFeedback :questionFeedbackList){
                UserInfo userInfo=userInfoServiceImpi.findByUserId(questionFeedback.getUserId());
                UserComment user=new UserComment();
                user.setQuestionId(questionFeedback.getQuestionId());
                /***/       user.setUserName(userInfo.getUserName());
                user.setComment(questionFeedback.getFeedbackContent());
                d.add(user);
            }
            questionBankDTO.setFeedbackContent(d);
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            if(answerAnalysis==null){
                questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");
            }
            else{
                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
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
        return Msg.success().add("questionBank",questionBankDTOList);
    }

    @GetMapping("/list15")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回题目科目为数学的题目列表以及评论嵌套")
    public Msg list15()
    {
        List<QuestionBank> questionBankList=questionBankServiceImpi.findByQuestionSubject("数学");
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList)
        {
            //*1
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            //*1
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();

            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            //*3
            if(answerAnalysis==null){
                questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");
                //*3"还没有这个题目的解析"
            }
            else{
                //*2
                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            // questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            //*2
            questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//
            for (String options :splitOption)
            {
                Option option=new Option();
                option.setText(options);
//              map.put("text:",options);
                a.add(option);
            }
            for (String answers: splitAnswer){
                Option option = new Option();
                option.setText(answers);
                c.add(option);
            }
            // questionBankDTO.setQuestionAnswer();
//            if (answerAnalysis.getQuestionId()==questionBank1.getQuestionId()){
//                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
//            }
//            if(questionScore.getQuestionType()==questionBank1.getQuestionType()){
//                questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
//            }
            questionBankDTO.setQuestionAnswer(c);
            questionBankDTO.setQuestionOption(a);
            questionBankDTOList.add(questionBankDTO);
        }
        return Msg.success().add("questionBank",questionBankDTOList);
    }

    @GetMapping("/list16")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理和状态为收藏的错题以及评论嵌套
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回题目科目为英语的题目列表以及评论嵌套")
    public Msg list16()
    {
        List<QuestionBank> questionBankList111=questionBankServiceImpi.findByQuestionSubject("英语");
        List<QuestionBank> questionBankList=new ArrayList<>();
        List<QuestionBank> questionBankList2=new ArrayList<>();
        for (QuestionBank questionBank:questionBankList111)
        {
            questionBankList=questionBankServiceImpi.findByQuestionId2(questionBank.getQuestionId());
            for(QuestionBank questionBank11:questionBankList)
            {
                questionBankList2.add(questionBank11);
            }
        }
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList2)
        {
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionBank.getQuestionId());
            List<Object> d=new ArrayList<>();
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            for (QuestionFeedback questionFeedback :questionFeedbackList){
                UserInfo userInfo=userInfoServiceImpi.findByUserId(questionFeedback.getUserId());
                UserComment user=new UserComment();
                user.setQuestionId(questionFeedback.getQuestionId());
                /***/       user.setUserName(userInfo.getUserName());
                user.setComment(questionFeedback.getFeedbackContent());
                d.add(user);
            }
            questionBankDTO.setFeedbackContent(d);
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            if(answerAnalysis==null){
                questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");
            }
            else{
                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
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
        return Msg.success().add("questionBank",questionBankDTOList);
    }

    @GetMapping("/list17")//需要核心处理逻辑，既有数据字段拼接，又有空值异常处理和状态为收藏的错题以及评论嵌套
    @ApiOperation(value = "",notes = "题库、解析、分数多表数据拼接和空指针处理返回题目科目为计算机的题目列表以及评论嵌套")
    public Msg list17()
    {
        List<QuestionBank> questionBankList111=questionBankServiceImpi.findByQuestionSubject("计算机");
        List<QuestionBank> questionBankList=new ArrayList<>();
        List<QuestionBank> questionBankList2=new ArrayList<>();
        for (QuestionBank questionBank:questionBankList111)
        {
            questionBankList=questionBankServiceImpi.findByQuestionId2(questionBank.getQuestionId());
            for(QuestionBank questionBank11:questionBankList)
            {
                questionBankList2.add(questionBank11);
            }
        }
        List<QuestionBankDTO> questionBankDTOList=new ArrayList<>();
        for(QuestionBank questionBank:questionBankList2)
        {
            AnswerAnalysis answerAnalysis=questionAnalysisServiceImpi.findByQuestionId(questionBank.getQuestionId());
            QuestionScore questionScore=questionScoreService.findByQuestionType(questionBank.getQuestionType());
            List<QuestionFeedback> questionFeedbackList=questionFeedbackServiceImpi.findByQuestionId(questionBank.getQuestionId());
            List<Object> d=new ArrayList<>();
            QuestionBankDTO questionBankDTO=new QuestionBankDTO();
            BeanUtils.copyProperties(questionBank,questionBankDTO);
            for (QuestionFeedback questionFeedback :questionFeedbackList){
                UserInfo userInfo=userInfoServiceImpi.findByUserId(questionFeedback.getUserId());
                UserComment user=new UserComment();
                user.setQuestionId(questionFeedback.getQuestionId());
                /***/       user.setUserName(userInfo.getUserName());
                user.setComment(questionFeedback.getFeedbackContent());
                d.add(user);
            }
            questionBankDTO.setFeedbackContent(d);
            List<Object> a=new ArrayList<>();
            List<Object> c=new ArrayList<>();
            String[] splitOption=questionBank.getQuestionOption().split(",");
            String[] splitAnswer=questionBank.getQuestionAnswer().split(",");
            if(answerAnalysis==null){
                questionBankDTO.setAnalysisContent("还没有这个问题的答案哦");
            }
            else{
                questionBankDTO.setAnalysisContent(answerAnalysis.getAnalysisContent());
            }
            questionBankDTO.setQuestionScore(questionScore.getQuestionScore());
            for (String options :splitOption)
            {
                Option option=new Option();
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
        return Msg.success().add("questionBank",questionBankDTOList);
    }
}
