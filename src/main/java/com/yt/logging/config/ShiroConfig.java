package com.yt.logging.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getshiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //1设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加shiro内置过滤器
        /**
         * shiro内置过滤器，可实现权限相关的拦截器
         * anon:无需认证 可访问
         * authc:必须认证才可访问
         * user:有rememberMe功能可直接访问
         * perms:有资源才可访问
         * role:有该角色才可访问
         */
        Map<String,String> filterMap = new LinkedHashMap<>();


        filterMap.put("/hello","anon");
        filterMap.put("/login","anon");

        //授权过滤器
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");

        filterMap.put("/*","authc");

        //被拦截的的请求跳转到login页面
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthor");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //关联realm
        securityManager.setRealm(userRealm);

        return securityManager;
    }

    /**
     * 创建Reaml
     */
    @Bean(name="userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

//    配置shiroDialect 用于thymeleaf和shiro标签配合使用
    @Bean
    public ShiroDialect getShiroDialect(){
    
        return new ShiroDialect();
    }


}
