package com.biz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.service.StudentService;
/**
 * 
 * @author zzt
 * 2018年2月10日
 * Description: 学生添加
 */
public class StudentAdd extends HttpServlet {
	StudentService stu = new StudentService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String bir = request.getParameter("bir");
		String avg = request.getParameter("avg");
		String desc = request.getParameter("desc");
		stu.StudentAdd(name, bir, avg, desc);
		StudentManage stu = new StudentManage();
		stu.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}