package com.logey.aiminmcpserver.controller;


import com.logey.aiminmcpserver.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final TableInfoService tableInfoService;

    @GetMapping("/tableNames")
    public String list() {
        List<Map<String, String>> tableNames = tableInfoService.getTableNames();
        System.out.println(tableNames);
        return "ok";
    }


}
