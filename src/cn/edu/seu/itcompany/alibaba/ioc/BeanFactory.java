package cn.edu.seu.itcompany.alibaba.ioc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.InputStreamReader;
import java.lang.reflect.Method;

import java.util.HashMap;
import java.util.Map;


public class BeanFactory {

	public static void main(String[] args) throws Exception {
		Object bean=getBean("cn.edu.seu.itcompany.alibaba.ioc.Singleton.java");
	}
	
	private static Map<String, Object> beanPool = new HashMap();

	public static Object getBean(String bean) throws Exception {
		
		Object beanInstance = null;
		
		if (!beanPool.containsKey(bean)) {
			
			File file = new File("C:\\dev\\workspace\\idea\\newcoder\\src\\cn\\edu\\seu\\itcompany\\alibaba\\ioc\\ioc.txt");

			//InputStreamReader in=new InputStreamReader(new FileInputStream(file));
			
			BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			
			//ileInputStream in = new FileInputStream(file);

			String beanName = in.readLine();

			Class clazz = Class.forName(beanName);

			Method construct = clazz.getDeclaredMethod("getInstance");

			beanInstance = construct.invoke(clazz);

			beanPool.put(beanName, beanInstance);
		} else
			beanInstance = beanPool.get(bean);

		return beanInstance;

	}
}
