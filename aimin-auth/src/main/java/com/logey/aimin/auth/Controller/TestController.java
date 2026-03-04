package com.logey.aimin.auth.Controller;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.logey.aimin.auth.Service.Userservice;
import com.logey.aimin.auth.common.Result;
import com.logey.aimin.auth.entity.User;
import com.logey.aimin.satoken.admin.AdminStpUtil;
import com.logey.aimin.satoken.user.UserStpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import java.sql.Wrapper;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final RedisTemplate<String,Object> template;

    private final Userservice userservice;

    private final RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/test")
    public Result test(){
        try {
            StpUtil.checkLogin();
        }catch (Exception e){
            return Result.error("error");
        }

        return Result.success("yes");
    }

    @GetMapping("/getAll")
    public String getAll(){
        return  userservice.list().toString();
    }

    @GetMapping("/join")
    public  String join(){
           List userListJoinAddress = userservice.getUserListJoinAddress(2);
           for(Object user : userListJoinAddress){
               System.out.println(user);
           }
           return "success";
    }

    @GetMapping("/join2")
    public  String join2(){
        User user = userservice.getByIdDeep(2);
        System.out.println(user);
        return "success";
    }

    @GetMapping("/join3")
    public  String join3(){

        List<User> users = userservice.listDeep(Wrappers.emptyWrapper());
        users.forEach(System.out::println);
        return "success";
    }

    @GetMapping("/redis")
    public  String redis(){
        template.opsForValue().set("name","zhangsan");
        return "success";
    }

    @GetMapping("/login")
    public String login() {

        SaLoginModel saLoginModel = new SaLoginModel();
        saLoginModel.setIsWriteHeader(false);
        StpUtil.login(1);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return tokenInfo.getTokenValue();
    }

    @GetMapping("/checkLogin")
    public String checkLogin() {
        return "success";
    }

    @GetMapping("/redisConnection")
    public String redisConnection() {
        if (redisConnectionFactory instanceof LettuceConnectionFactory) {
            LettuceConnectionFactory lettuceFactory = (LettuceConnectionFactory) redisConnectionFactory;
            LettuceClientConfiguration clientConfig = lettuceFactory.getClientConfiguration();

            // 查看是否启用了池
            if (clientConfig instanceof LettucePoolingClientConfiguration) {
                System.out.println("Redis 使用了连接池！");
                // 可以进一步检查池配置
            } else {
                System.out.println("Redis 没有使用连接池！");
            }
        }

        return "success";
    }

    @GetMapping("/userLogin")
    public String userLogin() {
        SaTokenInfo tokenInfo = UserStpUtil.miniLogin("1");
        return tokenInfo.getTokenValue();
    }

    @GetMapping("/userLoginCheck")
    public String userLoginCheck() {
        return "UserLoginCheck:success";
    }

    @GetMapping("/adminLogin")
    public String adminLogin() {
        SaTokenInfo tokenInfo = AdminStpUtil.login("key: '23'");
        return tokenInfo.getTokenValue();
    }

    @GetMapping("/adminLoginCheck")
    public String adminLoginCheck() {
        return "userLoginCheck: success";
    }






}
