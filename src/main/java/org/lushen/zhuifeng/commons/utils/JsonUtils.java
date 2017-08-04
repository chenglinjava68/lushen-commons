package org.lushen.zhuifeng.commons.utils;

import java.util.List;

import org.lushen.zhuifeng.commons.utils.support.JsonException;

import com.alibaba.fastjson.JSON;

/**
 * JSON工具类
 * 
 * @author hlm
 */
public class JsonUtils {

	/**
	 * 解析 JSON串 为 Java Bean
	 * 
	 * @param json				JSON串
	 * @param clazz				目标对象类型
	 * @return T
	 * @throws JsonException
	 */
	public static final <T> T resolveJson2Bean(String json, Class<T> clazz) throws JsonException {
		
		try {
			
			return JSON.parseObject(json, clazz);
			
		} catch(Exception e) {
			
			throw new JsonException(e);
		}
	}

	/**
	 * 解析 JSON串 为 Java Bean List
	 * 
	 * @param json				JSON串
	 * @param clazz				目标对象子元素类型
	 * @return T
	 * @throws JsonException
	 */
	public static final <T> List<T> resolveJson2BeanList(String json, Class<T> clazz) throws JsonException {
		
		try {
			
			return JSON.parseArray(json, clazz);
			
		} catch(Exception e) {
			
			throw new JsonException(e);
		}
	}
	
	/**
	 * 转换  Java Bean 为 JSON串
	 */
	public static final String toJsonString(Object object) throws JsonException {
		
		try {
			
			return JSON.toJSONString(object);
			
		} catch(Exception e) {
			
			throw new JsonException(e);
		}
	}

}
