package com.xzg.dao;


import com.xzg.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    //查询资源分类信息列表
    public List<ResourceCategory> findAllResourceCategory();
}
