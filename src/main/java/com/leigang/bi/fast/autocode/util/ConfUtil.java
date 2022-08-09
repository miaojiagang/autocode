package com.leigang.bi.fast.autocode.util;

import java.io.*;
import java.util.List;

import com.leigang.bi.fast.autocode.conf.*;
import com.leigang.bi.fast.autocode.domain.column.MysqlColumn;
import com.thoughtworks.xstream.XStream;
import com.leigang.bi.fast.autocode.conf.*;

/**
 * 
 * <p>
 * 内容摘要:配置解析类
 * </p>
 * <p>
 * 完成日期: 2013年9月7日 下午5:08:37
 * </p>
 * <p>
 * 修改记录:
 * </p>
 * 
 * <pre>
 *    修改日期:
 *    修 改 人:
 *    修改内容:
 * </pre>
 * 
 * @author leigang
 */
public class ConfUtil {
	/**
	 * 
	 * @date：2013年9月7日
	 * @Description：方法功能描述
	 * @param xmlConfig
	 * @return
	 */
	public static Config parse(InputStream xmlConfig) {

		XStream xstream = new XStream();

		xstream.alias("config", Config.class);
		xstream.useAttributeFor(Config.class, "targetPackage");
		xstream.useAttributeFor(Config.class, "targetProject");
		xstream.useAttributeFor(Config.class, "toFilePath");

		xstream.alias("javaDoc", JavaDoc.class);
		xstream.useAttributeFor(JavaDoc.class, "author");
		xstream.useAttributeFor(JavaDoc.class, "createDate");

		xstream.alias("generator", GeneratorConfig.class);
		xstream.useAttributeFor(GeneratorConfig.class, "template");
		xstream.useAttributeFor(GeneratorConfig.class, "type");
		xstream.useAttributeFor(GeneratorConfig.class, "name");
		xstream.useAttributeFor(GeneratorConfig.class, "fileType");

		xstream.alias("jdbcconnection", JdbcConnection.class);
		xstream.useAttributeFor(JdbcConnection.class, "connectionURL");
		xstream.useAttributeFor(JdbcConnection.class, "driverClass");
		xstream.useAttributeFor(JdbcConnection.class, "userId");
		xstream.useAttributeFor(JdbcConnection.class, "password");
		xstream.useAttributeFor(JdbcConnection.class, "infSql");

		xstream.alias("table", Table.class);
		xstream.useAttributeFor(Table.class, "schema");
		xstream.useAttributeFor(Table.class, "moduleName");
		xstream.useAttributeFor(Table.class, "className");
		xstream.useAttributeFor(Table.class, "tableName");
		xstream.useAttributeFor(Table.class, "className");
		xstream.useAttributeFor(Table.class, "fieldName");
		xstream.useAttributeFor(Table.class,"name");

		return (Config) xstream.fromXML(xmlConfig);
	}

	/**
	 *
	 * @date：2013年9月7日
	 * @Description：方法功能描述
	 * @param xmlConfig
	 * @return
	 */
	public static Table parseTable(InputStream xmlConfig) {
		XStream xstream = new XStream();
		xstream.alias("table", Table.class);
		xstream.alias("columns", List.class);
		xstream.alias("column", MysqlColumn.class);
		xstream.useAttributeFor(MysqlColumn.class, "columnName");
		xstream.useAttributeFor(MysqlColumn.class, "inputType");
		return (Table) xstream.fromXML(xmlConfig);
	}

	/**
	 * 
	 * @date：2013年9月7日
	 * @Description：数据库类型到java数据类型转换
	 * @param type
	 * @return
	 */
	public static String type2Suffix(String type) {
		/*
		 * xml domain dao service action vm
		 */
		if (GeneratorConfig.TYPE_XML.equals(type)) {
			return ".xml";
		} else if (GeneratorConfig.TYPE_DAO.equals(type)) {
			return "Dao.java";
		} else if (GeneratorConfig.TYPE_DOMAIN.equals(type)) {
			return ".java";
		} else if (GeneratorConfig.TYPE_SERVICE.equals(type)) {
			return "Service.java";
		} else if (GeneratorConfig.TYPE_ACTION.equals(type)) {
			return "Action.java";
		} else if (GeneratorConfig.TYPE_PAGE.equals(type)) {
			return ".vm";
		} else {
			return "." + type;
		}
	}
}
