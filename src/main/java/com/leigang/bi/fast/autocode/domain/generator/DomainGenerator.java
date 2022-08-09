package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.domain.TemplateModel;
import com.leigang.bi.fast.autocode.util.IOUtil;
import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class DomainGenerator extends Generator {
    private Map<String, Object> generatorContext;

    public DomainGenerator(Config config, GeneratorConfig gConfig, Table table) {
        super(config, gConfig, table);
//        this.targetPackage = this.basePackage + "." + table.getModuleName() + ".model";
        this.targetPackage = this.basePackage + "." + table.getModuleName();
        generatorContext = new HashMap<>();
    }


    @Override
    public void generate() {
        try {
            System.out.println("Generating ["+gConfig.getType()+"] class...");
            String templateStr = StringUtils.defaultIfEmpty(gConfig.getTemplate(),"/template/domain.vm");
            TemplateModel template = new TemplateModel(templateStr, ".java", this.toFilePath);
            String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(), template.getTemplate()), generatorContext);
            IOUtil.writeCodeFile(getPath(template.getToFilePath(), template.getToFileName()), code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Generate fail：" + e.getMessage());
        }
    }
}
