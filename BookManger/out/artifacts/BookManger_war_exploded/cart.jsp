<%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/26
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>查看购物车</title>
</head>
<body>
    <c:forEach var="i" items="${cart.map}" varStatus="vs">
        书籍id: ${i.key} &nbsp;&nbsp; 购买数量: ${i.value}<br />
    </c:forEach>
    <a href="${pageContext.request.contextPath}/servlet/orderServlet?method=summitOrder">提交订单</a>
</body>
</html>
