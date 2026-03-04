package com.logey.aimin.gateway.auth;

import org.springframework.util.AntPathMatcher;

import java.util.LinkedHashMap;
import java.util.Map;

public class StrategyFactory {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    private static final Map<String, LoginCheckStrategy> strategyMap = new LinkedHashMap<>();

    static {
        strategyMap.put("/aimin-auth/public/**", new PublicPathStrategy());
        strategyMap.put("/aimin-auth/test/userLoginCheck", new UserPathStrategy());
        strategyMap.put("/aimin-auth/test/adminLoginCheck", new AdminPathStrategy());

    }

    public static LoginCheckStrategy getStrategy(String requestPath) {
        // 遍历时保持插入顺序（关键改进）
        for (Map.Entry<String, LoginCheckStrategy> entry : strategyMap.entrySet()) {
            if (pathMatcher.match(entry.getKey(), requestPath)) {
                return entry.getValue();
            }
        }
        return new PublicPathStrategy(); // 默认策略
    }
}