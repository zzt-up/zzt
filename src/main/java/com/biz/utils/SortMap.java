/**  
* <p>Title: SortMap.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* @author shenlan  
* @date 2018年2月8日  
* @version 1.0  
*/ 
package com.biz.utils;

import java.util.Comparator;
import java.util.Map;

/**
 * @author zzt
 * 2018年2月8日
 * Description: 集合排序工具类
 */
 public class SortMap implements Comparator{
	
	private String sortName = null;
	private String compareType = "int";
	public SortMap(String sortName,String compareType){
		this.sortName = sortName;
		this.compareType = compareType;
	}

	@Override
	public int compare(Object o1, Object o2) {
		Map m1 = (Map)o1;
		Map m2 = (Map)o2;
		if(compareType.equalsIgnoreCase("int")){
			/*Integer ia = (Integer)m1.get(sortName);*/
			Integer ia = Integer.valueOf((String) m1.get(sortName));
			Integer ib = Integer.valueOf((String) m2.get(sortName));
			
			/*Integer ib = (Integer)m2.get(sortName);*/
			//compareTo两边参数不同，正反排序方式不同
			return ib.compareTo(ia);
		}else{
			String ia = (String)m1.get(sortName);
			String ib = (String)m2.get(sortName);
			return ia.compareTo(ib);
		}
	}
	
}
