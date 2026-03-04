package com.logey.aimin.satoken.user;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;

public class UserStpUtil {

    public static StpLogic stpLogic = new StpLogic("USER");
    public static final String PC = "PC";
    public static final String MINI = "MINI";

    //小程序登录
    public static SaTokenInfo miniLogin(String id) {
        SaLoginModel config = new SaLoginModel();
        config.setIsWriteHeader(false);
        config.setDevice(MINI);
        stpLogic.login(id, config);

        return stpLogic.getTokenInfo();
    }

    //pc端登录
    public static void pcLogin(String key) {
        SaLoginModel config = new SaLoginModel();
        config.setIsWriteHeader(false);
        config.setDevice(PC);
        stpLogic.login(key, config);
    }

}
