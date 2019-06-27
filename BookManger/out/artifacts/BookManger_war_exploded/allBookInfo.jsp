<%@ page import="com.book.service.IBookService" %>
<%@ page import="com.book.service.impl.BookServiceImpl" %>
<%@ page import="com.book.domain.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/25
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>全部图书信息</title>
</head>
<body>
    <%
        IBookService bookService = new BookServiceImpl();
        List<Book> allBookInfo = bookService.getAllBookInfo();
        request.setAttribute("books", allBookInfo);
    %>
    <c:forEach var="i" items="${books}" varStatus="vs">
        <table>
            <tr><td>图书ID: </td><td>${i.id}</td></tr>
            <tr><td>图书名称: </td><td>${i.bookname}</td></tr>
            <tr><td>图书作者: </td><td>${i.author}</td></tr>
            <tr><td>出版社: </td><td>${i.publisher}</td></tr>
            <tr><td>剩余数量: </td><td>${i.number}</td></tr>
            <tr><td>价格: </td><td>${i.normalPrice}</td></tr>
            <tr><td>会员价格: </td><td>${i.VIPPrice}</td></tr>
        </table>
        <a href="servlet/bookServlet?method=bookInfo&id=${i.id}">查看详细信息</a>
        <br /><br /><br /><br />
    </c:forEach>
</body>
</html>
