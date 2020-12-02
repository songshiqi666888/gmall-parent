package com.atguigu.gmall1213.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall1213.product.mapper.*;
import com.atguigu.gmall1213.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    private BaseCategory1Mapper baseCategory1Mapper;

    @Autowired
    private BaseCategory2Mapper baseCategory2Mapper;

    @Autowired
    private BaseCategory3Mapper baseCategory3Mapper;

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;


    @Override
    public List<BaseCategory1> getCategory1() {
        //一级属性没有搜索条件
        return baseCategory1Mapper.selectList(null);
    }

    /**
     * 查询二级属性
     *
     * @param category1Id
     * @return
     */
    @Override
    public List<BaseCategory2> getCategory2(Long category1Id) {
        QueryWrapper<BaseCategory2> queryWrapper = new QueryWrapper<>();
        //查询条件
        queryWrapper.eq("category1_id", category1Id);
        List<BaseCategory2> baseCategory2List = baseCategory2Mapper.selectList(queryWrapper);
        return baseCategory2List;
    }

    /**
     * 查询三级属性
     *
     * @param category2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getCategory3(Long category2Id) {
        QueryWrapper<BaseCategory3> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category2_id", category2Id);
        List<BaseCategory3> baseCategory3List = baseCategory3Mapper.selectList(queryWrapper);

        return baseCategory3List;
    }


    /**
     * 查询商品属性值
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {

        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id, category2Id, category3Id);
    }

    /**
     * 保存商品属性信息
     *
     * @param baseAttrInfo
     */
    @Override
    @Transactional//开启事务
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //插入或者更新属性
        if (baseAttrInfo.getId() != null) {
            baseAttrInfoMapper.updateById(baseAttrInfo);
        } else {
            baseAttrInfoMapper.insert(baseAttrInfo);
        }

        //baseAttrValue平台属性值，通过平台属性查平台属性值
        //更新平台属性值的方式是先删除再插入，根据平台属性id进行删除
        //删除条件是：baseAttrValue.attrId=baseAttrInfo.id
        //删除
        QueryWrapper<BaseAttrValue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("attr_id", baseAttrInfo.getId());
        baseAttrValueMapper.delete(queryWrapper);
        //插入
        //根据前端页面传过来的所有平台属性值数据
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList != null && attrValueList.size() > 0) {
            //循环遍历通过平台属性id=平台属性值id进行插入
            for (BaseAttrValue baseAttrValue : attrValueList) {
                //获取平台属性id给attr，因为attr要在该平台属性下就需要进行关联
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(baseAttrValue);
            }

        }

    }

    @Override
    public BaseAttrInfo getAttrInfo(Long attrId) {
        //平台属性
        BaseAttrInfo baseAttrInfo = baseAttrInfoMapper.selectById(attrId);

        if (null!=baseAttrInfo) {
            //查谁new谁
            QueryWrapper<BaseAttrValue> baseAttrValueQueryWrapper = new QueryWrapper<>();
            baseAttrValueQueryWrapper.eq("attr_id", attrId);
            List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.selectList(baseAttrValueQueryWrapper);
            // 将平台属性值结合放入baseAttrInfo 中，此时才能返回！
            baseAttrInfo.setAttrValueList(baseAttrValueList);
        }
        return baseAttrInfo;
    }
}
