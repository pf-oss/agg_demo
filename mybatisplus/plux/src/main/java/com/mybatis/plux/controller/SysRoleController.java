//package com.mybatis.plux.controller;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//
//import com.central.generator.model.SysRole;
//import com.central.generator.service.UserService;
//import com.central.common.model.PageResult;
//import com.central.common.model.Result;
//
///**
// *
// *
// * @author zlt
// * @date 2020-12-28 19:30:55
// */
//@Slf4j
//@RestController
//@RequestMapping("/sysrole")
//@Api(tags = "")
//public class SysRoleController {
//    @Autowired
//    private UserService sysRoleService;
//
//    /**
//     * 列表
//     */
//    @ApiOperation(value = "查询列表")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
//    })
//    @GetMapping
//    public PageResult list(@RequestParam Map<String, Object> params) {
//        return sysRoleService.findList(params);
//    }
//
//    /**
//     * 查询
//     */
//    @ApiOperation(value = "查询")
//    @GetMapping("/{id}")
//    public Result findUserById(@PathVariable Long id) {
//        SysRole model = sysRoleService.getById(id);
//        return Result.succeed(model, "查询成功");
//    }
//
//    /**
//     * 新增or更新
//     */
//    @ApiOperation(value = "保存")
//    @PostMapping
//    public Result save(@RequestBody SysRole sysRole) {
//        sysRoleService.saveOrUpdate(sysRole);
//        return Result.succeed("保存成功");
//    }
//
//    /**
//     * 删除
//     */
//    @ApiOperation(value = "删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//        sysRoleService.removeById(id);
//        return Result.succeed("删除成功");
//    }
//}
