package com.xzg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzg.dao.ResourceMapper;
import com.xzg.domain.Resource;
import com.xzg.domain.ResourceVO;
import com.xzg.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    //资源分页
    @Autowired
    private ResourceMapper resourceMapper;
    @Override
        public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {
        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVO);
        PageInfo<Resource> objectPageInfo = new PageInfo<Resource>(allResource);
        return objectPageInfo;

    }
    //添加资源信息
    @Override
    public void saveResource(Resource resource) {
        //补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }
    //更新资源
    @Override
    public void updateResource(Resource resource ) {
        //补全信息
        resource.setUpdatedTime(new Date());
        resourceMapper.updateResource(resource);
    }
    //删除资源
    @Override
    public void deleteResource(int id) {
        resourceMapper.deleteResource(id);
    }
}
