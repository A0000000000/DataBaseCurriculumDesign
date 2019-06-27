<%@ page import="com.book.service.IBookService" %>
<%@ page import="com.book.service.impl.BookServiceImpl" %>
<%@ page import="com.book.domain.Book" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/26
  Time: 7:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>编辑书籍信息</title>
    <script type="text/javascript">
        function change() {
            var sel = document.getElementById("sel");
            var id = sel[sel.selectedIndex].value;
            var bookname = document.getElementById("bookname").value;
            var author = document.getElementById("author").value;
            var publisher = document.getElementById("publisher").value;
            var number = document.getElementById("number").value;
            var normalPrice = document.getElementById("normalPrice").value;
            var VIPPrice = document.getElementById("VIPPrice").value;
            var url = "id=" + id + "&bookname=" + bookname + "&author=" + author + "&publisher=" + publisher + "&number=" + number + "&normalPrice=" + normalPrice + "&VIPPrice=" + VIPPrice;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.status == 200 && req.readyState == 4){
                    alert(req.responseText);
                    location.reload();
                }
            }
            req.open("POST","/BookManger/servlet/bookServlet",true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=changeBook&" + url);

        }
    </script>
</head>
<body>
    <%
        IBookService bookService = new BookServiceImpl();
        List<Book> allBookInfo = bookService.getAllBookInfo();
        request.setAttribute("books", allBookInfo);
    %>
    <select id="sel">
        <c:forEach var="i" items="${books}" varStatus="vs">
            <option value="${i.id}">${i.bookname}</option>
        </c:forEach>
    </select><br />
    新书名: <input type="text" id="bookname" /><br />
    新作者: <input type="text" id="author" /><br />
    新出版社: <input type="text" id="publisher" /><br />
    新数量: <input type="text" id="number" /><br />
    新价格: <input type="text" id="normalPrice" /><br />
    新会员价格: <input type="text" id="VIPPrice" /><br />
    <input type="button" value="修改" onclick="change()" /><br />
    <br /><br />
    <a href="${pageContext.request.contextPath}/editBookInfo.jsp">修改书籍详细信息</a>
</body>
</html>
