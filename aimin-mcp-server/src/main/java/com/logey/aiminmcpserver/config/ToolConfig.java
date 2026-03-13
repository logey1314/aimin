package com.logey.aiminmcpserver.config;


import com.logey.aiminmcpserver.tools.TabbleInfoTool;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToolConfig {

    @Bean
    public ToolCallbackProvider  weatherTool(TabbleInfoTool tabbleInfoTool){
        return MethodToolCallbackProvider.builder().toolObjects(tabbleInfoTool).build();
    }

}
