/**  
* <p>Title: StudentService.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* @author shenlan  
* @date 2018年2月6日  
* @version 1.0  
*/ 
package com.biz.service;

import java.util.ArrayList;
import java.util.Map;

import com.biz.dao.StudentDao;

/**
 * @author zzt
 * 2018年2月6日
 * Description: 
 */
public class StudentService {
	StudentDao dao=new StudentDao();

	/**
	 * @param name
	 * @param bir
	 * @param avg
	 * @param desc
	 */
	
	public void StudentAdd(String name, String bir, String avg, String desc) {
		// TODO Auto-generated method stub
		  dao.addStudent(name,bir,avg,desc);
	}

	/**
	 * @return
	 */
	public ArrayList<Map<String, String>> StudentManage() {
		// TODO Auto-generated method stub
		return dao.StudentManage();
	}

	/**
	 * @param id
	 * @return
	 */
	public ArrayList<Map<String, String>> StudentModify(String id) {
		// TODO Auto-generated method stub
		return dao.StudentManage(id);
	}

	/**
	 * @param id
	 * @param name
	 * @param bir
	 * @param avg
	 * @param desc
	 */
	public void StudentModifyPush(String id, String name, String bir, String avg, String desc) {
		dao.StudentModifyPush(id,name,bir,avg,desc);
		
	}

	/**
	 * @param string
	 */
	public void StudentDel(String id) {
		dao.StudentDel(id);
		
	}

}
