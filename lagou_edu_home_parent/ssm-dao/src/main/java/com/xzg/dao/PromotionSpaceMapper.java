package com.xzg.dao;

import com.xzg.domain.PromotionSpace;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PromotionSpaceMapper {

    //查询所有广告位
    public List<PromotionSpace> findAllPromotionSpace();

    //添加广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);

    //修改广告位
    public void updatePromotionSpace(PromotionSpace promotionSpace);

    //回显广告位名称
    public PromotionSpace findPromotionSpaceById(int id);
}
