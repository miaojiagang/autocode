package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.domain.TemplateModel;
import com.leigang.bi.fast.autocode.util.IOUtil;
import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ActionGenerator extends Generator {

    private Map<String, Object> generatorContext;

    public ActionGenerator(Config config, GeneratorConfig gConfig, Table table) {
        super(config, gConfig, table);
        generatorContext = new HashMap<>();
        generatorContext.put("module", table.getModuleName());
        generatorContext.put("domain", this.basePackage + "." + table.getModuleName() + ".model." + table.getClassName());
        generatorContext.put("service", this.basePackage + "." + table.getModuleName() + ".service." + table.getClassName() + "Service");
        this.targetPackage = this.basePackage + ".controller";
    }

    @Override
    public void generate() {
        try {
            System.out.println("Generating ["+gConfig.getType()+"] class...");
            String templateStr = StringUtils.defaultIfEmpty(gConfig.getTemplate(),"/template/action.vm");
//            TemplateModel template = new TemplateModel(templateStr, "Action.java", this.toFilePath);
//            String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(), template.getTemplate()), generatorContext);
//            IOUtil.writeCodeFile(getPath(template.getToFilePath(), template.getToFileName()), code);

            TemplateModel template = new TemplateModel("/template/actionController.vm", "Controller.java",this.toFilePath);
            String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(), template.getTemplate()), generatorContext);
            IOUtil.writeCodeFile(getPath(template.getToFilePath(), template.getToFileName()), code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Generate fail：" + e.getMessage());
        }
    }
}
