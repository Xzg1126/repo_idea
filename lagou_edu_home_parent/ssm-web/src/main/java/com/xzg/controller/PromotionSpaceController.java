package com.xzg.controller;

import com.xzg.domain.PromotionSpace;
import com.xzg.domain.ResponseResult;
import com.xzg.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;
    //查询所有广告
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();
        ResponseResult result =
                new ResponseResult(true, 200, "查询成功", allPromotionSpace);
        return result;
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    //添加&修改广告位
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

       if(promotionSpace.getId()==null){
           //添加广告
           promotionSpaceService.savePromotionSpace(promotionSpace);
           ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
           return  result;
       }else {
           promotionSpaceService.updatePromotionSpace(promotionSpace);
           ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
           return result;
       }
    }
    @RequestMapping("/findPromotionSpaceById")
    //回显广告位名称
    public ResponseResult findPromotionSpaceById( int id){
        PromotionSpace promotionSpaceById = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionSpaceById);
        return  result;
    }
}
