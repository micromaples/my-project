package com.micromaple.my.project.server.controller;


import com.micromaple.my.project.common.dto.BaseResult;
import com.micromaple.my.project.common.utils.MapperUtils;
import com.micromaple.my.project.server.domain.SysUser;
import com.micromaple.my.project.server.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Micromaple
 * @since 2022-09-21 21:51:15
 */
@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/get/all")
    public BaseResult getAll() {
        List<SysUser> sysUsers = sysUserService.getBaseMapper().selectList(null);
        return BaseResult.success(sysUsers);
    }
}
