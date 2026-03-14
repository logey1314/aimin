package com.logey.aiminmcpserver.service;


import com.logey.aiminmcpserver.mapper.TableInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TableInfoService {

    private final TableInfoMapper tableInfoMapper;

    public List<Map<String,String>> getTableNames(){
        return tableInfoMapper.tableNames();
    }

    public List<Map<String,String>> getTableInfo(String tableName){
        System.out.println("——————————查表————————————————");
        return tableInfoMapper.getableInfo(tableName);
    }

    public List<Map<String,Object>> executeSql(String sql){
        return tableInfoMapper.executeSql(sql);
    }




}
