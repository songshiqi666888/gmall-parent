package com.atguigu.gmall1213.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall1213.product.service.ManageService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "商品基础属性接口")
@RestController
@RequestMapping("admin/product")
public class BaseManageController {
    @Autowired
    ManageService manageService;

    /**
     * 查询所有一级分类信息
     *
     * @return
     */
    @GetMapping("getCategory1")
    public Result<List<BaseCategory1>> getCategory1() {
        List<BaseCategory1> baseCategory1List = manageService.getCategory1();
        return Result.ok(baseCategory1List);
    }

    /**
     * 根据一级分类id查询二级分类信息
     *
     * @param category1Id
     * @return
     */
    @GetMapping("getcategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable("category1Id") Long category1Id) {

        List<BaseCategory2> baseCategory2List = manageService.getCategory2(category1Id);
        return Result.ok(baseCategory2List);
    }

    /**
     * 根据二级分类id，查询三级分类信息
     *
     * @param category2Id
     * @return
     */
    @GetMapping("getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable("category2Id") Long category2Id) {
        List<BaseCategory3> baseCategory3List = manageService.getCategory3(category2Id);
        return Result.ok(baseCategory3List);
    }

    /**
     * 查询商品属性列表
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> getCategory3(@PathVariable("category1Id")Long category1Id,
                                                   @PathVariable("category2Id")Long category2Id,
                                                   @PathVariable("category3Id")Long category3Id
                                                   ){
        List<BaseAttrInfo> attrInfoList = manageService.getAttrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(attrInfoList);
    }

    /**
     * 保存平台属性
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
        return Result.ok();

    }

    /**
     * 查询平台属性值
     * @param attrId
     * @return
     */
    @GetMapping("getAttrValueList/{attrId}")
    public Result<List<BaseAttrValue>> getAttrValueList(@PathVariable Long attrId){
        BaseAttrInfo baseAttrInfo = manageService.getAttrInfo(attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrInfo.getAttrValueList();
        return Result.ok(baseAttrValueList);
    }

    @GetMapping("{page}/{size}")
    public Result<IPage<SpuInfo>> index(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                                        @ApiParam(name = "size", value = "每页记录数", required = true) @PathVariable Long size,
                                        @ApiParam(name = "spuInfo", value = "查询对象", required = false) @PathVariable SpuInfo spuInfo) {
        Page<SpuInfo> pageParam = new Page<>(page, size);

        IPage<SpuInfo> spuInfoIPage = manageService.selectPage(pageParam, spuInfo);
        return Result.ok(spuInfoIPage);
    }
}
