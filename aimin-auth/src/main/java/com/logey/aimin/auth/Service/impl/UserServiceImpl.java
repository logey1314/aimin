package com.logey.aimin.auth.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.logey.aimin.auth.Mapper.UserMapper;
import com.logey.aimin.auth.Service.Userservice;
import com.logey.aimin.auth.dto.UserDTO;
import com.logey.aimin.auth.entity.User;
import com.logey.aimin.auth.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements Userservice {

    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getUserListJoinAddress(int userId) {
        MPJLambdaWrapper<User> wrapper = new MPJLambdaWrapper<User>()
                .selectAll(User.class)//查询user表全部字段
                .select(Address::getCity, Address::getAddress)
                .leftJoin(Address.class, Address::getUserId, User::getId);
        List<UserDTO> userList = userMapper.selectJoinList(UserDTO.class, wrapper);
        return userList;
    }
}
