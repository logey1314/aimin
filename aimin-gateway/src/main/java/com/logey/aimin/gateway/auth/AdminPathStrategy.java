package com.logey.aimin.gateway.auth;

import com.logey.aimin.satoken.admin.AdminStpUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminPathStrategy implements LoginCheckStrategy{
    @Override
    public void checkAuth() {
        log.info("admin path check auth");
        AdminStpUtil.stpLogic.checkLogin();
    }
}