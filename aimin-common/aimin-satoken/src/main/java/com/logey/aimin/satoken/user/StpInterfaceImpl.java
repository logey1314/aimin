package com.logey.aimin.satoken.user;

import cn.dev33.satoken.stp.StpInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final RedisTemplate<String, Object> redisTemplate;
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<Object> range = redisTemplate.opsForList().range(loginId.toString(), 0, -1);
        assert range != null;
        return range.stream().map(o -> (String) o).collect(Collectors.toList());
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return List.of();
    }
}
