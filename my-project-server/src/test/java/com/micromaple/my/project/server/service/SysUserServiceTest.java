package com.micromaple.my.project.server.service;

import com.micromaple.my.project.server.MyProjectServerApplication;
import com.micromaple.my.project.server.domain.SysUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MyProjectServerApplication.class)
@RunWith(SpringRunner.class)
public class SysUserServiceTest {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 测试新增
     */
    @Test
    public void addTest() {
        SysUser sysUser = new SysUser();
        sysUser.setCode("user");
        sysUser.setName("用户");
        sysUser.setSex("女");
        sysUser.setAge(18);
        boolean save = sysUserService.save(sysUser);
        System.out.println(save);
        // 输出 true 代表插入成功
    }

    /**
     * 测试查询
     */
    @Test
    public void selectTest() {
        List<SysUser> sysUsers = sysUserService.getBaseMapper().selectList(null);
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser.getName());
        }
        /**
         * 输出内容如下：
         * 超级管理员
         * 用户
         */
    }
}
