<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="second-maven/stuMa" method="post">
<a href="jsp/studentAdd.jsp">添加学生信息</a>
<table>
<tr><td>学生编号</td><td>姓名</td><td>出生日期</td><td>平均分</td><td>备注</td><td>操作</td>      </tr>

<c:forEach items="${list}" var="list">

<tr>
   <td>${list.id}</td><td>${list.name}</td>
   <td>${list.birthday}</td><td>${list.avg}</td> 
   <td>${list.desc}</td><td><a href="StudentModify?id=${list.id}">修改</a>|<a href="StudentDel?id=${list.id}">删除</a></td>
</tr>
</c:forEach>
</table>

</form>
</body>
</html>