<%@ page import="com.book.service.IAdminService" %>
<%@ page import="com.book.service.impl.AdminServiceImpl" %>
<%@ page import="com.book.domain.Customer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/24
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户管理</title>
    <script type="text/javascript">
        function change(id) {
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200){
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","${pageContext.request.contextPath}/servlet/adminServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=changeCustomer&" + "id=" + id);
        }

    </script>
</head>
<body>
    <%
        IAdminService adminService = new AdminServiceImpl();
        List<Customer> allCustomer = adminService.getAllCustomer();
        request.setAttribute("customers", allCustomer);
    %>
    <c:forEach var="i" items="${customers}" varStatus="vs">
        <table>
            <tr><td>用户ID: <td><td>${i.id}</td></tr>
            <tr><td>用户名: <td><td>${i.username}</td></tr>
            <tr><td>密码: <td><td>${i.password}</td></tr>
            <tr><td>身份: <td><td>${i.permission == 0 ? "普通用户" : "会员用户"}&nbsp;<input type="button" value="切换用户身份" onclick="change(${i.id})"/></td></tr>
            <tr><td>备注: <td><td>${i.message}</td></tr>
        </table>
        <br /><br /><br />
    </c:forEach>
</body>
</html>
