package com.logey.aimin.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.logey.aimin.generator.config.MysqlProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class Generate implements CommandLineRunner {

    private final MysqlProperties properties;
    private static final String AUTHOR = "logey";

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MyBatis-Plus 代码生成器 ===");
        System.out.println("数据库：" + properties.getUrl());

        // 输入模块名
        System.out.print("请输入模块名（如：aimin-auth）：");
        String moduleName = scanner.nextLine().trim();

        // 输入包名
        System.out.print("请输入包名（如：com.logey.aimin.auth）：");
        String parentPackage = scanner.nextLine().trim();

        // 输入表名
        System.out.print("请输入表名，多个用英文逗号分隔（输入 all 生成所有表）：");
        String tables = scanner.nextLine().trim();

        // 是否生成 Controller
        System.out.print("是否生成 Controller？（y/n，默认 n）：");
        boolean generateController = scanner.nextLine().trim().equalsIgnoreCase("y");

        // 生成输出目录
        String outputDir = System.getProperty("user.dir") + "/generate_output";
        String javaOutputDir = outputDir + "/java";
        String xmlOutputDir = outputDir + "/resources/Mapper";

        System.out.println("\n开始生成代码...");
        System.out.println("输出目录：" + outputDir);

        FastAutoGenerator.create(properties.getUrl(), properties.getUsername(), properties.getPassword())
                .globalConfig(builder -> {
                    builder.author(AUTHOR)
                            .outputDir(javaOutputDir)
                            .commentDate("yyyy/MM/dd")
                            .disableOpenDir();
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage)
                            .entity("entity")
                            .mapper("Mapper")
                            .service("Service")
                            .serviceImpl("Service.impl")
                            .controller("Controller")
                            .pathInfo(Collections.singletonMap(
                                    OutputFile.xml,
                                    xmlOutputDir
                            ));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(getTables(tables))
                            .addTablePrefix("t_")
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .mapperBuilder()
                            .superClass("com.github.yulichang.base.MPJBaseMapper")
                            .enableMapperAnnotation()
                            .serviceBuilder()
                            .superServiceClass("com.github.yulichang.extension.mapping.base.MPJDeepService")
                            .superServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .controllerBuilder()
                            .enableRestStyle();

                    if (!generateController) {
                        builder.controllerBuilder().disable();
                    }
                })
                .templateEngine(new BeetlTemplateEngine())
                .execute();

        System.out.println("\n✓ 代码生成完成！");
        System.out.println("Java 文件：" + javaOutputDir + "/" + parentPackage.replace(".", "/"));
        System.out.println("XML 文件：" + xmlOutputDir);
        System.out.println("\n请检查生成的代码，确认无误后手动复制到目标模块：" + moduleName);
    }

    /**
     * 处理表名输入
     */
    protected List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
