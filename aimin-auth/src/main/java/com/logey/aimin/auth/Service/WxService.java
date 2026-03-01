package com.logey.aimin.auth.Service;

import com.alibaba.fastjson2.JSONObject;
import com.google.common.base.Joiner;
import com.logey.aimin.auth.pojo.Jscode2SessionResult;
import com.logey.aimin.auth.properties.WXProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WxService {

    private final RestTemplate restTemplate;
    private final WXProperties wxProperties;
    public static final String URL="https://api.weixin.qq.com/sns/jscode2session";

    public Jscode2SessionResult wxLogin(String code){

            Map<String, String> params = new HashMap<>(6);
            params.put("appid", wxProperties.getAppID());
            params.put("secret", wxProperties.getAppSecret());
            params.put("js_code", code);
            params.put("grant_type", "authorization_code");

            String paramsStr = Joiner.on("&").withKeyValueSeparator("=").join(params);
            String url = STR."\{URL}?\{paramsStr}";

            String response = restTemplate.getForObject(url, String.class);
            return JSONObject.parseObject(response, Jscode2SessionResult.class);

    }
}
