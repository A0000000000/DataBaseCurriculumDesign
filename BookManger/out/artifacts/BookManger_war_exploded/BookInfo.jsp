<%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/25
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>书籍详细信息</title>
</head>
<body>
    图书ID: ${bookId}<br />
    图书章节名称:<br />
    <c:forEach var="i" items="${bookChapters}" varStatus="vs">
        ${i.key}: ${i.value}<br />
    </c:forEach>
    图书章节内容:<br />
    <c:forEach var="i" items="${bookContent}" varStatus="vs">
        ${i.key}: ${i.value}<br />
    </c:forEach>
</body>
</html>
