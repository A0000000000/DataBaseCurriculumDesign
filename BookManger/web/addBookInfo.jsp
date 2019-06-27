<%@ page import="com.book.service.IBookService" %>
<%@ page import="com.book.service.impl.BookServiceImpl" %>
<%@ page import="com.book.domain.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/25
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>增加书章节信息</title>
    <script type="text/javascript">

        function addBookInfo() {
            var sel = document.getElementById("sel");
            var id = sel[sel.selectedIndex].value;
            var chapters = document.getElementById("chapters").value;
            var chaptersName = document.getElementById("chaptersName").value;
            var content = document.getElementById("content").value;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200) {
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","${pageContext.request.contextPath}/servlet/bookServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=addBookInfo&" + "id=" + id
                + "&chapters=" + chapters + "&chaptersName=" + chaptersName + "&content=" + content);
        }

    </script>
</head>
<body>
    <%
        IBookService bookService = new BookServiceImpl();
        List<Book> allBookInfo = bookService.getAllBookInfo();
        List<Integer> list = new LinkedList<>();
        for (Book book : allBookInfo) {
            list.add(book.getId());
        }
        request.setAttribute("list", list);
    %>
    <select id="sel">
        <c:forEach var="i" items="${list}" varStatus="vs">
            <option value="${i}">${i}</option>
        </c:forEach>
    </select><br />
    章节: <input type="text" name="chapters" id="chapters" /><br />
    章节名称: <input type="text" name="chaptersName" id="chaptersName" /><br />
    章节内容: <input type="text" name="content" id="content" /><br />
    <input type="button" value="增加" onclick="addBookInfo()"/>
</body>
</html>
