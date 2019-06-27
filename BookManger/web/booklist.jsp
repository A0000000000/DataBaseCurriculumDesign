<%@ page import="com.book.service.IBookService" %>
<%@ page import="com.book.service.impl.BookServiceImpl" %>
<%@ page import="com.book.domain.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/26
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>商品列表</title>
    <script type="text/javascript">
        function buy(id) {
            var num = document.getElementById(id + "num").value;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200) {
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","${pageContext.request.contextPath}/servlet/orderServlet", true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=addBookToCart&" + "id=" + id + "&num=" + num);
        }
    </script>
</head>
<body>
    <%
        IBookService bookService = new BookServiceImpl();
        List<Book> allBookInfo = bookService.getAllBookInfo();
        request.setAttribute("books", allBookInfo);
    %>
    <c:forEach var="i" items="${books}" varStatus="vs">
        书名: ${i.bookname}<br />
        作者: ${i.author}<br />
        库存: ${i.number}<br />
        价格: ${customer.permission == 0 ? i.normalPrice : i.VIPPrice}<br />
        出版社: ${i.publisher}<br />
        购买数量: <input type="text" id="${i.id}num" /><br />
        <input type="button" value="加入购物车" onclick="buy('${i.id}')"/><br />
    </c:forEach>
</body>
</html>
