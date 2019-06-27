<%@ page import="com.book.domain.Admin" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/24
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员信息</title>
</head>
<body>
    <%
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        request.setAttribute("username", admin.getUsername());
        request.setAttribute("status", admin.getPermission() == 0 ? "管理员" : "超级管理员");
    %>
    <div>
        管理员信息：<br />
        用户名: ${username}<br />
        身份: ${status}<br />
        操作: <br />
        <a href="${pageContext.request.contextPath}/servlet/adminServlet?method=logout">退出</a><br />
        <a href="${pageContext.request.contextPath}/managerUser.jsp">管理用户</a><br />
        <a href="${status == "管理员" ? "/BookManger/managerSelf.jsp" : "/BookManger/managerAdmin.jsp"}">管理普通管理员</a><br />
        <a href="${pageContext.request.contextPath}/managerBook.html">管理图书信息</a><br />
    </div>
</body>
</html>
