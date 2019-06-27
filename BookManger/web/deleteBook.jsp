<%@ page import="com.book.service.IBookService" %>
<%@ page import="com.book.service.impl.BookServiceImpl" %>
<%@ page import="com.book.domain.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/25
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>删除书籍</title>
    <script type="text/javascript">
        function del() {
            var req = new XMLHttpRequest();
            var sel = document.getElementById("sel");
            var id = sel[sel.selectedIndex].value;
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200) {
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","${pageContext.request.contextPath}/servlet/bookServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=deleteBookInfo&" + "id=" + id);
        }
    </script>
</head>
<body>
    <%
        IBookService bookService = new BookServiceImpl();
        List<Book> allBookInfo = bookService.getAllBookInfo();
        request.setAttribute("books", allBookInfo);
    %>
    <p>注意: 删除书籍将会把书籍详细信息一块删除</p>
    <select id="sel">
        <c:forEach var="i" items="${books}" varStatus="vs">
            <option value="${i.id}">${i.bookname}</option>
        </c:forEach>
    </select><br />
    <input type="button" value="删除" onclick="del()"/><br />
    <a href="${pageContext.request.contextPath}/deleteBookInfo.jsp">删除书籍详细信息</a>
</body>
</html>
