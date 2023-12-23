package com.xzg.controller;

import com.xzg.domain.ResourceCategory;
import com.xzg.domain.ResponseResult;
import com.xzg.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {
    @Autowired
    private ResourceCategoryService resourceCategoryService;
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        ResponseResult result = new ResponseResult(true, 200, "查询成功", allResourceCategory);
        return result;

    }
}
