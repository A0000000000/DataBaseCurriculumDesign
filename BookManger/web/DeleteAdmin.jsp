<%@ page import="com.book.service.IAdminService" %>
<%@ page import="com.book.service.impl.AdminServiceImpl" %>
<%@ page import="com.book.domain.Admin" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/24
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>删除用户</title>
    <script type="text/javascript">
        function deleteAdmin(username) {
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200) {
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","${pageContext.request.contextPath}/servlet/adminServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=deleteAdmin&" + "username=" + username);
        }
    </script>
</head>
<body>
    <%
        IAdminService adminService = new AdminServiceImpl();
        List<Admin> allAdmin = adminService.getAllAdmin();
        request.setAttribute("admins", allAdmin);
    %>
    <c:forEach var="i" items="${admins}" varStatus="vs">
        <table>
            <tr><td>用户名：</td><td>${i.username}</td></tr>
            <tr><td>密码：</td><td>${i.password}</td></tr>
            <tr><td>权限：</td><td>${i.permission == 0 ? "普通管理员" : "超级管理员"}</td></tr>
        </table>
        <input type="button" value="删除" onclick="deleteAdmin('${i.username}')" />
    </c:forEach>
</body>
</html>
