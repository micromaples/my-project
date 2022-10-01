package com.micromaple.my.project.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.micromaple.my.project.server.domain.SysUser;
import com.micromaple.my.project.server.utils.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Micromaple
 * @since 2022-09-21 21:51:15
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface SysUserMapper extends BaseMapper<SysUser> {

}
