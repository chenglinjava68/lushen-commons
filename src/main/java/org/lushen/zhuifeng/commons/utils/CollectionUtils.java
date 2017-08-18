package org.lushen.zhuifeng.commons.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 集合类、Map类、数组等工具类
 * 
 * @author hlm
 */
public class CollectionUtils {
	
	/**
	 * 获取第一个元素
	 * 
	 * @param collection
	 * @return
	 */
	public static final <E> E findFirstElement(Collection<E> collection) {
		if(ObjectUtils.isEmpty(collection)) {
			return null;
		}
		return collection.iterator().next();
	}
	
	/**
	 * 可粘连Map
	 * 
	 * @return SimpleMap<K, V>
	 */
	public static final <K, V> SimpleMap<K, V> createSimpleMap() {
		return new SimpleMap<>();
	}
	
	/**
	 * 根据下标集，从数组中提取对应位置的集
	 * 
	 * @param args
	 * @param indexs
	 * @return Object[]
	 */
	public static final Object[] extractFromArray(Object[] args, int[] indexs) {
		if(ObjectUtils.isEmpty(args) || ObjectUtils.isEmpty(indexs)) {
			return new Object[0];
		}
		if(sizeOf(indexs) > sizeOf(args)) {
			throw new ArrayIndexOutOfBoundsException("The size of indexs rather than the size of args.");
		}
		Object[] objs = new Object[indexs.length];
		int i = 0;
		for(int index : indexs) {
			if(index >= args.length) {
				throw new ArrayIndexOutOfBoundsException(index);
			}
			objs[i++] = args[index];
		}
		return objs;
	}
	
	/**
	 * 获取对象数组、集合、Map的长度（null=0, Object=1）
	 * 
	 * @param object
	 * @return int
	 */
	public static final int sizeOf(Object object) {
		if(ObjectUtils.isNull(object)) {
			return 0;
		}
		if(object instanceof Collection<?>) {
			return ((Collection<?>)object).size();
		}
		else if(object instanceof Object[]) {
			return ((Object[])object).length;
		}
		else if(object instanceof Map<?, ?>) {
			return ((Map<?, ?>)object).size();
		}
		else {
			return 1;
		}
	}
	
	/**
	 * 合并Map为一个新Map
	 * 
	 * @param map1
	 * @param map2
	 * @return Map<K, V>
	 */
	public static final <K, V> Map<K, V> mergeMap(Map<K, V> map1, Map<K, V> map2) {
		if(ObjectUtils.isNull(map1) && ObjectUtils.isNull(map2)) {
			return new HashMap<K, V>();
		}
		if(ObjectUtils.isEmpty(map1)) {
			return map2;
		}
		if(ObjectUtils.isEmpty(map2)) {
			return map1;
		}
		Map<K, V> mergeMap = new HashMap<K, V>(map1.size()+map2.size());
		mergeMap.putAll(map1);
		mergeMap.putAll(map2);
		return mergeMap;
	}
	
	/**
	 * 转换Map的key值为小写
	 * 
	 * @param valuesMap
	 * @return Map<String, Object>
	 */
	public static final Map<String, Object> toLowerCaseMapKey(Map<String, Object> valuesMap) {
		if(ObjectUtils.isEmpty(valuesMap)) {
			return new HashMap<>(0);
		}
		Map<String, Object> newValuesMap = new HashMap<>();
		for(Entry<String, Object> entry : valuesMap.entrySet()) {
			newValuesMap.put(entry.getKey().toLowerCase(), entry.getValue());
		}
		return newValuesMap;
	}

	/**
	 * 转换Map的key值为大写
	 * 
	 * @param valuesMap
	 * @return Map<String, Object>
	 */
	public static final Map<String, Object> toUpCaseMapKey(Map<String, Object> valuesMap) {
		if(ObjectUtils.isEmpty(valuesMap)) {
			return new HashMap<>(0);
		}
		Map<String, Object> newValuesMap = new HashMap<>();
		for(Entry<String, Object> entry : valuesMap.entrySet()) {
			newValuesMap.put(entry.getKey().toUpperCase(), entry.getValue());
		}
		return newValuesMap;
	}
	
	/**
	 * 简单的Map，用于粘连put、remove
	 * @author hlm
	 * @param <K>
	 * @param <V>
	 */
	@SuppressWarnings("serial")
	public static class SimpleMap<K, V> extends HashMap<K, V> {
		
		/**
		 * put into Map
		 * 
		 * @param key
		 * @param value
		 * @return SimpleMap<K, V>
		 */
		public SimpleMap<K, V> serialPut(K key, V value) {
			put(key, value);
			return this;
		}
		
		/**
		 * put all into Map
		 * 
		 * @param map
		 * @return SimpleMap<K, V>
		 */
		public SimpleMap<K, V> serialPutAll(Map<K, V> map) {
			putAll(map);
			return this;
		}
		
		/**
		 * remove from Map
		 * 
		 * @param key
		 * @return SimpleMap<K, V>
		 */
		public SimpleMap<K, V> serialRemove(K key) {
			remove(key);
			return this;
		}
		
	}

}
