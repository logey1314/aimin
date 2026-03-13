package com.logey.aiminmcpserver.stdio;

import com.logey.aiminmcpserver.stdio.tool.WeatherTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AiminMcpServerStdioApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiminMcpServerStdioApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider getWeatherTool(WeatherTool computedTool) {
        return MethodToolCallbackProvider.builder().toolObjects(computedTool).build();
    }

}
