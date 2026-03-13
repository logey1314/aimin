package com.logey.aiminmcpserver.tools;

import com.logey.aiminmcpserver.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TabbleInfoTool {

    private final TableInfoService tableInfoService;

    @Tool(name = "getTableNames", description = "获取全部表名以及表的描述信息")
    public List<Map<String, String>> getWeather(String time) {
        List<Map<String, String>> tableNames = tableInfoService.getTableNames();
        return tableNames;
    }

    @Tool(name = "getTableInfo", description = "获取表名以及表字段描述信息")
    public List<Map<String, String>> getTableInfo(String tableName) {
        List<Map<String, String>> tableInfo = tableInfoService.getTableInfo(tableName);
        return tableInfo;
    }

    @Tool(name = "execSQL", description = "执行查询sql语句")
    public List<Map<String, Object>> executeSql(String sql) {
        System.out.println("sql:" + sql);
        return tableInfoService.executeSql(sql);
    }

}