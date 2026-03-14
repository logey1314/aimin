package com.logey.aiminmcpserver.stdio.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class WeatherTool {

    @Tool(name = "getWeather", description = "根据城市获取天气信息")
    public String getWeather(String city) {
        return city + ",今天有暴雨";
    }
}