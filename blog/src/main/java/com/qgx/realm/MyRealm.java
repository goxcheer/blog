package com.qgx.realm;

import com.qgx.entity.Blogger;
import com.qgx.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 *@Author: goxcheer
 *@Date:11:22 2018/7/28
 *@email:604721660@qq.com
 *@decription: 自定义Ream
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 为当前认证用户授权
     * @param principalCollection
     * @return
     */

    @Resource
    private BloggerService bloggerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证当前用户
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String)authenticationToken.getPrincipal();  //从token获取用户名信息
        Blogger blogger = bloggerService.getBloggerByUsername(username);
        if (blogger != null) {
            AuthenticationInfo info = new SimpleAuthenticationInfo(blogger.getUsername(), blogger.getPassword(), this.getName());
            SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger);
            return info;
        }
        return null;
    }
}
