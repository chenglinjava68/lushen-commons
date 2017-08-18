package org.lushen.zhuifeng.commons.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class类对象工具类
 * 
 * @author hlm
 */
public class ClassUtils {
	
	/**
	 * 获取Object Class<?>
	 * 
	 * @param object
	 * @return Class<?>
	 */
	public static final Class<?> getClass(Object object) {
		if(ObjectUtils.isNull(object)) {
			return null;
		}
		return object.getClass();
	}
	
	/**
	 * 获取Class的指定方法
	 * 
	 * @param destClass
	 * @param methodName
	 * @param parameterTypes
	 * @return Method
	 * @throws Exception
	 */
	public static final Method findMethod(Class<?> destClass, String methodName, Class<?>... parameterTypes) throws Exception {
		if(ObjectUtils.isNull(destClass)) {
			throw new NullPointerException("Dest class is null");
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(methodName)) {
			throw new NoSuchMethodException("methodName=" + methodName);
		}
		return destClass.getMethod(methodName, parameterTypes);
	}
	
	/**
	 * 获取public方法（不包含Object的方法）
	 * 
	 * @param destClass
	 * @return Method[]
	 */
	public static final Method[] findMethods(Class<?> destClass) {
		
		if(ObjectUtils.isNull(destClass)) {
			return new Method[0];
		}
		
		List<String> objectMethods = Arrays.asList("getClass", "hashCode", "equals", "clone", "toString", "notify", "notifyAll", "wait");
		
		Collection<Method> collection = new ArrayList<Method>();

		for(Method method : destClass.getMethods()) {
			if( ! objectMethods.contains(method.getName())) {
				collection.add(method);
			}
		}
		Method[] methods = new Method[collection.size()];
		return collection.toArray(methods);
	}

	/**
	 * 获取所有方法（不包含Object的方法）
	 * 
	 * @param destClass
	 * @return Method[]
	 */
	public static final Method[] findDeclaredMethods(Class<?> destClass) {
		
		if(ObjectUtils.isNull(destClass)) {
			return new Method[0];
		}
		
		List<String> objectMethods = Arrays.asList("getClass", "hashCode", "equals", "clone", "toString", "notify", "notifyAll", "wait");
		
		Collection<Method> collection = new ArrayList<Method>();
		
		for(Method method : destClass.getDeclaredMethods()) {
			if( ! objectMethods.contains(method.getName())) {
				collection.add(method);
			}
		}
		Method[] methods = new Method[collection.size()];
		return collection.toArray(methods);
	}
	
	/**
	 * 获取public Fields
	 * 
	 * @param destClass
	 * @return Field[]
	 */
	public static final Field[] findFields(Class<?> destClass) {
		if(ObjectUtils.isNull(destClass)) {
			return new Field[0];
		}
		Field[] fields = destClass.getFields();
		return fields;
	}
	
	/**
	 * 获取所有 Fields
	 * 
	 * @param destClass
	 * @return Field[]
	 */
	public static final Field[] findDeclaredFields(Class<?> destClass) {
		if(ObjectUtils.isNull(destClass)) {
			return new Field[0];
		}
		Field[] fields = destClass.getDeclaredFields();
		return fields;
	}
	
	/**
	 * 获取目标类属性的setter方法集
	 * 
	 * @param destClass
	 * @return Map<Field, Method>
	 */
	public static final Map<Field, Method> findDeclaredFieldSetters(Class<?> destClass) {
		if(ObjectUtils.isNull(destClass)) {
			return new HashMap<>(0);
		}
		Field[] fields = findDeclaredFields(destClass);
		return findFieldSetters(destClass, fields);
	}
	
	/**
	 * 获取目标类属性的setter方法集
	 * 
	 * @param destClass
	 * @param fields
	 * @return Map<Field, Method>
	 */
	public static final Map<Field, Method> findFieldSetters(Class<?> destClass, Field[] fields) {
		
		if(ObjectUtils.isEmpty(fields)) {
			return new HashMap<>(0);
		}
		
		Map<Field, Method> settersMap = new HashMap<>();
		Method[] methods = findMethods(destClass);
		
		for(Field field : fields) {
			for(Method method : methods) {
				if(isFieldSetter(method, field)) {
					settersMap.put(field, method);
					break;
				}
			}
		}
		return settersMap;
	}

	/**
	 * 判断方法是否属性的setter
	 * 
	 * @param method
	 * @param field
	 * @return boolean
	 */
	public static final boolean isFieldSetter(Method method, Field field) {
		
		if(ObjectUtils.containsNull(method, field)) {//含有空值
			return false;
		}
		
		if( ! atOneClass(method, field)) {//同个类的属性和方法
			return false;
		}
		
		if( ! hasOnlyOneArgument(method)) {//方法只有一个参数
			return false;
		}
		
		Class<?> firstArgClass = findFirstArgumentClass(method);
		Class<?> fieldTypeClass = findFieldTypeClass(field);
		if( ! ObjectUtils.equals(firstArgClass, fieldTypeClass)) {//方法参数类型和属性类型相同
			return false;
		}
		
		//方法名称判断
		String methodName = method.getName();
		String fieldName = field.getName();
		String upCaseFirstCharFieldName = StringUtils.toUpCaseFirstChar(fieldName);
		String setterMethodName = StringUtils.join("set", upCaseFirstCharFieldName);
		return StringUtils.equals(methodName, setterMethodName);
	}
	
