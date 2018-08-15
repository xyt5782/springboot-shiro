package com.yt.logging.config;

import com.yt.logging.domain.User;
import com.yt.logging.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 */
public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("授权doGetAuthorizationInfo");
        System.out.println("principalCollection:"+principalCollection);
        System.out.println("principalCollection:"+principalCollection.getPrimaryPrincipal());
        System.out.println("principalCollection:"+principalCollection.getRealmNames());
//        User user = (User)SecurityUtils.getSubject().getPrincipal();
//        System.out.println(user);
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
//        info.addStringPermission("user:add");

//        获取当前用户
        Subject subject = SecurityUtils.getSubject();
//        Object obj = subject.getPrincipal();
//        System.out.println("username-obj:"+obj);
//        User user = (User) subject.getPrincipal();
//        System.out.println("username-str:"+user);

        //这里如果报错，可能是认证时返回值没给对像,返回值跟认证给的东西有关
        User user = (User) subject.getPrincipal();
//        数据库获取权限
        User dbUser = userService.fingById(user.getId());
//        System.out.println(dbUser.getPerms());
        //添加权限
        info.addStringPermission(dbUser.getPerms());
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证doGetAuthenticationInfo");
        //模拟数据  账号密码
//        String name = "yt";
//        String password = "123";

//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //认证，判断用户名
//        if(!token.getPrincipal().equals(name)){
            //密码错误

//            return  null;//shiro底层抛出  UnknownAccountException
//        }
//        AuthenticationInfo info = new SimpleAuthenticationInfo("",password,"");

        //数据获取数据
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByName(token.getUsername());
        if (user == null){
            return null;
        }
        char[] password = user.getPassword().toCharArray();


        AuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());
        System.out.println("info:"+info);
        return info;
    }

}
