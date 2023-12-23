package com.xzg.service.impl;

import com.xzg.dao.PromotionSpaceMapper;
import com.xzg.domain.PromotionSpace;
import com.xzg.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class PromotionSpaceServiceImpl implements PromotionSpaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    //查询所有广告
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.findAllPromotionSpace();
        return allPromotionSpace;
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        //补全信息
        Date date = new Date();
        // 封装PromotionSpace
        UUID uuid = UUID.randomUUID();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setSpaceKey(uuid.toString());
        promotionSpace.setIsDel(0);
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }
    //修改广告
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        //补全信息
        Date date = new Date();
        promotionSpace.setUpdateTime(date);
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
    //回显广告位名称
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpaceById = promotionSpaceMapper.findPromotionSpaceById(id);
        return  promotionSpaceById;
    }


}
