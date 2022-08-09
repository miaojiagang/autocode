package com.leigang.bi.fast.autocode.domain;

/**
 * Created by Administrator on 2017/11/8.
 */
public class TemplateModel {
    String template;
    String toFileName;
    String toFilePath;

    public TemplateModel(String template, String toFileName, String toFilePath) {
        this.template = template;
        this.toFilePath = toFilePath;
        this.toFileName = toFileName;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getToFileName() {
        return toFileName;
    }

    public void setToFileName(String toFileName) {
        this.toFileName = toFileName;
    }

    public String getToFilePath() {
        return toFilePath;
    }

    public void setToFilePath(String toFilePath) {
        this.toFilePath = toFilePath;
    }
}
