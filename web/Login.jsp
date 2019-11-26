<%--
  Created by IntelliJ IDEA.
  User: Real
  Date: 2019/11/24
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <form name="loginForm" onsubmit="return check()" action="LoginServlet" method="post">
        <p align="center">用户登录</p>
        <p align="center">
            <br>
            用&nbsp;户&nbsp;名&nbsp;
            <input class="input" tabindex="1" type="text" maxlength="20" size="36" name="user">
            <br>
            密 &nbsp;码 &nbsp;
            <input class="input" tabindex="2" type="password" maxlength="20" size="40" name="pwd">
            <br>
            <br>
            <input type="submit" class="btn" tabindex="3"  value="登录">
            <input type="reset" value="重置">
        </p>
    </form>
</body>
</html>
