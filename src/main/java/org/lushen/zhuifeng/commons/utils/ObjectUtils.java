package org.lushen.zhuifeng.commons.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 
 * Object工具类
 * 
 * @author hlm
 */
public class ObjectUtils {
	
	/**
	 * 判断两个对象是否同个对象
	 * 
	 * @param obj1
	 * @param obj2
	 * @return boolean
	 */
	public static final boolean equals(Object obj1, Object obj2) {
		if(isNull(obj1)) {
			return false;
		}
		if(isNull(obj2)) {
			return false;
		}
		return obj1 == obj2;
	}
	
	/**
	 * 是否为null值
	 * 
	 * @param object
	 * @return boolean
	 */
	public static final boolean isNull(Object object) {
		return object == null;
	}

	/**
	 * 是否不为null值
	 * 
	 * @param object
	 * @return boolean
	 */
	public static final boolean isNotNull(Object object) {
		return object != null;
	}
	
	/**
	 * 是否含有null值
	 * 
	 * @param object
	 * @return boolean
	 */
	public static final boolean containsNull(Object... objects) {
		for(Object object : objects) {
			if(isNull(object)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否不含有null值
	 * 
	 * @param object
	 * @return boolean
	 */
	public static final boolean notContainsNull(Object... objects) {
		return !containsNull(objects);
	}
	
	/**
	 * 是否为空集
	 * 
	 * @param object
	 * @return boolean
	 */
	public static final boolean isEmpty(Object object) {
		if(isNull(object)) {
			return true;
		}
		if(object instanceof Collection<?>) {
			return ((Collection<?>)object).isEmpty();
		}
		else if(object instanceof Object[]) {
			return ((Object[])object).length == 0;
		}
		else if(object instanceof Map<?,?>) {
			return ((Map<?,?>)object).isEmpty();
		}
		else {
			return false;
		}
	}
	
	/**
	 * 是否不为空集
	 * 
	 * @param object
	 * @return boolean
	 */
	public static final boolean isNotEmpty(Object object) {
		return !isEmpty(object);
	}

}
