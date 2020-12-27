package com.atguigu.gmall1213.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall1213.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product/baseTrademark")
public class BaseTradeMarkController {
    @Autowired
    private BaseTrademarkService baseTrademarkService;

    @ApiOperation("商品品牌列表")
    @GetMapping("{page}/{limit}")
    public Result index(@ApiParam(name = "page",value = "当前页码",required = true)
                        @PathVariable("page") Long page,
                        @ApiParam(name = "limit",value = "每页记录数",required = true)
                        @PathVariable("limit") Long limit){
        Page<BaseTrademark> pageParam = new Page<>(page, limit);
        IPage<BaseTrademark> pageModel = baseTrademarkService.selectPage(pageParam);

        return Result.ok(pageModel);
    }

    @ApiOperation("获取商品品牌")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {

        BaseTrademark baseTrademark = baseTrademarkService.getById(id);
        return Result.ok(baseTrademark);
    }



    @ApiOperation("新增商品品牌")
    @PostMapping("save")
    public Result save(@RequestBody BaseTrademark banner){
        baseTrademarkService.save(banner);
        return Result.ok();

    }
    @ApiOperation("修改品牌")
    @PostMapping("update")
    public Result updateById(@RequestBody BaseTrademark banner){
        baseTrademarkService.updateById(banner);
        return Result.ok();

    }

    @ApiOperation("删除品牌")
    @DeleteMapping("delete/{id}")
    public Result remove(@PathVariable Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();

    }



}
