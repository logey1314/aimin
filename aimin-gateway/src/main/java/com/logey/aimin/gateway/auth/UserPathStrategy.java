package com.logey.aimin.gateway.auth;

import com.logey.aimin.satoken.user.UserStpUtil;

public class UserPathStrategy implements LoginCheckStrategy{
    @Override
    public void checkAuth() {
        UserStpUtil.stpLogic.checkLogin();
    }
}