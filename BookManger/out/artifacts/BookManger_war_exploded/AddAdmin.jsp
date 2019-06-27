<%--
  Created by IntelliJ IDEA.
  User: 13406
  Date: 2019/6/24
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加管理员</title>
    <script type="text/javascript">
        function AddAdmin() {
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if(req.readyState == 4 && req.status == 200){
                    alert(req.responseText);
                    location.reload();
                }
            };
            req.open("POST", "/BookManger/servlet/adminServlet", true);
            req.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            req.send("method=addAdmin&" + "username=" + username + "&password=" + password);
        }
    </script>
</head>
<body>
    用户名: <input type="text" name="username" id="username"/><br />
    密码: <input type="password" name="password" id="password"/><br />
    <input type="button" value="增加" onclick="AddAdmin()">
</body>
</html>
