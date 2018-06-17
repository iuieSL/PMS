package com.sl.pms.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * json处理的
 * 
 * @author 莉
 * 
 */
public class Json {

	public static <T> List<String[]> ListChangeJson(List<T> list, T t)
			throws IllegalArgumentException, IllegalAccessException {
		// 获取所有字段
		List<String[]> result = new ArrayList<>();
		int length = list.size();

		for (int i = 0; i < length; i++) {
			result.add(getSingleString(list.get(i)));
		}
		return result;
	}

	private static <T> String[] getSingleString(T t) {
		Class<? extends Object> c = t.getClass();
		Field[] fields = c.getDeclaredFields();
		String[] d = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			// 访问私有方法
			fields[i].setAccessible(true);
			// 跳过静态属性
			String mod = Modifier.toString(fields[i].getModifiers());
			if (mod.indexOf("static") != -1)
				continue;

			// 获得类型
			String className = fields[i].getType().getSimpleName();
			// 忽略Set类型
			if (className.equalsIgnoreCase("Set")) {
				continue;
			}
			try {
				if (fields[i].get(t) != null) {
					Method method1 = c.getMethod("get"
							+ toUpperCase(fields[i].getName()));
					d[i] = method1.invoke(t) + "";
				}
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchMethodException | SecurityException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return d;
	}

	public static String toUpperCase(String str) {
		if (str != null && !str.trim().equalsIgnoreCase("")) {
			String first = str.substring(0, 1);
			str = str.replaceFirst(first, first.toUpperCase());
			return str;
		}
		return null;
	}

	/**
	 * 为了查询把所有对象的属性绑定到一个值
	 * 
	 * @param t
	 * @param value
	 * @return
	 */
	public static <T> T bindValue(T t, String value) {
		Class<? extends Object> c = t.getClass();
		Field[] fields = c.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			// 访问私有方法
			fields[i].setAccessible(true);
			try {
				// 跳过静态属性
				String mod = Modifier.toString(fields[i].getModifiers());
				if (mod.indexOf("static") != -1)
					continue;
				// 获得类型
				String className = fields[i].getType().getSimpleName();
				// 忽略Set类型
				if (className.equalsIgnoreCase("Set")) {
					continue;
				}
				if(className.equalsIgnoreCase("String")){
					Method method = c.getMethod("set"
							+ toUpperCase(fields[i].getName()),String.class);
					// 运行set方法
					method.invoke(t, value);
				}
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return t;
	}
}
