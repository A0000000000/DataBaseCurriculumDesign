<%@ page import="com.book.service.IOrderService" %>
<%@ page import="com.book.service.impl.OrderServiceImpl" %>
<%@ page import="com.book.domain.Customer" %>
<%@ page import="com.book.domain.OrderInfo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/26
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>所有订单</title>
</head>
<body>
    <%
        IOrderService orderService = new OrderServiceImpl();
        Customer customer = (Customer) request.getSession().getAttribute("customer");
        List<OrderInfo> allOrder = orderService.getAllOrder(customer.getId());
        System.out.println(allOrder.size());
        request.setAttribute("orders", allOrder);
    %>
    下单人ID: ${customer.id}
    <c:forEach var="i" items="${orders}" varStatus="vs">
        订单ID: ${i.orderId}<br />
        订单状态: ${i.status == 0 ? "未支付" : "已支付"}&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/servlet/orderServlet?method=buyBook&orderId=${i.orderId}">支付</a>
        <br />
    </c:forEach>
</body>
</html>
