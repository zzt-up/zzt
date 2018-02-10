package com.biz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.service.StudentService;

public class StudentModifyPush extends HttpServlet {
	StudentService stu=new StudentService();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String bir=request.getParameter("bir");
		String avg=request.getParameter("avg");
		String desc=request.getParameter("desc");		
		stu.StudentModifyPush(id,name,bir,avg,desc);
		StudentManage Stu=new StudentManage();
		Stu.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}