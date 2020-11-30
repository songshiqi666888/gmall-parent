package com.atguigu.gmall1213.product.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;

import java.util.List;

public interface ManageService {

    /**
     * 查询所有一级分类信息
     *
     * @return
     */
    List<BaseCategory1> getCategory1();

    /**
     * 根据一级分类id查询二级分类信息
     *
     * @param category1Id
     * @return
     */
    List<BaseCategory2> getCategory2(long category1Id);

    /**
     * 根据二级分类id查询三级分类信息
     *
     * @param category2Id
     * @return
     */
    List<BaseCategory3> getCategory3(long category2Id);

    /**
     * 根据123级id查询商品属性列表
     *  根据分类Id 获取平台属性数据
     *   接口说明：
     *       1，平台属性可以挂在一级分类、二级分类和三级分类
     *       2，查询一级分类下面的平台属性，传：category1Id，0，0；   取出该分类的平台属性
     *       3，查询二级分类下面的平台属性，传：category1Id，category2Id，0；
     *           取出对应一级分类下面的平台属性与二级分类对应的平台属性
     *       4，查询三级分类下面的平台属性，传：category1Id，category2Id，category3Id；
     *           取出对应一级分类、二级分类与三级分类对应的平台属性
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> getAttrInfoList(long category1Id, long category2Id, long category3Id);
}
