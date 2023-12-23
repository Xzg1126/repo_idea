package com.xzg.service;

import com.github.pagehelper.PageInfo;
import com.xzg.domain.Resource;
import com.xzg.domain.ResourceVO;

import java.util.List;
import java.util.Map;

public interface ResourceService {
    //资源信息分页&条件查询
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);

    //添加资源信息
    public void saveResource(Resource resource);

    //更新资源
    public void updateResource(Resource resource);

    //删除资源
    public void deleteResource(int id);
}