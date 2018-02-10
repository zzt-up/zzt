package com.biz.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.service.StudentService;
/**
 * 
 * @author zzt
 * 2018年2月10日
 * Description:学生修改第一步，先等到学生信息发送到指定页面
 */
public class StudentModifyGet extends HttpServlet {
	StudentService Stu=new StudentService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("hello zzt...");
		String id=request.getParameter("id");
		ArrayList<Map<String,String>> list=new ArrayList<Map<String,String>>();
		list=Stu.StudentModify(id);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/studentModify.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}