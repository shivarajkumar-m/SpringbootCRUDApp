<%@page import="java.util.*,com.ibm.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Student u=(Student)request.getAttribute("user");
%>
<h3> Username: <%= u.getName() %></h3>
<h3> Email: <%= u.getEmail() %></h3>
<h3> Registered successfully</h3>

</body>
</html>