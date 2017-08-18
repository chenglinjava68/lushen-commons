package org.lushen.zhuifeng.commons.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;

/**
 * 注解工具类
 * 
 * @author hlm
 */
public class AnnotationUtils {
	
	/**
	 * 获取目标类目标注解
	 * 
	 * @param destClass
	 * @param annotationClass
	 * @return T
	 */
	public static final <T extends Annotation> T findClassAnnotation(Class<?> destClass, Class<T> annotationClass) {
		if(ObjectUtils.containsNull(destClass, annotationClass)) {
			return null;
		}
		T annotation = destClass.getAnnotation(annotationClass);
		return annotation;
	}
	
	/**
	 * 获取目标方法目标注解
	 * 
	 * @param method
	 * @param annotationClass
	 * @return T
	 */
	public static final <T extends Annotation> T findMethodAnnotation(Method method, Class<T> annotationClass) {
		if(ObjectUtils.containsNull(method, annotationClass)) {
			return null;
		}
		T annotation = method.getAnnotation(annotationClass);
		return annotation;
	}
	
	/**
	 * 获取目标方法的参数目标注解集
	 * 
	 * @param method
	 * @param annotationClass
	 * @return List<Annotation>
	 */
	@SuppressWarnings("unchecked")
	public static final <T extends Annotation> Collection<T> findParamAnnotations(Method method, Class<T> annotationClass) {
		if(ObjectUtils.containsNull(method, annotationClass)) {
			return null;
		}
		Annotation[][] annotations = method.getParameterAnnotations();
		if(CollectionUtils.sizeIsEmpty(annotations)) {
			return new ArrayList<T>(0);
		}
		Collection<T> annotationList = new ArrayList<T>(annotations.length);
		for(Annotation[] annos : annotations) {
			boolean hasAnnotation = false;
			for(Annotation annotation : annos) {
				if(ClassUtils.isAssignable(annotation.getClass(), annotationClass)) {
					hasAnnotation = true;
					annotationList.add((T) annotation);
					break;
				}
			}
			if(!hasAnnotation) {
				annotationList.add((T)null);
			}
		}
		return annotationList;
	}
	
	/**
	 * 获取注解的键值Map
	 */
	public static final Map<String, Object> findAnnotationValues(Annotation annotation) throws Exception {
		if(ObjectUtils.isNull(annotation)) {
			return new HashMap<String, Object>(0);
		}
		Map<String, Object> valuesMap = new HashMap<>();
		for(Method method : org.lushen.zhuifeng.commons.utils.ClassUtils.findMethods(annotation.getClass())) {
			if(method.getName().equals("annotationType")) {
				continue;
			}
			Object obj = method.invoke(annotation);
			valuesMap.put(method.getName(), obj);
		}
		return valuesMap;
	}
	
	/**
	 * 获取注解集的键值Map集
	 * 
	 * @param annotations
	 * @return Collection<Map<String, Object>>
	 * @throws Exception
	 */
	public static final Collection<Map<String, Object>> findAnnotationsValues(Collection<Annotation> annotations) throws Exception {
		Collection<Map<String, Object>> collection = new ArrayList<>(annotations.size());
		for(Annotation annotation : annotations) {
			Map<String, Object> annotationValues = findAnnotationValues(annotation);
			collection.add(annotationValues);
		}
		return collection;
	}
	
	/**
	 * 获取注解的默认键值Map
	 */
	public static final Map<String, Object> findAnnotationDefaultValues(Annotation annotation) throws Exception {
		if(ObjectUtils.isNull(annotation)) {
			return new HashMap<String, Object>(0);
		}
		Map<String, Object> valuesMap = new HashMap<>();
		for(Method method : org.lushen.zhuifeng.commons.utils.ClassUtils.findMethods(annotation.getClass())) {
			if(method.getName().equals("annotationType")) {
				continue;
			}
			valuesMap.put(method.getName(), method.getDefaultValue());
		}
		return valuesMap;
	}
	
	/**
	 * 获取注解集的默认键值Map集
	 * 
	 * @param annotations
	 * @return Collection<Map<String, Object>>
	 * @throws Exception
	 */
	public static final Collection<Map<String, Object>> findAnnotationsDefaultValues(Collection<Annotation> annotations) throws Exception {
		Collection<Map<String, Object>> collection = new ArrayList<>(annotations.size());
		for(Annotation annotation : annotations) {
			Map<String, Object> annotationValues = findAnnotationDefaultValues(annotation);
			collection.add(annotationValues);
		}
		return collection;
	}

}
