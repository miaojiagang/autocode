package com.leigang.bi.fast.autocode.util;

import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by Administrator on 2017/11/28.
 */
public class PathUtil {

    public static String getRootPath() {
        URL url = PathUtil.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = null;
        try {
            filePath = URLDecoder.decode(url.getPath(), "utf-8");// 转化为utf-8编码
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (filePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
            // 截取路径中的jar包名
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        return filePath;
    }


    public static String getPath(String inputPath) {
        String res ="";
        res = getRootPath() + inputPath;
        return res;
    }
}
