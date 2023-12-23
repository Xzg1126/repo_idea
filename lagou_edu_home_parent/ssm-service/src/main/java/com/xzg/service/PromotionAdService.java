package com.xzg.service;

import com.github.pagehelper.PageInfo;
import com.xzg.domain.PromotionAd;
import com.xzg.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {

    //分页查询
    public PageInfo<PromotionAd>  findAllPromotionAd(PromotionAdVo promotionAdVo);
    //新建广告
    public void savePromotionAd (PromotionAd promotionAd);
    //更新广告
    void updatePromotionAd(PromotionAd promotionAd);
    //修改回显广告信息接口
    public PromotionAd findPromotionAdById(int id);
    //广告状态上下线
    public void updatePromotionAdStatus(int id,int status);
}
