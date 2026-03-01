package com.logey.aimin.auth.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

@Data
public class Jscode2SessionResult implements Serializable {
    @JSONField(name = "session_key")
    private String sessionKey;
    private String unionid;
    private String openid;
    private String errmsg;
    private int errcode;
}
