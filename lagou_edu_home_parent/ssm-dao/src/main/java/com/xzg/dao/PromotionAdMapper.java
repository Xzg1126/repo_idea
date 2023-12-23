package com.xzg.dao;

import com.xzg.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {
    //广告分页查询
    public List<PromotionAd> findAllPromotionAd();

    //新建广告
    public void savePromotionAd (PromotionAd promotionAd);

    //修改广告
    public void updatePromotionAd (PromotionAd promotionAd);
    //修改回显广告信息接口
    public PromotionAd findPromotionAdById(int id);

    //广告状态上下线
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
