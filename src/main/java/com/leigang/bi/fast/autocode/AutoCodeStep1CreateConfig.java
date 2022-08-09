package com.leigang.bi.fast.autocode;

import com.leigang.bi.fast.autocode.domain.column.Column;
import com.leigang.bi.fast.autocode.domain.generator.TableConfigGenerator;
import com.leigang.bi.fast.autocode.util.PathUtil;
import com.leigang.bi.fast.autocode.util.StringUtil;
import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;
import com.leigang.bi.fast.autocode.dao.JdbcDao;
import com.leigang.bi.fast.autocode.util.ConfUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>
 * 内容摘要: 代码生成执行类
 * </p>
 * <p>
 * 完成日期: 2013年9月7日 下午5:00:23
 * </p>
 * <p>
 * 修改记录:
 * </p>
 * <p>
 * <pre>
 *    修改日期:
 *    修 改 人:
 *    修改内容:
 * </pre>
 *
 * @author leigang
 */
public class AutoCodeStep1CreateConfig {
    /**
     * @param configPath
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     * @date：2013年9月7日
     * @Description：方法功能描述
     */
    public static void compiler(String configPath) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        if (null == configPath || "".equals(configPath)) {
            configPath = PathUtil.getPath("config.xml");
        }
        AutoCodeStep2CreateCode.class.getResourceAsStream(configPath);
        InputStream stream = null;
        Config config = null;
        try {
            stream = new FileInputStream(new File(configPath));
            config = ConfUtil.parse(stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                    stream = null;
                } catch (IOException e) {
                }
            }
        }
        // 载入数据库连接信息
        String url = config.getJdbcConnection().getConnectionURL();
        String userId = config.getJdbcConnection().getUserId();
        String password = config.getJdbcConnection().getPassword();
        String driver = config.getJdbcConnection().getDriverClass();
        String infSql = config.getJdbcConnection().getInfSql();
        JdbcDao columnDao = new JdbcDao(url, userId, password, driver);
        for (Table table : config.getTables()) {
            table.setColumns(columnDao.getColumns(infSql, table.getSchema(), table.getTableName()));
            table.setFieldName(StringUtil.toCamel(table.getTableName(), false));
            GeneratorConfig generatorConfig = new GeneratorConfig();
            generatorConfig.setType(GeneratorConfig.TYPE_CONFIG);
            TableConfigGenerator generator =new TableConfigGenerator(config,generatorConfig,table);
            generator.generate();
        }
    }

    private static String mergeTemplate(String targetTemplate,
                                        String targetPackage, Table table, List<Column> columns,
                                        Map<String, Object> generatorContext) throws Exception {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        Velocity.init(p);
        VelocityContext context = new VelocityContext();
        context.put("columns", columns);
        context.put("targetPackage", targetPackage);
        context.put("tableName", table.getTableName());
        context.put("moduleName", table.getModuleName());
        context.put("className", table.getClassName());
        context.put("idFieldName", table.getIdFieldName());
        context.put("idFieldGetName", table.getIdFieldGetName());
        context.put("idColumnName", table.getIdCoulmnName());
        context.put("generatorContext", generatorContext);

        StringWriter w = new StringWriter();
        Velocity.mergeTemplate(targetTemplate, "UTF-8", context, w);

        return w.getBuffer().toString();
    }


    /**
     * @param table
     * @param columns
     * @date：2013年9月7日
     * @Description：查找表的主键字段
     */
    public static void addTableId(Table table, List<Column> columns) {
        for (Column column : columns) {
            if (!StringUtil.isBlank(column.getColumnKey())) {
                table.setIdFieldName(StringUtil.toCamel(column.getColumnName(), false));
                table.setIdCoulmnName(column.getColumnName());
            }
        }
    }

    /**
     * @param args
     * @date：2013年9月7日
     * @Description：程序入口
     */
    public static void main(String[] args) {
        try {
            String configPath = PathUtil.getPath("../config.xml");
            File configFile = new File(configPath);
            if (configFile.exists()) {
                compiler(configPath);
            } else {
                compiler(null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
