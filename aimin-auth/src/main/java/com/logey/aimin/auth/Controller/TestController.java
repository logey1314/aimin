package com.logey.aimin.auth.Controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.logey.aimin.auth.Service.Userservice;
import com.logey.aimin.auth.common.Result;
import com.logey.aimin.auth.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Wrapper;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final Userservice userservice;

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




}
