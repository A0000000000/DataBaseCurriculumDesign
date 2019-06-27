<%@ page import="com.book.service.IBookService" %>
<%@ page import="com.book.service.impl.BookServiceImpl" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/26
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>修改书籍详细信息</title>
    <script type="text/javascript">
        function edit(str1, str2) {
            var chaptersName = document.getElementById("chaptersName").value;
            var content = document.getElementById("content").value;
            var url = "chapters=" + str1 + "&bookId=" + str2 + "&chaptersName=" + chaptersName + "&content=" + content;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200) {
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST","${pageContext.request.contextPath}/servlet/bookServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=editInfo&" + url);
        }
    </script>
</head>
<body>
    <%
        IBookService bookService = new BookServiceImpl();
        List<Map<String, String>> bookInfo = bookService.getBookInfo();
        request.setAttribute("bookInfos", bookInfo);
    %>
</body>
    新章节名称: <input type="text" id="chaptersName" /><br />
    新章节内容: <input type="text" id="content" /><br />
    <c:forEach var="i" items="${bookInfos}" varStatus="vs1">
        <c:set var="tmp1" scope="request" value=""></c:set>
        <c:set var="tmp2" scope="request" value=""></c:set>
        <c:forEach var="j" items="${i}" varStatus="vs2">
            <c:set var="tmp1" scope="request" value="${j.key=='章节' ? j.value : tmp1}"></c:set>
            <c:set var="tmp2" scope="request" value="${j.key=='书籍ID' ? j.value : tmp2}"></c:set>
            ${j.key}: ${j.value}&nbsp;&nbsp;&nbsp;&nbsp;
        </c:forEach>
        <input type="button" value="修改" onclick="edit('${tmp1}', '${tmp2}')" /><br />
    </c:forEach>

</html>
