package com.leigang.bi.fast.autocode.conf;

import com.leigang.bi.fast.autocode.domain.column.Column;
import com.leigang.bi.fast.autocode.util.StringUtil;
import org.apache.struts2.convention.SEOActionNameBuilder;

import java.util.List;

/**
 * <p>
 * 内容摘要: 表信息
 * </p>
 * <p>
 * 完成日期: 2013年9月7日 下午5:10:49
 * </p>
 * <p>
 * 修改记录:
 * </p>
 * <p/>
 * <pre>
 *    修改日期:
 *    修 改 人:
 *    修改内容:
 * </pre>
 *
 * @author leigang
 */
public class Table {
    private String schema;
    private String moduleName;
    private String tableName;
    private String name;
    private String className;
    private String fieldName;
    private String idFieldName;
    private String idCoulmnName;

    private List<Column> columns;

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     */

    public Table() {
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getClassName() {
        if (className == null || className.length() == 0) {
            className = StringUtil.toCamel(tableName);
        }
        return className;
    }

    public String getUrlName() {
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getIdFieldName() {
        return idFieldName;
    }

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public String getIdCoulmnName() {
        return idCoulmnName;
    }

    public void setIdCoulmnName(String idCoulmnName) {
        this.idCoulmnName = idCoulmnName;
    }

    public String getIdFieldGetName() {
        return StringUtil.toCamel("get_"
                        + new SEOActionNameBuilder("true", "_").build(idFieldName),
                false);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getName() {
        return name;
    }

}
