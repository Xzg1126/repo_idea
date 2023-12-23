package com.xzg.dao;

import com.xzg.domain.Resource;
import com.xzg.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {
    //资源信息分页&条件查询
    public List<Resource> findAllResource(ResourceVO resourceVO);
    //添加资源信息
    public void saveResource(Resource resource);
    //修改资源
    public void updateResource(Resource resource);
    //删除资源
    public void deleteResource(int id);
}
