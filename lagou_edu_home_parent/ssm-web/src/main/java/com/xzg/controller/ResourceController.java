package com.xzg.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzg.domain.Resource;
import com.xzg.domain.ResourceVO;
import com.xzg.domain.ResponseResult;
import com.xzg.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    //资源分页
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){
        PageInfo<Resource> allResource = resourceService.findAllResource(resourceVO);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", allResource);
        return result;
    }

    //添加&更新资源信息
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId()==null){
            //添加资源
            resourceService.saveResource(resource);
            ResponseResult result = new ResponseResult(true, 200, "添加成功", null);
            return result;
        }else{
            resourceService.updateResource(resource);
            ResponseResult result = new ResponseResult(true, 200, "修改成功成功", null);
            return result;
        }
    }
    //删除资源
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(int id){
        resourceService.deleteResource(id);
        ResponseResult result = new ResponseResult(true, 200, "删除成功", null);
        return result;
    }
}
