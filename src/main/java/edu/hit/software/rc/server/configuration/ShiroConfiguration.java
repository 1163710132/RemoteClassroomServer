/*package edu.hit.software.rc.server.configuration;

import edu.hit.software.rc.server.permission.AccountRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration {

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(@Qualifier("accountRealm") AccountRealm accountRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(accountRealm);
        return securityManager;
    }

    @Bean("shiroFilterChainDefinition")
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        return new DefaultShiroFilterChainDefinition();
    }
}*/
