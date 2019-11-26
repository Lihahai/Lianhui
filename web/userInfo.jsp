<%@ page import="shitilei.User" %><%--
  Created by IntelliJ IDEA.
  User: Real
  Date: 2019/11/24
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>userInfo</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<%
    if (null == session.getAttribute("user")) {
        response.sendRedirect("Login.jsp");
    }

  User user=(User)session.getAttribute("user");
   /*
    System.out.println("UserInfo:");
  System.out.println(user.getuAccount());
  System.out.println(user.getuPwd());
  */


%>
<div class="bar">
    <div class="bar_div">
        <div class="logo"><img src="images/logo.png" width="50" height="50" /></div>

        <div class="user">
            <input  type="button" value="注销" class="bar_btn"/>
        </div>

    </div>
</div>
<div class="btn_bar">
    <div class="btnbar_div">
        <input  type="button" value="任务" class="bar_btn1"/>
        <input  type="button" value="排行" class="bar_btn1"/>
        <input  type="button" value="用户管理" class="bar_btn1"/>
        <input  type="button" value="个人信息" name="userInfo" onclick="window.location.href=('userInfo.jsp')" class="bar_btn1"/>
    </div>
</div>
<div class="mbody">
    <div class="mbody_div">
        <div class="caidan">
            <input type="button" value="任务发布"  class="caidan_btn"/>
            <input type="button" value="任务列表"  class="caidan_btn"/>
            <input type="button" value="任务接取"  class="caidan_btn"/>
        </div>
        <div class="main_div">
            <br>
            <br>
            <p align="center">我的信息</p>
            <br>
            <p align="center">
                <table align="center">
            <tr>
                <td>
                    用户名：${sessionScope.user.uAccount}
                </td>
                <td></td>
            </tr>
        </table>
            </p>
        </div>
    </div>

</div>


</body>
</html>
