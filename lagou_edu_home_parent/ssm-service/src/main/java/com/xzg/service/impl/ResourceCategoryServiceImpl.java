package com.xzg.service.impl;

import com.xzg.dao.ResourceCategoryMapper;
import com.xzg.domain.ResourceCategory;
import com.xzg.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {
    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();
        return allResourceCategory;
    }
}
