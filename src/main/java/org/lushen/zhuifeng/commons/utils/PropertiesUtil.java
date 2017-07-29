package org.lushen.zhuifeng.commons.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * properties文件读取工具类
 *
 * @author hlm
 */
public final class PropertiesUtil {
	
	private static Log log =LogFactory.getLog(PropertiesUtil.class);

	/**
	 * 从properties对象中读取properties的值
	 * 
	 * @param properties
	 * @param key
	 * 
	 * @return String
	 */
	public static final String readFromProperties(Properties properties, String key) {
		return properties.getProperty(key);
	}
	
	/**
	 * 从文件中读取properties的值
	 * 
	 * @param file
	 * @param key
	 * 
	 * @return String
	 */
	public static final String readFromResourceFile(String file, String key) {
		
		try (InputStream is = PropertiesUtil.class.getResourceAsStream(file)) {
			
			Properties prop = new Properties();
			prop.load(is);
			
			return prop.getProperty(key);
			
		} catch (Exception e) {
			
			log.info(e);
			
			return null;
			
		}
	}
	
	/**
	 * 从文件流中读取properties的值
	 * 
	 * @param is
	 * @param key
	 * 
	 * @return String
	 */
	public static final String readFromStream(InputStream is, String key) {
		
		try {
			
			Properties prop = new Properties();
			prop.load(is);
			
			return prop.getProperty(key);
			
		} catch (Exception e) {
			
			log.info(e);
			
			return null;
			
		}
	}

}
