package com.logey.aimin.satoken;

import cn.dev33.satoken.config.SaTokenConfig;
import com.logey.aimin.satoken.admin.AdminStpUtil;
import com.logey.aimin.satoken.user.UserStpUtil;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class StpUtilConfig {
    
    @PostConstruct
    public void init(){
        SaTokenConfig adminConfig = new SaTokenConfig();
        adminConfig.setTokenName("aimin-admin-token");
        adminConfig.setTimeout(2592000);
        adminConfig.setActiveTimeout(-1L);
        adminConfig.setIsConcurrent(false);
        adminConfig.setIsShare(true);
        adminConfig.setIsLog(true);
        adminConfig.setTokenStyle("random-64");
        AdminStpUtil.stpLogic.setConfig(adminConfig);

        SaTokenConfig userConfig = new SaTokenConfig();
        userConfig.setTokenName("aimin-auth-token");
        userConfig.setTimeout(2592000);
        userConfig.setActiveTimeout(-1);
        userConfig.setIsConcurrent(false);
        userConfig.setIsShare(true);
        userConfig.setIsLog(true);
        userConfig.setTokenStyle("random-64");
        UserStpUtil.stpLogic.setConfig(userConfig);
    }
}