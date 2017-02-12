package cn.edu.seu.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestListMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getDebugList();
		getDebugMap();
		getDebugListMap();
	}
	
	public static void getDebugList(){
		List<String> list= new ArrayList<String>();
		list.add("hello");
		list.add("debug");
		list.add("is");
		list.add("OK");
		System.out.println(list);
	}	
	public static void getDebugMap(){
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("姓名","小明");
		map.put("性别","男");
		map.put("爱好","篮球");
		map.put("年龄",(Integer)22);		
		System.out.println(map);
	}
	
	public static Map getMap(Map<String, Object> map){
		map.put("姓名","小明");
		map.put("性别","男");
		map.put("爱好","篮球");
		map.put("年龄",(Integer)22);	
		return map;
	}
	public static void getDebugListMap(){
		Map<String,Object> map= new HashMap<String,Object>();
		List<HashMap<String, Object>> lm=new ArrayList<HashMap<String,Object>>();
		lm.add((HashMap<String, Object>) getMap(map));
		lm.add((HashMap<String, Object>) getMap(map));
		lm.add((HashMap<String, Object>) getMap(map));
		lm.add((HashMap<String, Object>) getMap(map));
		lm.add((HashMap<String, Object>) getMap(map));
		lm.add((HashMap<String, Object>) getMap(map));
		System.out.println(lm);
	}

}
