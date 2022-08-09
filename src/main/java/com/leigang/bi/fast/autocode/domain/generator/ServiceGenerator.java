package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;
import com.leigang.bi.fast.autocode.domain.TemplateModel;
import com.leigang.bi.fast.autocode.util.IOUtil;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ServiceGenerator extends Generator {

    private Map<String, Object> generatorContext;

    public ServiceGenerator(Config config, GeneratorConfig gConfig, Table table) {
        super(config, gConfig, table);
        generatorContext = new HashMap<>();
//        generatorContext.put("domain", this.basePackage + "." + table.getModuleName() + ".model." + table.getClassName());
//        if (StringUtils.isEmpty(this.targetPackage)) {
//        this.targetPackage = this.basePackage + "." + table.getModuleName() + ".service";
//        }
        generatorContext.put("domain", this.basePackage + ".entity." + table.getClassName());
        this.targetPackage = this.basePackage  + ".service";
        generatorContext.put("daoPackage", this.basePackage + ".dao");
    }

    @Override
    public void generate() {
        try {
            System.out.println("Generating ["+gConfig.getType()+"] class...");
            String templateStr = StringUtils.defaultIfEmpty(gConfig.getTemplate(),"/template/service.vm");
            TemplateModel template = new TemplateModel(templateStr, "Service.java", this.toFilePath);
            String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(), template.getTemplate()), generatorContext);
            IOUtil.writeCodeFile(getPath(template.getToFilePath(), template.getToFileName()), code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Generate fail：" + e.getMessage());
        }
    }

}
