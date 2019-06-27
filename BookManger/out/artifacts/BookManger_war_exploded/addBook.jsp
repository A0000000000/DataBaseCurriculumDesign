<%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/25
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加书籍</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/servlet/bookServlet?method=addBook" method="post">
        图书名称: <input type="text" name="bookname" /><br />
        图书作者: <input type="text" name="author" /><br />
        出版社: <input type="text" name="publisher" /><br />
        图书数量: <input type="text" name="number" /><br />
        价格: <input type="text" name="normalPrice" /><br />
        会员价格: <input type="text" name="VIPPrice" /><br />
        <input type="submit" value="增加" />
    </form>
    <br />
    <a href="${pageContext.request.contextPath}/addBookInfo.jsp">增加书籍详细信息</a>
</body>
</html>
