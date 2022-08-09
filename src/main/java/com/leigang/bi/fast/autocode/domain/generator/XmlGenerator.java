package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;
import com.leigang.bi.fast.autocode.domain.TemplateModel;
import com.leigang.bi.fast.autocode.util.IOUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class XmlGenerator extends Generator {

    private Map<String, Object> generatorContext;

    public XmlGenerator(Config config, GeneratorConfig gConfig, Table table) {
        super(config, gConfig, table);
        generatorContext = new HashMap<String, Object>();
//        generatorContext.put("domain", this.basePackage + "." + table.getModuleName() + ".model." + table.getClassName());
        generatorContext.put("domain", this.basePackage + ".entity." + table.getClassName());
        generatorContext.put("dao", this.basePackage + ".dao." + table.getClassName());

        this.targetPackage = table.getModuleName();
        String className = table.getClassName();
        generatorContext.put("instanceName", className.substring(0, 1).toLowerCase() + className.substring(1));
        this.targetPackage ="";
    }

    @Override
    public void generate() {
        try {
            System.out.println("Generating ["+gConfig.getType()+"] class...");
            String templateStr = StringUtils.defaultIfEmpty(gConfig.getTemplate(),"/template/mapXml.vm");
            TemplateModel template = new TemplateModel(templateStr, "Dao.xml", "/src/main/resources/mybatis/mapper/");
            String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(), template.getTemplate()), generatorContext);
            IOUtil.writeCodeFile(getPath(template.getToFilePath(), template.getToFileName()), code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Generate fail：" + e.getMessage());
        }
    }


}
