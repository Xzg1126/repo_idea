package com.xzg.service;

import com.xzg.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {

    //查询所有广告位
    public List<PromotionSpace> findAllPromotionSpace();

    //添加广告
    public void savePromotionSpace(PromotionSpace promotionSpace);

    //修改广告
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    //回显广告位名称
    public PromotionSpace findPromotionSpaceById(int id);
}
