package com.logey.aiminmcpserver.mapper;

import com.github.yulichang.base.MPJBaseMapper;
import entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 表信息查询 Mapper
 * 用于查询数据库表结构和元数据信息
 */
@Mapper
public interface TableInfoMapper extends MPJBaseMapper<User> {
    
    /**
     * 获取当前数据库的所有表名和表注释
     * @return 表名和表注释的列表
     */
    List<Map<String,String>> tableNames();

    /**
     * 获取指定表的详细信息
     * @param tableName 表名
     * @return 表的详细信息
     */
    List<Map<String,String>> getableInfo(String tableName);

    /**
     * 执行动态 SQL 查询
     * 注意: 使用 ${} 存在 SQL 注入风险,仅用于内部可信场景
     * @param sql SQL 语句
     * @return 查询结果
     */
    @Select("${sql}")
    List<Map<String, Object>> executeSql(String sql);
}
