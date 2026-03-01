package com.logey.aimin.auth.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yulichang.extension.mapping.base.MPJDeepService;
import com.logey.aimin.auth.entity.User;

import java.util.List;

public interface Userservice extends MPJDeepService<User> {

    List getUserListJoinAddress(int userId);
}
