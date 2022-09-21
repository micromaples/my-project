package com.micromaple.my.project.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.micromaple.my.project.server.domain.SysUser;
import com.micromaple.my.project.server.mapper.SysUserMapper;
import com.micromaple.my.project.server.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Micromaple
 * @since 2022-09-21 21:51:15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