	/**
	 * 获取目标类属性的getter方法集
	 * 
	 * @param destClass
	 * @return Map<Field, Method>
	 */
	public static final Map<Field, Method> findDeclaredFieldGetters(Class<?> destClass) {
		if(ObjectUtils.isNull(destClass)) {
			return new HashMap<>(0);
		}
		Field[] fields = findDeclaredFields(destClass);
		return findFieldGetters(destClass, fields);
	}
	
	/**
	 * 获取目标类属性的getter方法集
	 * 
	 * @param destClass
	 * @param fields
	 * @return Map<Field, Method>
	 */
	public static final Map<Field, Method> findFieldGetters(Class<?> destClass, Field[] fields) {
		
		if(ObjectUtils.isEmpty(fields)) {
			return new HashMap<>(0);
		}
		
		Map<Field, Method> gettersMap = new HashMap<>();
		Method[] methods = findMethods(destClass);
		
		for(Field field : fields) {
			for(Method method : methods) {
				if(isFieldGetter(method, field)) {
					gettersMap.put(field, method);
					break;
				}
			}
		}
		return gettersMap;
	}

	/**
	 * 判断方法是否属性的getter
	 * 
	 * @param method
	 * @param field
	 * @return boolean
	 */
	public static final boolean isFieldGetter(Method method, Field field) {
		
		if(ObjectUtils.containsNull(method, field)) {//含有空值
			return false;
		}
		
		if( ! atOneClass(method, field)) {//同个类的属性和方法
			return false;
		}
		
		if( ! hasNoArgument(method)) {//无方法参数
			return false;
		}
		
		Class<?> methodReturnTypeClass = findReturnTypeClass(method);
		Class<?> fieldTypeClass = findFieldTypeClass(field);
		if( ! ObjectUtils.equals(methodReturnTypeClass, fieldTypeClass)) {//方法参数类型和属性类型相同
			return false;
		}
		
		String methodName = method.getName();
		String fieldName = field.getName();
		String upCaseFirstCharFieldName = StringUtils.toUpCaseFirstChar(fieldName);
		if(ObjectUtils.equals(fieldTypeClass, boolean.class)) {
			String setterMethodName = StringUtils.join("is", upCaseFirstCharFieldName);
			return StringUtils.equals(methodName, setterMethodName);
		} else {
			String setterMethodName = StringUtils.join("get", upCaseFirstCharFieldName);
			return StringUtils.equals(methodName, setterMethodName);
		}
	}
	
	/**
	 * 是否在同一个Class
	 * 
	 * @param method
	 * @param field
	 * @return boolean
	 */
	public static final boolean atOneClass(Method method, Field field) {
		if(ObjectUtils.containsNull(method, field)) {
			return false;
		}
		Class<?> methodDeclaring = method.getDeclaringClass();
		Class<?> fieldDeclaring = field.getDeclaringClass();
		return methodDeclaring.equals(fieldDeclaring);
	}
	
	/**
	 * 获取方法第一个参数的类型
	 * 
	 * @param method
	 * @return Class<?>
	 */
	public static final Class<?> findFirstArgumentClass(Method method) {
		if(ObjectUtils.isNull(method)) {
			return null;
		}
		Class<?>[] classes = method.getParameterTypes();
		if(ObjectUtils.isEmpty(classes)) {
			return null;
		}
		return classes[0];
	}
	
	/**
	 * 方法是否只有一个参数
	 * 
	 * @param method
	 * @return boolean
	 */
	public static final boolean hasOnlyOneArgument(Method method) {
		if(ObjectUtils.isNull(method)) {
			return false;
		}
		Class<?>[] classes = method.getParameterTypes();
		return classes.length == 1;
	}
	
	/**
	 * 方法是否无参数
	 * 
	 * @param method
	 * @return boolean
	 */
	public static final boolean hasNoArgument(Method method) {
		if(ObjectUtils.isNull(method)) {
			return false;
		}
		Class<?>[] classes = method.getParameterTypes();
		return classes.length == 0;
	}
	
	/**
	 * 获取类属性的类型Class
	 * 
	 * @param field
	 * @return Class<?>
	 */
	public static final Class<?> findFieldTypeClass(Field field) {
		if(ObjectUtils.isNull(field)) {
			return null;
		}
		return field.getType();
	}
	
	/**
	 * 获取方法返回值类型
	 * 
	 * @param method
	 * @return Class<?>
	 */
	public static final Class<?> findReturnTypeClass(Method method) {
		if(ObjectUtils.isNull(method)) {
			return null;
		}
		return method.getReturnType();
	}

	/**
	 * 获取参数类型列表
	 * 
	 * @param method
	 * @return Class<?>[]
	 */
	public static final Class<?>[] findParametersTypeClass(Method method) {
		if(ObjectUtils.isNull(method)) {
			return new Class<?>[0];
		}
		return method.getParameterTypes();
	}
	
}
