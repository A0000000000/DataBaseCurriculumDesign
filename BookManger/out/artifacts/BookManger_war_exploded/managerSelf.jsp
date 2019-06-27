<%@ page import="com.book.domain.Admin" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/24
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理信息</title>
</head>
<body>
    <%
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        request.setAttribute("username", admin.getUsername());
        request.setAttribute("password", admin.getPassword());
        request.setAttribute("status", admin.getPermission() == 0 ? "普通管理员" : "超级管理员");
    %>
</body>
    <form method="post" action="${pageContext.request.contextPath}/servlet/adminServlet?method=changePassword">
        <p>提示： 暂只支持修改密码</p>
        用户名: ${username} <input type="hidden" value="${username}" name="username"><br />
        密码:  <input type="text" value="${password}" name="password" /><br />
        身份: ${status}<br />
        <input type="submit" value="修改" />
    </form>
</html>
