package com.duoduo.gencode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 代码生成器
 * @author chengesheng@gmail.com
 * @date 2014-8-3 上午12:30:45
 * @version 1.0.0
 */
public class Generator {

	private String templateDir = System.getProperty("user.dir") + "/src/main/java/com/duoduo/gencode/template";
	private Configuration cfg;
	private Connection connection;

	private void initial() {
		try {
			if (null == cfg) {
				cfg = new Configuration();
			}
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			connection = DBConnectionUtils.getJDBCConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cfg.setObjectWrapper(new DefaultObjectWrapper());
	}

	public void generator(String packageName, String outputPath) throws Exception {
		initial();

		initPackage(outputPath);

		cfg.setDefaultEncoding("utf-8");
		Template entityTemplate = cfg.getTemplate("entity.ftl");
		Template daoTemplate = cfg.getTemplate("dao.ftl");
		Template managerTemplate = cfg.getTemplate("manager.ftl");
		Template serviceTemplate = cfg.getTemplate("service.ftl");
		Template voTemplate = cfg.getTemplate("vo.ftl");
		Template controllerTemplate = cfg.getTemplate("controller.ftl");
		Template formTemplate = cfg.getTemplate("form.ftl");
		Template listTemplate = cfg.getTemplate("list.ftl");
		List<Map<String, Object>> templates = generatorTemplateData();

		String currentDate = getCurrentDate();
		String currentDateTime = getCurrentDatetime();
		for (Map<String, Object> o : templates) {
			// 包名
			o.put("packageName", packageName);
			// 增加日期和时间相关数据
			o.put("currentDate", currentDate);
			o.put("currentDateTime", currentDateTime);

			// 输出到文件
			File file = new File(outputPath + "/model/" + o.get("beanName") + ".java");
			Writer writer = new FileWriter(file);
			entityTemplate.process(o, writer);
			writer.close();

			file = new File(outputPath + "/dao/" + o.get("beanName") + "Dao.java");
			writer = new FileWriter(file);
			daoTemplate.process(o, writer);
			writer.close();

			file = new File(outputPath + "/manager/" + o.get("beanName") + "Manager.java");
			writer = new FileWriter(file);
			managerTemplate.process(o, writer);
			writer.close();

			file = new File(outputPath + "/service/" + o.get("beanName") + "Service.java");
			writer = new FileWriter(file);
			serviceTemplate.process(o, writer);
			writer.close();

			file = new File(outputPath + "/vo/" + o.get("beanName") + "VO.java");
			writer = new FileWriter(file);
			voTemplate.process(o, writer);
			writer.close();

			file = new File(outputPath + "/controller/" + o.get("beanName") + "Controller.java");
			writer = new FileWriter(file);
			controllerTemplate.process(o, writer);
			writer.close();

			String name = o.get("beanName").toString().toLowerCase();
			file = new File(outputPath + "/jsps/" + name);
			if (!file.exists()) {
				file.mkdirs();
			}

			file = new File(outputPath + "/jsps/" + name + "/" + name + "-list.jsp");
			writer = new FileWriter(file);
			listTemplate.process(o, writer);
			writer.close();

			file = new File(outputPath + "/jsps/" + name + "/" + name + "-form.jsp");
			writer = new FileWriter(file);
			formTemplate.process(o, writer);
			writer.close();

			System.out.println("生成:" + o.get("beanName"));
		}
	}

	/**
	 * 初始化包及子包
	 * @param outputPath
	 */
	private void initPackage(String outputPath) {
		File file = new File(outputPath);
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(outputPath + "/model");
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(outputPath + "/dao");
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(outputPath + "/manager");
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(outputPath + "/service");
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(outputPath + "/vo");
		if (!file.exists()) {
			file.mkdirs();
		}

		file = new File(outputPath + "/controller");
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	private List<Map<String, Object>> generatorTemplateData() throws Exception {
		DatabaseMetaData dbmd = connection.getMetaData();
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		String[] tables = {
			"Table"
		};
		ResultSet tableSet = dbmd.getTables(null, null, "%", tables);//
		while (tableSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			String tableName = tableSet.getString("TABLE_NAME");
			ResultSet columnSet = dbmd.getColumns(null, "%", tableName, "%");
			List<Column> columns = new ArrayList<Column>();
			boolean hasDateColumn = false;
			while (columnSet.next()) {
				String columnName = columnSet.getString("COLUMN_NAME");
				String attributeName = handlerColumnName(columnSet.getString("COLUMN_NAME"));
				String columnType = columnSet.getString("TYPE_NAME");
				String attributeType = handlerColumnType(columnSet.getString("TYPE_NAME"));
				Column column = new Column();
				column.setColumnName(columnName);
				column.setColumnType(columnType);
				column.setAttributeName(attributeName);
				column.setAttributeType(attributeType);
				columns.add(column);

				if (attributeType.startsWith("Date")) {
					hasDateColumn = true;
				}
			}
			map.put("tableName", tableName);
			map.put("beanName", handlerTableName(tableName));
			map.put("columns", columns);
			map.put("hasDateColumn", hasDateColumn);
			lists.add(map);
		}
		connection.close();
		return lists;
	}

	public static String handlerColumnName(String oldName) {
		String[] arrays = oldName.split("_");
		String newName = "";
		if (arrays.length > 0) {
			newName = arrays[0];
		}
		for (int i = 1; i < arrays.length; i++) {
			newName += (arrays[i].substring(0, 1).toUpperCase() + arrays[i].substring(1, arrays[i].length()));
		}
		return newName;
	}

	public static String handlerTableName(String oldName) {
		if (oldName.indexOf("_") == -1) {
			return oldName;
		}

		String[] arrays = oldName.split("_");
		String newName = "";
		for (int i = 1; i < arrays.length; i++) {
			newName += (arrays[i].substring(0, 1).toUpperCase() + arrays[i].substring(1, arrays[i].length()));
		}
		return newName;
	}

	public static String handlerColumnType(String oldType) {
		if (oldType.toUpperCase().startsWith("VARCHAR")) {
			return "String";
		}
		if (oldType.toUpperCase().startsWith("INT")) {
			return "Integer";
		}
		if (oldType.toUpperCase().startsWith("DATETIME")) {
			return "Date";
		}
		if (oldType.toUpperCase().startsWith("TIMESTAMP")) {
			return "Date";
		}
		if (oldType.toUpperCase().startsWith("CHAR")) {
			return "String";
		}
		if (oldType.toUpperCase().startsWith("TINYINT")) {
			return "Integer";
		}
		if (oldType.toUpperCase().startsWith("BIT")) {
			return "Integer";
		}
		if (oldType.toUpperCase().startsWith("BIGINT")) {
			return "Long";
		}
		return oldType;
	}

	/** 获取当前时间戳并转换成yyyy-MM-dd hh:mm:ss格式的日期时间字符串 */
	private String getCurrentDatetime() {
		return DateFormat.getDateTimeInstance().format(new Date());
	}

	/** 获取当前时间戳并转换成yyyy-MM-dd格式的日期字符串 */
	private String getCurrentDate() {
		return DateFormat.getDateInstance().format(new Date());
	}

	public static void main(String[] args) throws Exception {
		Generator gen = new Generator();
		gen.generator("com.duoduo.frame", System.getProperty("user.dir") + "/src/com/duoduo/frame");
	}
}
