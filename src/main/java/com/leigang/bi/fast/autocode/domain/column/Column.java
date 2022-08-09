package com.leigang.bi.fast.autocode.domain.column;

import com.leigang.bi.fast.autocode.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * <p>
 * 内容摘要: 数据库列相关信息
 * </p>
 * <p>
 * 完成日期: 2013年9月7日 下午5:09:03
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
public abstract class Column {
	public static final String INPUT_TYPE_TEXT = "text";
	public static final String INPUT_TYPE_TEXTAREA = "textarea";
	public static final String INPUT_TYPE_DATE = "date";
	public static final String INPUT_TYPE_SELECT= "select";

	private String columnName;
	private String columnType;
	private String columnDateType;
	private String columnKey;
	private String dataType;
	private String columnCommnet;
	private Integer columnLength =0;
	private String inputType = INPUT_TYPE_TEXT;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
		setColumnLength();
	}

	public String getColumnDateType() {
		return columnDateType;
	}

	public void setColumnDateType(String columnDateType) {
		this.columnDateType = columnDateType;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
		setColumnLength();
	}

	public String getColumnCommnet() {
		return columnCommnet;
	}

	public void setColumnCommnet(String columnCommnet) {
		this.columnCommnet = columnCommnet;
	}

	public String getFieldName() {
		return StringUtil.toCamel(columnName, false);
	}

	public String getGetName() {
		return StringUtil.toCamel("get_" + columnName, false);
	}

	public String getSetName() {
		return StringUtil.toCamel("set_" + columnName, false);
	}

	abstract public String getFieldType();

	public Integer getColumnLength() {
		return columnLength;
	}

	public void setColumnLength() {
		if(!StringUtil.isBlank(columnType) && !StringUtil.isBlank(dataType)){
			String length = columnType.replace(dataType,"").replace("(","").replace(")","");
			if(length.length()>0&& StringUtils.isNumeric(length)){
				columnLength =  Integer.valueOf(length);
			}
		}
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

}
