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
	public static void main(String[] args) {
	//预存一些到sset里面	
	Jedis jedis=JedisPoolUtils.getJedis();
	jedis.zadd("StuID", 10001, "10001");
	jedis.zadd("StuID", 10002, "10002");
	jedis.zadd("StuID", 10003, "10003");
	jedis.zadd("StuID", 10004, "10004");
	jedis.zadd("StuID", 10005, "10005");
	
	Map<String,String> map=new HashMap<String,String>();
	map=jedis.hgetAll("myset2");
	//存到sset里面的试着全部取出来
	Set<String> zrange = jedis.zrange("StuID", 0, -1);
	for(String str:zrange){
		System.out.println("str :"+str);
	}
	//取sset里面最后一位，按分数排序，吧分数和ID设为一致的，遍历最后一位就遍历到最后的ID了
	
	Map<String, String> hgetAll = jedis.hgetAll("10006");
	 Iterator<Entry<String, String>> it = hgetAll.entrySet().iterator();  
     while (it.hasNext()) {  
         Entry<String, String> me = it.next();  
         String key = me.getKey();  
         Object value = me.getValue();  
         System.out.println("key  : "+key +"value :"+value);
     } 
    List<Map<String,String>> list=new ArrayList<Map<String,String>>();
    Set<String> zrange2 = jedis.zrange("StuID", 0, -1);
 	for(String str:zrange2){
 		System.out.println("str====="+str);
 		Map<String, String> hgetAll1 = jedis.hgetAll(str);
 		list.add(hgetAll1);
 	}
 	 Iterator<Map<String, String>> iterator = list.iterator();
     while( iterator.hasNext()){
     	System.out.println("---list--"+iterator.next());
 	   
    }
 	
	jedis.close();
	
	
	}
   
	/*public static void main(String[] args) {
    	Jedis jedis=JedisPoolUtils.getJedis();
    	jedis.zadd("id", 1, "1");
    	jedis.zadd("id", 1, "12");
    	jedis.zadd("id", 1, "123");
    	jedis.zadd("id", 1, "1234");
    	jedis.zincrby("id", 1, "56666");
        String username=jedis.get("username");
        System.out.println(username);
        Map<String,String> map=new HashMap<String,String>();
        map=jedis.hgetAll("myset2");
        List<Map<String,String>> list=new LinkedList<Map<String,String>>();
        list.add(map);
        Map<String,String> map1=new HashMap<String,String>();
        map1=jedis.hgetAll("myset1");
        list.add(map1);
      
        Iterator<Map<String, String>> iterator = list.iterator();
        while( iterator.hasNext()){
        	System.out.println("-----"+iterator.next());
    	   
       }
        Iterator<Entry<String, String>> it = map.entrySet().iterator();  
        while (it.hasNext()) {  
            Entry<String, String> me = it.next();  
            String key = me.getKey();  
            Object value = me.getValue();  
            System.out.println("key  : "+key +"value :"+value);
        } 
        Set<String> zrange = jedis.zrange("id", 1, -1);
        for(String set:zrange){
        	System.out.println(set);
        	
        }
    	jedis.close();
    	JedisPoolUtils.close();
    	
	}*/

	/**
	 * @param name
	 * @param bir
	 * @param avg
	 * @param desc
	 */
	public void addStudent(String name, String bir, String avg, String desc) {
		// TODO Auto-generated method stub
		Jedis jedis=JedisPoolUtils.getJedis();
		Set<String> zrange1 = jedis.zrange("StuID", -1, -1);
		
		for(String str:zrange1){
			
			int index=Integer.valueOf(str);
			System.out.println("str index:"+index);
			index++;
			System.out.println("___"+index);
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
	 * @return
	 */
	public ArrayList<Map<String, String>> StudentManage() {
		Jedis jedis=JedisPoolUtils.getJedis();
		ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
	    Set<String> zrange2 = jedis.zrange("StuID", 0, -1);
		 	for(String str:zrange2){
		 		Map<String, String> hgetAll1 = jedis.hgetAll(str);
		 		list.add(hgetAll1);
		 		SortMap sm = new SortMap("avg","int");  
		        Collections.sort(list,sm); 
		 		
		 	}
		 /*	 Iterator<Map<String, String>> iterator = list.iterator();
		     while( iterator.hasNext()){
		     	System.out.println("---list--"+iterator.next());
		 	   
		    }*/
		 jedis.close();
		 
		 return list;
		    
		// TODO Auto-generated method stub
/*		Jedis jedis=JedisPoolUtils.getJedis();
		jedis.zadd("StuID", 1, "0001");
		jedis.zadd("StuID", 2, "0002");
		jedis.zadd("StuID", 3, "0003");
		jedis.zadd("StuID", 4, "0004");
		jedis.zadd("StuID", 3, "0005");
		
		Map<String,String> map=new HashMap<String,String>();
		map=jedis.hgetAll("myset2");
		Set<String> zrange = jedis.zrange("StuID", 0, -1);
		for(String str:zrange){
			System.out.println("str :"+str);
		}
		
		return null;*/
	}

	/**
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
	 */
	public void StudentDel(String id) {
		Jedis jedis=JedisPoolUtils.getJedis();
		jedis.del(id);
		jedis.zrem("StuID", id);
		jedis.close();
		
	}
	}
