package com.logey.aimin.auth.Controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.logey.aimin.auth.Service.WxService;
import com.logey.aimin.auth.common.Result;
import com.logey.aimin.auth.pojo.Jscode2SessionResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/wx")
@RequiredArgsConstructor
public class WxController {

    private final WxService wxService;

    @GetMapping("/token")
    public Result<SaTokenInfo> token(String code) {
        System.out.println(code);
        Jscode2SessionResult jscode2SessionResult = wxService.wxLogin(code);

        //数据库存储
        //tokene
        StpUtil.login(1);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return Result.success(tokenInfo);
    }
}
