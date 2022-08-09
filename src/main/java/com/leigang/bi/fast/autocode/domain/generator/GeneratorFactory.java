package com.leigang.bi.fast.autocode.domain.generator;

import com.leigang.bi.fast.autocode.conf.Config;
import com.leigang.bi.fast.autocode.conf.GeneratorConfig;
import com.leigang.bi.fast.autocode.conf.Table;

public class GeneratorFactory {

    public static Generator createGenerator(Config config, GeneratorConfig gConfig, Table table) throws Exception {
        if (GeneratorConfig.TYPE_DOMAIN.equals(gConfig.getType())) {
            return new DomainGenerator(config, gConfig, table);
        } else if (GeneratorConfig.TYPE_ACTION.equals(gConfig.getType())) {
            return new ActionGenerator(config, gConfig, table);
        } else if (GeneratorConfig.TYPE_DAO.equals(gConfig.getType())) {
            return new DaoGenerator(config, gConfig, table);
        } else if (GeneratorConfig.TYPE_DAO_IMPL.equals(gConfig.getType())) {
            return new DaoImplGenerator(config, gConfig, table);
        } else if (GeneratorConfig.TYPE_SERVICE.equals(gConfig.getType())) {
            return new ServiceGenerator(config, gConfig, table);
        } else if (GeneratorConfig.TYPE_SERVICE_IMPL.equals(gConfig.getType())) {
            return new ServiceImplGenerator(config, gConfig, table);
        } else if (GeneratorConfig.TYPE_XML.equals(gConfig.getType())) {
            return new XmlGenerator(config, gConfig, table);
        } else if (GeneratorConfig.TYPE_PAGE.equals(gConfig.getType())) {
            return new PageGenerator(config, gConfig, table);
        }
        throw new Exception("不支持的生成器类型：" + gConfig.getType());
    }

}
