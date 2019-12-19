package com.example.demo.Controller.User;

import com.UpYun;
import com.example.demo.VO.ResultVO;
import com.example.demo.config.UpYunConfig;
import com.example.demo.utils.ResultVOUtil;
import com.upyun.UpException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/image")//
@Api(description = "图片操作")
public class ImageController
{
    @Autowired
    private UpYunConfig upYunConfig;
    @PostMapping("/upload")
    @ApiOperation(value = "图片上传",notes = "图片上传操作")
    public ResultVO upload(@RequestParam("file_data") MultipartFile multipartFile) throws IOException,UpException
    {
        UpYun upYun =new UpYun(upYunConfig.getBucketName(),upYunConfig.getUsername(),upYunConfig.getPassword());
        String fileName=String.format("%s.%s", UUID.randomUUID().toString(),multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".")+1));
        upYun.writeFile(fileName,multipartFile.getInputStream(),true,new HashMap<>());
        Map map=new HashMap<>();
        map.put("fileName",fileName);
        map.put("fileUrl","http://"+upYunConfig.getBucketName()+"."+upYunConfig.getHostName()+"/"+fileName);
        return ResultVOUtil.success(map);
    }

}