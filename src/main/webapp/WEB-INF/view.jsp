<%@page import="java.util.*,com.ibm.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students</title>
</head>
<body>

<h2>Students</h2>

<table border="1">
<tr>
<th>Id</th>
<th>Name</th>
<th>Email</th>
<th>Actions</th>
</tr>

<%
List<Student> st = (List<Student>) request.getAttribute("students");

if(st != null && !st.isEmpty()){
    for(Student s : st){
%>
<tr>
    <td><%= s.getId() %></td>
    <td><%= s.getName() %></td>
    <td><%= s.getEmail() %></td>
    <td>
        <a href="<%= request.getContextPath() %>/delete/<%= s.getId() %>">Delete</a>
        |
        <a href="<%= request.getContextPath() %>/edit/<%= s.getId() %>">Edit</a>
    </td>
</tr>
<%
    }
} else {
%>
<tr>
    <td colspan="4">No students found</td>
</tr>
<%
}
%>

</table>

</body>
</html>