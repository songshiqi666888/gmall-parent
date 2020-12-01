package com.atguigu.gmall1213.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall1213.product.mapper.*;
import com.atguigu.gmall1213.product.service.ManageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param category1Id
     * @return
     */
    @Override
    public List<BaseCategory2> getCategory2(long category1Id) {
        QueryWrapper<BaseCategory2> queryWrapper = new QueryWrapper<>();
        //查询条件
        queryWrapper.eq("category1_id", category1Id);
        List<BaseCategory2> baseCategory2List = baseCategory2Mapper.selectList(queryWrapper);
        return baseCategory2List;
    }

    /**
     * 查询三级属性
     * @param category2Id
     * @return
     */
    @Override
    public List<BaseCategory3> getCategory3(long category2Id) {
        QueryWrapper<BaseCategory3> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category2_id", category2Id);
        List<BaseCategory3> baseCategory3List = baseCategory3Mapper.selectList(queryWrapper);

        return baseCategory3List;
    }


    @Override
    public List<BaseAttrInfo> getAttrInfoList(long category1Id, long category2Id, long category3Id) {

        return baseAttrInfoMapper.selectBaseAttrInfoList(category1Id, category2Id, category3Id);
    }
}
