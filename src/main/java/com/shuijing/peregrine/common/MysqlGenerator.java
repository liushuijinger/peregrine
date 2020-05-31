package com.shuijing.peregrine.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 刘水镜
 * @blog https://liushuijinger.blog.csdn.net
 * @date 2020/04/26
 */
public class MysqlGenerator {

	public static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/peregrine?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai";
	public static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DATABASE_USERNAME = "root";
	public static final String DATABASE_PASSWORD = "123456";

	public static final String OUT_PUT_PATH = "/src/main/java";
	public static final String TEMPLATES_MAPPER_XML_PATH = "/templates/mapper.xml.ftl";
	public static final String XML_PATH = "/src/main/resources/mapper/";
	public static final String XML_POSTFIX = "Mapper";

	public static final String AUTHOR = "刘水镜";
	public static final String PARENT_PACKAGE = "com.shuijing.peregrine";
	public static final String SUPER_ENTITY_CLASS = "com.shuijing.peregrine.common.base.BaseEntity";
	public static final String[] SUPER_ENTITY_COLUMNS = { "id", "create_time", "update_time", "creator", "updator" };

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
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + OUT_PUT_PATH);
		gc.setAuthor(AUTHOR);
		gc.setOpen(false);
		gc.setServiceName("%sService");
		gc.setBaseResultMap(true);
		gc.setActiveRecord(true);
		gc.setBaseColumnList(true);
		gc.setSwagger2(true);
		gc.setFileOverride(true);
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(DATABASE_URL);
		dsc.setDriverName(DATABASE_DRIVER);
		dsc.setUsername(DATABASE_USERNAME);
		dsc.setPassword(DATABASE_PASSWORD);
		mpg.setDataSource(dsc);

		// 包配置
		PackageConfig pc = new PackageConfig();
		// pc.setModuleName(scanner("模块名"));
		pc.setParent(PARENT_PACKAGE);
		mpg.setPackageInfo(pc);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig(TEMPLATES_MAPPER_XML_PATH) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + XML_PATH + tableInfo.getEntityName() + XML_POSTFIX + StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(SUPER_ENTITY_CLASS);
		strategy.setSuperEntityColumns(SUPER_ENTITY_COLUMNS);
		strategy.setEntityLombokModel(true);
		strategy.setEntityBuilderModel(true);
		strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));

		strategy.setControllerMappingHyphenStyle(true);
		strategy.setRestControllerStyle(true);

		// 配置自动填充字段（Entity 会添加相应注解）
		List<TableFill> tableFillList = new ArrayList<>();
		tableFillList.add(new TableFill("creator", FieldFill.INSERT));
		tableFillList.add(new TableFill("updator", FieldFill.INSERT_UPDATE));
		strategy.setTableFillList(tableFillList);

		mpg.setStrategy(strategy);
		// 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}
