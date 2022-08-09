
package com.leigang.bi.fast.autocode.util;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * 
 * <p>内容摘要:io工具类</p>
 * <p>完成日期: 2013年9月7日 下午5:06:30</p>
 * <p>修改记录:</p>
 * <pre>
 *    修改日期:
 *    修 改 人:
 *    修改内容:
 * </pre>
 * @author   leigang
 */
public class IOUtil {
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(IOUtil.class);
	public static void writeCodeFile(String path, String code){
		path = path.replaceAll("\\\\", "/");
		
		String dir = path.substring(0, path.lastIndexOf("/"));
		File dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		FileOutputStream fileOut = null;
		OutputStreamWriter fileOutWriter = null;
		try{
			fileOut =new FileOutputStream(path);
			fileOutWriter = new OutputStreamWriter(fileOut,"utf-8");
			fileOutWriter.write(code);
			fileOutWriter.flush();
			fileOutWriter.close();
			fileOut.close();
			LOGGER.debug("done!");
			LOGGER.debug(path);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(fileOut!=null){
					fileOut.close();
				}
				if(fileOutWriter!=null){
					fileOutWriter.close();
				}
			}catch (Exception e){
			}
		}

	}
}
