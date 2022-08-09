package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;
import com.leigang.bi.fast.autocode.domain.TemplateModel;
import com.leigang.bi.fast.autocode.util.IOUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class PageGenerator extends Generator {

    private Map<String, Object> generatorContext;
    private String indexPageTemplate;
    private String addPageTemplate;
    private String editPageTemplate;

    public PageGenerator(Config config, GeneratorConfig gConfig, Table table) {
        super(config, gConfig, table);
        generatorContext = new HashMap<String, Object>();
        generatorContext.put("module", table.getModuleName());
        this.targetPackage = table.getModuleName();
    }

    @Override
    public void generate() {
        try {
            System.out.println("Generating [" + gConfig.getType() + gConfig.getName() + "] .vm file...");
            String fileType = !StringUtils.isEmpty(gConfig.getFileType()) ? gConfig.getFileType() : "vm";
            if(StringUtils.isEmpty(gConfig.getTemplate())){
                System.out.println("Generating [" + gConfig.getType() + gConfig.getName() + "] .vm file...ERROR:Please specify the template parameter.");
                return;
            }
            TemplateModel template = new TemplateModel(gConfig.getTemplate(), gConfig.getName() + "." + fileType, "/src/main/webapp/WEB-INF/content/");
            String code = mergeTemplate(StringUtils.defaultString(gConfig.getTemplate(), template.getTemplate()), generatorContext);
            IOUtil.writeCodeFile(getPath(template.getToFilePath(), template.getToFileName()), code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Generate fail：" + e.getMessage());
        }
    }

}
