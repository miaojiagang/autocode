package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.util.PathUtil;
import com.leigang.bi.fast.autocode.util.StringUtil;
import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.JavaDoc;
import com.leigang.bi.fast.autocode.conf.Table;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.*;

public abstract class Generator {
    protected Config config;
    protected GeneratorConfig gConfig;
    protected Table table;

    protected String targetProject;
    protected String toFilePath;
    protected String targetPackage;
    protected String basePackage;
    protected JavaDoc javaDoc;

    protected List<String> ignoreList = new ArrayList<>(Arrays.asList("exp1","exp2","exp3","exp4","exp5","exp6","exp7","exp8",
            "exp9","exp10"));

    public Generator(Config config, GeneratorConfig gConfig, Table table) {
        this.gConfig = gConfig;
        this.table = table;
        this.basePackage = config.getTargetPackage();
        this.targetProject = config.getTargetProject();
        this.toFilePath = config.getToFilePath();
        this.targetPackage = config.getTargetPackage();
        this.javaDoc = gConfig.getJavaDoc();
        this.config = config;
    }

    protected String mergeTemplate(String targetTemplate, Map<String, Object> generatorContext) throws Exception {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
        VelocityContext context = new VelocityContext();
        context.put("table", table);
        context.put("ignoreList", ignoreList);
        context.put("config", this.config);
        context.put("gConfig", this.gConfig);
        context.put("basePackage", basePackage);
        context.put("targetPackage", targetPackage);
        context.put("toFilePath", toFilePath);
        context.put("tableName", table.getTableName());
        context.put("fieldName", table.getFieldName());
        context.put("className", table.getClassName());
        context.put("javaDoc", javaDoc);
        context.put("generatorContext", generatorContext);

        StringWriter w = new StringWriter();
        targetTemplate = targetTemplate.replace("\\","/").replace("//","/");
        String templatePath = targetTemplate.substring(0,targetTemplate.lastIndexOf("/"));
        String templateName = targetTemplate.substring(targetTemplate.lastIndexOf("/")+1);
        String fileDir = null;
        if(templatePath.indexOf(":")==-1){
            fileDir = (PathUtil.getRootPath()+templatePath).replace("//","/");
        }else{
            fileDir = templatePath;
        }
        VelocityEngine ve = new VelocityEngine();
        Properties properties = new Properties();
        properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, fileDir); //此处的fileDir可以直接用绝对路径来
        ve.init(properties);
        Template t = ve.getTemplate(templateName,"UTF-8");
        t.merge(context,w);
        return w.getBuffer().toString();
    }

	public String getPath(String toFilePath,String toFileName) {
		StringBuffer buf = new StringBuffer();
        if(GeneratorConfig.TYPE_CONFIG.equals(gConfig.getType())){
            buf.append(PathUtil.getRootPath()).append(toFilePath);
            buf.append(table.getModuleName() + "/");
            buf.append(table.getClassName());
        }else{
            buf.append(targetProject).append(toFilePath).append(StringUtil.packge2path(targetPackage)).append("/");
            if(GeneratorConfig.TYPE_PAGE.equals(gConfig.getType())){
                buf.append(table.getUrlName()).append("/");
            }else if(GeneratorConfig.TYPE_XML.equals(gConfig.getType())){
               buf.append(table.getModuleName()).append("/");
                buf.append(table.getClassName());
            }else {
                buf.append(table.getClassName());
            }
        }
        buf.append(toFileName);
		return buf.toString().replace("//","/");
	}

    public abstract void generate();

}
