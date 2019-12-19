package com.example.demo.utils;
import com.example.demo.VO.ResultVO;

//将ResultVO定制得更通用以便在BuyerProductController直接一个“return ResultVOUtil.success(productInfoList);”
//使得BuyerProductController代码更简洁
public class ResultVOUtil
{

    public  static ResultVO success(Object obj)
    {
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(obj);
        return resultVO;
    }

    public  static ResultVO success()
    {
      //重写
        return success(null);
    }

    public  static ResultVO error( int code,String msg)
    {
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setData(msg);
        return resultVO;
    }

}
