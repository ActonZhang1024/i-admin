package com.imcode.sys;

import com.imcode.common.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: acton_zhang
 * @Date: 2020/2/9 8:34 下午
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class ShiroTest {
    @Autowired
    private SecurityManager securityManager;
    @Test
    public void test(){
        String username = "admin";
        //对密码进行加密
        String password = MD5Util.md5_private_salt("123456","ak47");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(token);
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.hasRole("admin"));
        System.out.println(subject.isPermitted("user:create222"));
        // 退出登录
        subject.logout();
    }
}
