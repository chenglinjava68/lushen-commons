package org.lushen.zhuifeng.commons.utils;

/**
 * 转换器 接口
 * 
 * @author hlm
 */
public interface Parser {

	/**
	 * 正向转换
	 * 
	 * @param arg
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	<F> String parseAsString(F arg) throws Exception;
	
	/**
	 * 逆向转换
	 * 
	 * @param arg
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	<F> F parseAsBean(String arg, Class<F> clazz) throws Exception;
	
}
