package org.lushen.zhuifeng.commons.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Properties工具类
 * 
 * @author hlm
 */
public class PropUtils {

	private static final Log log = LogFactory.getLog(PropUtils.class);
	
	/**
	 * 加载Properties文件
	 */
	public static final Properties loadProperties(String file) {
		try (InputStream inputStream = PropUtils.class.getResourceAsStream(file);) {
			Properties props = new Properties();
			props.load(inputStream);
			return props;
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 从Properties中获取值
	 */
	public static final String loadProperties(String file, String key) {
		try (InputStream inputStream = PropUtils.class.getResourceAsStream(file);) {
			Properties props = new Properties();
			props.load(inputStream);
			return props.getProperty(key);
		} catch (Exception e) {
			log.error(e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 从Properties中获取值
	 */
	public static final String getProp(Properties props, String key) {
		String value = props.getProperty(key);
		if(value == null) {
			throw new RuntimeException("Can't get property value by key :: " + key);
		}
		return value;
	}

}
