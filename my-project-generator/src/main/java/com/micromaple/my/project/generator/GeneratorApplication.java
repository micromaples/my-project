package com.micromaple.my.project.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;

/**
 * 代码生成
 * Title: GeneratorApplication
 * Description:
 *
 * @author Micromaple
 */
public class GeneratorApplication {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");

        String finalProjectPath = projectPath + "/my-project-generator";
        // 数据源配置
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig
                .Builder(
                "jdbc:mysql://192.168.110.135:3306/my-project?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT%2B8",
                "root",
                "123456")
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());

        new StrategyConfig.Builder()
                .serviceBuilder()
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImp")
                .build();

        FastAutoGenerator.create(dataSourceConfig)
                .globalConfig(builder -> {
                    builder.author("Micromaple") //设置作者
                            .commentDate("yyyy-MM-dd HH:mm:ss")//注释日期
                            .outputDir(finalProjectPath + "/src/main/java"); //指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com.micromaple.my.project.server")
                            .moduleName("") // 设置父包模块名
                            .entity("domain") //设置entity包名
                            //.other("model.dto") // 设置dto包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, finalProjectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("tb_"); // 设置过滤表前缀
                    builder.entityBuilder().enableLombok();//开启 lombok 模型
                    builder.entityBuilder().enableTableFieldAnnotation();//开启生成实体时生成字段注解
                    builder.controllerBuilder().enableRestStyle();//开启生成@RestController 控制器
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}