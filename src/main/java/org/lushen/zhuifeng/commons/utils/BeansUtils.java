package org.lushen.zhuifeng.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * bean对象工具类
 * 
 * @author hlm
 */
public class BeansUtils {

	/**
	 * 复制属性值到bean（忽略大小写）
	 * 
	 * @param obj
	 * @param valuesMap
	 */
	public static final void copyValuesIgnoreCase(Object object, Map<String, Object> valuesMap) {
		
		if(ObjectUtils.containsNull(object, valuesMap)) {
			throw new NullPointerException();
		}
		
		Class<?> objectClass = ClassUtils.getClass(object);
		Field[] fields = ClassUtils.findDeclaredFields(objectClass);
		Map<Field, Method> settersMap = ClassUtils.findFieldSetters(objectClass, fields);
		Map<String, Object> lowerCaseKeyMap = CollectionUtils.toLowerCaseMapKey(valuesMap);
		
		for(Field field : fields) {
			Method setter = settersMap.get(field);
			Object value = lowerCaseKeyMap.get(field.getName().toLowerCase());
			if(ObjectUtils.isNotNull(setter) && ObjectUtils.isNotNull(value)) {
				try {
					setter.invoke(object, value);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	/**
	 * 复制属性值到bean
	 * 
	 * @param object
	 * @param valuesMap
	 */
	public static final void copyValues(Object object, Map<String, Object> valuesMap) {
		if(ObjectUtils.containsNull(object, valuesMap)) {
			throw new NullPointerException();
		}
		try {
			BeanUtils.copyProperties(object, valuesMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
