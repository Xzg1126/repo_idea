package com.xzg.controller;

import com.github.pagehelper.PageInfo;
import com.xzg.domain.PromotionAd;
import com.xzg.domain.PromotionAdVo;
import com.xzg.domain.ResponseResult;
import com.xzg.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {


    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(@RequestBody PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> allPromotionAd = promotionAdService.findAllPromotionAd(promotionAdVo);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", allPromotionAd);
        return result;
    }

    /*
    文件上传
    */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file , HttpServletRequest request) throws IOException {

        //1.判断file是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //2.获取项目路径
        //D:\apache-tocat-8.5.56\webapp\ssm-web
        String realPath = request.getServletContext().getRealPath("/");
        //D:\apache-tocat-8.5.56\webapp\
        String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

        //3.获取文件名
        String originalFilename = file.getOriginalFilename();

        //4.生成新文件名
        String newFileName =  System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //5.文件上传
        //D:\apache-tocat-8.5.56\webapp\ upload
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath,newFileName);
        //判断目录存不存在，没有就创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
            System.out.println("创建目录: "+filePath);
        }
        //图片进行上传
        file.transferTo(filePath);

        //6.将文件名和文件路径返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);
        //responseResult 响应前台会转换为json格式   前台解析json串获取map中的值 实现回显
        return responseResult;

    }
    //新建&修改广告接口
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd (PromotionAd promotion){
        if (promotion.getId()==null){
            //添加广告
            promotionAdService.savePromotionAd(promotion);
            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
            return result;
        }else {
            //修改广告
            promotionAdService.updatePromotionAd(promotion);
            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
            return result;
        }
    }
    //回显广告
    @RequestMapping("/findPromotionAdById")
    private ResponseResult findPromotionAdById(@RequestParam int  id){
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        ResponseResult result = new ResponseResult(true, 200, "回显成功", promotionAdById);
        return result;
    }
    //修改广告状态
    @RequestMapping("/updatePromotionAdStatus")
    public  ResponseResult updatePromotionAdStatus(@RequestParam int id,@RequestParam int status){
        promotionAdService.updatePromotionAdStatus(id,status);
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "修改状态成功", map);
        return result;

    }
}
