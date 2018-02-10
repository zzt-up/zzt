/**  
* <p>Title: StudentDao.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* @author shenlan  
* @date 2018年2月6日  
* @version 1.0  
*/ 
package com.biz.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.biz.utils.JedisPoolUtils;
import com.biz.utils.SortMap;

import redis.clients.jedis.Jedis;

/**
 * @author zzt
 * 2018年2月6日
 * Description: 
 */
public class StudentDao { 	
	/**
	 * @param name
	 * @param bir
	 * @param avg
	 * @param desc
	 * 学生添加
	 */
public void addStudent(String name, String bir, String avg, String desc) {
		Jedis jedis=JedisPoolUtils.getJedis();
		Set<String> zrange1 = jedis.zrange("StuID", -1, -1);
		for(String str:zrange1){			
			int index=Integer.valueOf(str);			
			index++;			
			String indexStr=index+"";
			Map<String,String> map1=new HashMap<String,String>();
			map1.put("id",indexStr );
			map1.put("birthday", bir);
			map1.put("name", name);
			map1.put("desc", desc);
			map1.put("avg", avg);		
			jedis.zadd("StuID", index, indexStr);
			//把在sset里面取到的ID 加1 ，再作为Key用到hash,里面。
			jedis.hmset(indexStr, map1);			
		}
		    jedis.close();	 	
	}
	/**
	 * 学生全查询
	 * @return
	 */
	public ArrayList<Map<String, String>> StudentManage() {
		Jedis jedis=JedisPoolUtils.getJedis();
		ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
	    Set<String> zrange2 = jedis.zrange("StuID", 0, -1);
		 	for(String str:zrange2){
		 		Map<String, String> hgetAll1 = jedis.hgetAll(str);
		 		list.add(hgetAll1);
		 		//调用map集合排序工具类
		 		SortMap sm = new SortMap("avg","int");  
		        Collections.sort(list,sm); 		 		
		 	}
				 jedis.close();		 
				 return list;	    
	}

	/**
	 * 学生修改
	 * @param id
	 * @return
	 */
	public ArrayList<Map<String, String>> StudentManage(String id) {
		// TODO Auto-generated method stub
		Jedis jedis=JedisPoolUtils.getJedis();
		ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
		Map<String, String> hgetAll1 = jedis.hgetAll(id);
		list.add(hgetAll1);
		return list;
	}

	/**
	 * @param id
	 * @param name
	 * @param bir
	 * @param avg
	 * @param desc
	 * 学生修改提交
	 */
	public void StudentModifyPush(String id, String name, String bir, String avg, String desc) {
		// TODO Auto-generated method stub
		Jedis jedis=JedisPoolUtils.getJedis();
		jedis.hsetnx(id, "name", name);
		jedis.hsetnx(id, "birthday", bir);
		jedis.hsetnx(id, "avg", avg);	
		jedis.hsetnx(id, "desc", desc);
		jedis.close();
		
	}

	/**
	 * @param id
	 * 学生删除
	 */
	public void StudentDel(String id) {
		Jedis jedis=JedisPoolUtils.getJedis();
		jedis.del(id);
		jedis.zrem("StuID", id);
		jedis.close();
		
	}
	}
