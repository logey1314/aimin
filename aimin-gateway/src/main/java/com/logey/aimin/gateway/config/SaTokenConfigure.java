package com.logey.aimin.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.logey.aimin.gateway.auth.LoginCheckStrategy;
import com.logey.aimin.gateway.auth.StrategyFactory;
import com.logey.aimin.satoken.StpUtilConfig;
import com.logey.aimin.satoken.admin.AdminStpUtil;
import com.logey.aimin.satoken.user.UserStpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * [Sa-Token 权限认证] 配置类
 * @author click33
 */
//@Configuration
//public class SaTokenConfigure {
//    // 注册 Sa-Token全局过滤器
//    @Bean
//    public SaReactorFilter getSaReactorFilter() {
//        System.out.println("————————————SaReactorFilter————————————————");
//        return new SaReactorFilter()
//                // 拦截地址
//                .addInclude("/**")    /* 拦截全部path */
//                // 开放地址
//                .addExclude("/test/login")
//                // 鉴权方法：每次访问进入
//                .setAuth(obj -> {
//                    // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//                    SaRouter.match("/**", "/aimin-auth/test/login", r -> StpUtil.checkLogin());
//
//                    // 权限认证 -- 不同模块, 校验不同权限
////                    SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
////                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
////                    SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
////                    SaRouter.match("/orders/**", r -> StpUtil.checkPermission("orders"));
//
//                    // 更多匹配 ...  */
//                })
//                // 异常处理方法：每次setAuth函数出现异常时进入
//                .setError(e -> {
//                    return SaResult.error(e.getMessage());
//                })
//                ;
//    }
//}

@Configuration
public class SaTokenConfigure {

    /**
     * 注册 Sa-Token 全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 指定 拦截路由
                .addInclude("/**")
                // 指定 排除路由（/aimin-auth/** 下的接口全部放行）
                .addExclude("/aimin-auth/test/userLogin")
                .addExclude("/aimin-auth/test/adminLogin")
                // 指定 认证函数：每次请求执行
                .setAuth(obj -> {
                    String url = SaHolder.getRequest().getUrl();
                    String requestPath = SaHolder.getRequest().getRequestPath();
                    System.out.println(url);
                    System.out.println(requestPath);

                    System.out.println("---------- 进入Sa-Token全局认证 ----------");
                    // SaRouter.match("/test/test", () -> StpUtil.checkLogin());
                    // 示例：对 /api/** 下的接口进行登录校验
                    //SaRouter.match("/**", "/aimin-auth/test/login", r -> StpUtil.checkLogin());

//                    SaRouter.match("/aimin-auth/test/userLoginCheck", r -> UserStpUtil.stpLogic.checkLogin());
//                    SaRouter.match("/aimin-auth/test/adminLoginCheck", r -> AdminStpUtil.stpLogic.checkLogin());
                    LoginCheckStrategy strategy = StrategyFactory.getStrategy(requestPath);
                    strategy.checkAuth();


                })

                // 指定 异常处理函数：每次[认证函数]发生异常时执行此函数
                .setError(e -> {
                    System.out.println("---------- Sa-Token全局异常 ----------");
                    return SaResult.error(e.getMessage());
                });
    }
}
