package com.shuijing.peregrine.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import com.shuijing.peregrine.common.base.BaseEntity;

import java.util.Collections;
import java.util.Scanner;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/04/26
 */
public class MysqlGenerator {

    public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/peregrine?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
    public static final String DATABASE_USERNAME = "root";
    public static final String DATABASE_PASSWORD = "123456";

    public static final String OUT_PUT_PATH = "/src/main/java";
    public static final String XML_PATH = "/src/main/resources/mapper/";

    public static final String AUTHOR = "刘水镜";
    public static final String PARENT_PACKAGE = "com.shuijing.peregrine";
    public static final String[] SUPER_ENTITY_COLUMNS = {"id", "create_time", "update_time", "creator", "updator"};

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");

        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD)
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());

        FastAutoGenerator.create(dataSourceConfigBuilder)
                .globalConfig(builder -> {
                    // 设置作者
                    builder.author(AUTHOR)
                            // 开启 swagger 模式
                            .enableSwagger()
                            .disableOpenDir()

                            // 指定输出目录
                            .outputDir(projectPath + OUT_PUT_PATH);
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    builder.parent(PARENT_PACKAGE)
                            // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + XML_PATH))
                            .xml("").moduleName(null)
                    ;
                })
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(scanner("表名，多个英文逗号分割").split(","))
                            // 设置过滤表前缀
                            .addTablePrefix("t_")

                            .mapperBuilder().enableBaseColumnList().enableBaseResultMap()
                            .fileOverride()

                            .entityBuilder().enableLombok().superClass(BaseEntity.class)
                            .addSuperEntityColumns(SUPER_ENTITY_COLUMNS)
                            .enableChainModel()
                            .addTableFills(new Column("creator", FieldFill.INSERT))
                            .addTableFills(new Column("updator", FieldFill.INSERT_UPDATE))
                            .idType(IdType.AUTO)
                            .enableActiveRecord()
                            .fileOverride()

                            .controllerBuilder().enableRestStyle()
//                            .fileOverride()

                            .serviceBuilder().formatServiceFileName("%sService")
//                            .fileOverride()

                    ;
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())

                .injectionConfig(builder -> {
                    builder.customMap(Collections.singletonMap("parent", PARENT_PACKAGE)).fileOverride();
                }).execute();
    }

}
