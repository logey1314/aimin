package com.logey.aimin.satoken.admin;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpLogic;



public class AdminStpUtil {
    public static StpLogic stpLogic = new StpLogic("ADMIN");

    public static SaTokenInfo login(String key) {
        SaLoginModel config = new SaLoginModel();
        config.setIsWriteHeader(false);
        stpLogic.login(key,config);
        return stpLogic.getTokenInfo();
    }

}
