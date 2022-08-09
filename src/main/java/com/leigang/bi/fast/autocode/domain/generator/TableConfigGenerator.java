package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;
import com.leigang.bi.fast.autocode.domain.TemplateModel;
import com.leigang.bi.fast.autocode.util.IOUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class TableConfigGenerator extends Generator {
    private Map<String, Object> generatorContext;

    public TableConfigGenerator(Config config, GeneratorConfig gConfig, Table table) {
        super(config, gConfig, table);
        this.targetPackage = this.basePackage + "." + table.getModuleName() + ".model";
        generatorContext = new HashMap<>();
    }


    @Override
    public void generate() {
        try {
            System.out.println("Generating ["+gConfig.getType()+"] class...");
            String templateStr = StringUtils.defaultIfEmpty(gConfig.getTemplate(),"/template/configXml.vm");
            TemplateModel template = new TemplateModel(templateStr, ".xml", "/config/");
            String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(), template.getTemplate()), generatorContext);
            IOUtil.writeCodeFile(getPath(template.getToFilePath(), template.getToFileName()), code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Generate fail：" + e.getMessage());
        }
    }
}
