package com.biz.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
 * Description:学生全查询
 */
public class StudentManage extends HttpServlet {
	StudentService Stu = new StudentService();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();
		list = Stu.StudentManage();
		Iterator<Map<String, String>> iterator = list.iterator();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/studentManage.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}