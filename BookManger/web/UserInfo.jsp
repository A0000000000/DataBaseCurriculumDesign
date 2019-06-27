<%@ page import="com.book.domain.Customer" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/24
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <script type="text/javascript">
        function ChangeInfo() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var message = document.getElementById("message").value;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200){
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","${pageContext.request.contextPath}/servlet/customerServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=changeInfo&" + "username=" + username + "&password=" + password + "&message=" + message);
        }
    </script>
</head>
<body>
    <%
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        request.setAttribute("username", customer.getUsername());
        request.setAttribute("message", customer.getMessage());
        request.setAttribute("status", customer.getPermission() == 0 ? "普通用户" : "会员用户");
    %>
    <div>
        用户信息：<br />
        用户名: ${username} <input type="hidden" name="username" value="${username}" id="username" /><br/>
        新密码: <input type="password" name="password" id="password" /><br/>
        备注:<input type="text" value="${message}" name="message" id="message"/><br/>
        身份:${status}<br/>
        <input type="button" value="修改信息" onclick="ChangeInfo()"/><br />
        操作：<br/>
        <a href="${pageContext.request.contextPath}/servlet/customerServlet?method=logout">退出</a><br />
        <a href="${pageContext.request.contextPath}/booklist.jsp">购买书籍</a><br />
        <a href="${pageContext.request.contextPath}/cart.jsp">查看购物车</a><br />
        <a href="${pageContext.request.contextPath}/orderlist.jsp">查看所有订单</a><br />
    </div>
</body>
</html>
