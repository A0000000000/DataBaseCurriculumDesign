<%@ page import="com.book.service.IAdminService" %>
<%@ page import="com.book.service.impl.AdminServiceImpl" %>
<%@ page import="com.book.domain.Admin" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/24
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>修改管理员信息</title>
    <script type="text/javascript">
        window.onload = function () {
            var select = document.getElementById("select");
            var sel = select.selectedIndex;
            var username = select.options[sel].value;
            document.getElementById("username").value = username;
            select.onchange = function () {
                var sel = select.selectedIndex;
                var username = select.options[sel].value;
                document.getElementById("username").value = username;
            }
        }
        function ChangeInfo() {
            var select = document.getElementById("select");
            var username = select.options[select.selectedIndex].value;
            var newUsername = document.getElementById("username").value;
            var newPassword = document.getElementById("password").value;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200){
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","/BookManger/servlet/adminServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=changeInfo&" + "username=" + username + "&newUsername=" + newUsername + "&newPassword=" + newPassword);
        }
    </script>
</head>
<body>
    <%
        IAdminService adminService = new AdminServiceImpl();
        List<Admin> allAdmin = adminService.getAllAdmin();
        request.setAttribute("admins", allAdmin);
    %>
    <select id="select">
        <c:forEach var="i" items="${admins}" varStatus="vs">
            <option value="${i.username}">${i.username}</option>
        </c:forEach>
    </select><br/>
    新用户名: <input type="text" name="username" id="username" /><br />
    新密码: <input type="password" name="password" id="password"/><br />
    <input type="button" value="修改" onclick="ChangeInfo()" />
</body>
</html>
