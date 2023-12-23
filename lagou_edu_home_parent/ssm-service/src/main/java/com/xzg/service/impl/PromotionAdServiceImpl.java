package com.xzg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzg.dao.PromotionAdMapper;
import com.xzg.domain.PromotionAd;
import com.xzg.domain.PromotionAdVo;
import com.xzg.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Override
    public PageInfo<PromotionAd>  findAllPromotionAd(PromotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());
        List<PromotionAd> allAd = promotionAdMapper.findAllPromotionAd();
        PageInfo<PromotionAd> adPageInfo = new PageInfo<>(allAd);
        return adPageInfo;
    }
    //新建广告
    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.savePromotionAd(promotionAd);

    }
    //修改广告
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAdMapper.updatePromotionAd(promotionAd);
    }
    //回显广告
    @Override
    public PromotionAd findPromotionAdById(int id) {
        PromotionAd promotionAdById = promotionAdMapper.findPromotionAdById(id);
        return promotionAdById;
    }
    //修改广告状态
    @Override
    public void updatePromotionAdStatus(int id,int status) {
        //补全信息
        Date date = new Date();
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setUpdateTime(date) ;
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAdMapper.updatePromotionAdStatus(promotionAd);

    }
}
