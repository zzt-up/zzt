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
 <h2>学生信息修改</h2>

<form action="/second-maven/studentAdd" method="post">
<c:forEach items="${list }" var="list">
 姓名：<input type="text" name="id" value="${list.id}"  readonly>
姓名：<input type="text" name="name" value="${list.name}">
出生年月：<input type="date" name="bir"value="${list.birthday}">
平均分：<input type="text" name="avg" value="${list.avg}"><br>
备注：<br><textarea rows="10" cols="50" name="desc" value="${list.desc}"></textarea> 
<input type="submit">
</c:forEach>
</form>
</body>
</html>